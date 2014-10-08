package com.quartashow.jchampionship.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Time;

@Controller
@RequestMapping("time")
public class TimeController {

	@Autowired
	private TimeDao timeDao;

	@RequestMapping(value="/page/simple")
	public ModelAndView pageSimple() {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "time-system-form");
		return mv ;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Time time, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		this.timeDao.save(time);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/time/get/"+time.getId()));
		return new ResponseEntity<String>(new Gson().toJson(time), headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Time time = this.timeDao.get(Time.class, id);
		return new ResponseEntity<String>(new Gson().toJson(time), HttpStatus.OK);
	}
}
