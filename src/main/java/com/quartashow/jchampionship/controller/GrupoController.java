package com.quartashow.jchampionship.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quartashow.jchampionship.model.Grupo;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	@RequestMapping(value="grupo/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> save(@Valid Grupo grupo, BindingResult result) {
		return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
