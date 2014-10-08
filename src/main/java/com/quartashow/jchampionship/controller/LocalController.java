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
import com.quartashow.jchampionship.dao.LocalDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Local;

@Controller
@RequestMapping("local")
public class LocalController {

	@Autowired
	private LocalDao localDao;
	
	@RequestMapping(value="/page/simple", method=RequestMethod.GET)
	public ModelAndView pageSimple() {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "local-system-form"); 
		return mv;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Local local, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		this.localDao.save(local);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/local/get/"+local.getId()));
		return new ResponseEntity<String>(new Gson().toJson(local), headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Local local = this.localDao.get(Local.class, id);
		return new ResponseEntity<String>(new Gson().toJson(local), HttpStatus.OK);
	}
}
