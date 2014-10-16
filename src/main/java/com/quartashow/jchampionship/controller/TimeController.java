package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.net.URI;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
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
import com.quartashow.jchampionship.dao.JogadorDao;
import com.quartashow.jchampionship.dao.TimeDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Jogador;
import com.quartashow.jchampionship.model.Time;

@Controller
@RequestMapping("time")
public class TimeController {

	@Autowired
	private TimeDao timeDao;
	
	@Autowired
	private JogadorDao jogadorDao;

	@RequestMapping(value="/page/simple")
	public ModelAndView pageSimple(Time time) {
		ModelAndView mv = new ModelAndView("_base_simple");
		mv.addObject("content_import", "time-system-form");
		mv.addObject("time", time);
		return mv ;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Time time, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		if(time.getId() <= 0)
			this.timeDao.save(time);
		else
			this.timeDao.update(time);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/time/get/"+time.getId()));
		return new ResponseEntity<String>(new Gson().toJson(time), headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Time time = this.timeDao.get(Time.class, id);
		return new ResponseEntity<String>(new Gson().toJson(time), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}")
	public ModelAndView pageTime(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-page");
		Time time = this.timeDao.get(Time.class, id);
		mv.addObject("time", time);
		return mv;
	}
	
	@RequestMapping(value="/system/{id}")
	public ModelAndView pageTimeSystem(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("_base2");
		mv.addObject("content_import", "time-form");
		Time time = this.timeDao.get(Time.class, id);
		mv.addObject("time", time);
		mv.addObject("jogadoresAll", this.jogadorDao.getList(Jogador.class));
		return mv;
	}
	
	@RequestMapping(value="/system/{timeId}/post/add/jogador/{jogadorId}", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> addJogadorTime(@PathVariable("timeId") long timeId, @PathVariable("jogadorId") long jogadorId) {
		try {
			Time time = this.timeDao.get(Time.class, timeId);
			Jogador jogador = this.jogadorDao.get(Jogador.class, jogadorId);
			time.getJogadores().add(jogador);
			this.timeDao.update(time);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create("/time/system/"+jogador .getId()));			
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(jogador), headers , HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
