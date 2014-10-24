package com.quartashow.jchampionship.controller;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.dao.UsuarioDao;
import com.quartashow.jchampionship.helper.ValidationResponseHelper;
import com.quartashow.jchampionship.model.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDao usuarioDao;

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView pageLogin() {
		ModelAndView mv = new ModelAndView("_login");
		return mv;
	} 
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> login(@Valid Usuario usuario, BindingResult result) {
		try {
			if(result.hasErrors()) {
				ValidationResponse validationResponse = new ValidationResponseHelper().fieldsErrorsToValidationResponse(result);
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(validationResponse), HttpStatus.UNAUTHORIZED);
			}
			if(this.usuarioDao.login(usuario) == true) {
				HttpHeaders headers = new HttpHeaders(); 
				headers.setLocation(URI.create("/"));
				return new ResponseEntity<String>(headers , HttpStatus.OK);
			} else {
				ValidationResponse validationResponse = new ValidationResponse();
				validationResponse.setStatus("ERROR");
				Map<String, String> errorMessages = new HashMap<String, String>();
				errorMessages.put("login", "Email ou senha invalido...");
				validationResponse.setErrorMessages(errorMessages );
				return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(validationResponse), HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
