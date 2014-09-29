package com.quartashow.jchampionship.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.quartashow.jchampionship.controller.common.ValidationResponse;
import com.quartashow.jchampionship.model.Edicao;

@Controller
@RequestMapping("/edicao")
public class EdicaoController {
	
	@RequestMapping(value="/system/nova", method=RequestMethod.GET)
	public ModelAndView pageNovaEdicao(Edicao edicao) {
		ModelAndView mv = new ModelAndView("_base");
		mv.addObject("content_import", "edicao-system-form");
		mv.addObject("edicao", edicao);
		System.out.println(edicao);
		return mv;
	}

	@RequestMapping(value="/system/nova", method=RequestMethod.POST, produces="application/json")
	@ResponseStatus(value=HttpStatus.CREATED)
	public @ResponseBody ValidationResponse  pageNovaEdicaoPOST(@Valid Edicao edicao, BindingResult result) {
		ValidationResponse validationResponse = new ValidationResponse();
	    if(result.hasErrors() == true) {
	    	HashMap<String, String> mapErros = new HashMap<String, String>();
	    	List<FieldError> fieldErrors = result.getFieldErrors(); 
	    	for (FieldError fieldError : fieldErrors) {
	    		mapErros.put(fieldError.getField(), fieldError.getDefaultMessage());
	    	}               
	    	validationResponse.setStatus("ERROR");
	    	validationResponse.setErrorMessages(mapErros);
	    } else {
	    	validationResponse.setStatus("SUCCESS");
	    }
		return validationResponse;
	}
	
}
