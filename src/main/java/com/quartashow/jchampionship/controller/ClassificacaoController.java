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

import com.google.gson.Gson;
import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.ClassificacaoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Classificacao;

@Controller
@RequestMapping("classificacao")
public class ClassificacaoController {

	@Autowired
	private ClassificacaoDao classficacaoDao;

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Classificacao classificacao, BindingResult result) {
		if(result.hasErrors()) {
			ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
			return new ResponseEntity<String>(new Gson().toJson(validationResponse), HttpStatus.UNAUTHORIZED);
		}
		this.classficacaoDao.save(classificacao);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/classificacao/get/"+classificacao.getId()));
		classificacao = this.classficacaoDao.get(Classificacao.class, classificacao.getId());
		String json = new Gson().toJson(classificacao);
		return new ResponseEntity<String>(json, headers , HttpStatus.CREATED);
	}
	 
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) {
		Classificacao classificacao = this.classficacaoDao.get(Classificacao.class, id);
		return new ResponseEntity<String>(new Gson().toJson(classificacao ), HttpStatus.OK);
	}
	
}
 