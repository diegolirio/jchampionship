package com.quartashow.jchampionship.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
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

import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.JogoDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Jogo;

@Controller
@RequestMapping("/jogo")
public class JogoController {

	@Autowired
	private JogoDao jogoDao;

	@RequestMapping(value="/post", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> post(@Valid Jogo jogo, BindingResult result) {
		HttpHeaders headers = new HttpHeaders();
		String bodyJson = "{}";
		try {
			if(result.hasErrors()) {
				ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
				return new ResponseEntity<String>(validationResponse.toJSON(), HttpStatus.UNAUTHORIZED);
			}
			this.jogoDao.save(jogo);
			jogo = this.jogoDao.get(Jogo.class, jogo.getId());
			//headers.setLocation(URI.create("/jogo/get/"+jogo.getId()));
			bodyJson = new ObjectMapper().writeValueAsString(jogo);
		} catch (IOException e) {
			e.printStackTrace();
			return  new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(bodyJson, headers , HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> get(@PathVariable("id") long id) throws JsonGenerationException, JsonMappingException, IOException {
		Jogo jogo = this.jogoDao.get(Jogo.class, id);
		return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(jogo), HttpStatus.OK);
	}
	
}
