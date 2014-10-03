package com.quartashow.jchampionship.helper;

import java.util.HashMap;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.quartashow.jchampionship.controller.common.ValidationResponse;

public class ValidationResponseHelper {

	public ValidationResponse fieldsErrorsToValidationResponse(BindingResult result) {
		ValidationResponse validationResponse = new ValidationResponse();
	    HashMap<String, String> mapErros = new HashMap<String, String>();
	    List<FieldError> fieldErrors = result.getFieldErrors(); 
	    for (FieldError fieldError : fieldErrors) {
	    	mapErros.put(fieldError.getField(), fieldError.getDefaultMessage());
	    }               
	    validationResponse.setStatus("ERROR");
	    validationResponse.setErrorMessages(mapErros);
		return validationResponse;
	}
	
}