package com.quartashow.jchampionship.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.quartashow.jchampionship.controller.common.ValidationResponse;

public class ValidationResponseHelper {

	public ValidationResponse fieldsErrorsToValidationResponse(BindingResult result) {
		ValidationResponse validationResponse = new ValidationResponse();
		Map<String, String> mapErros = new HashMap<String, String>();
	    List<FieldError> fieldErrors = result.getFieldErrors(); 
	    for (FieldError fieldError : fieldErrors) {
	    	mapErros.put(fieldError.getField(), fieldError.getDefaultMessage());
	    	System.out.println("-----------------------");
	    	System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
	    	System.out.println("-----------------------");
	    }               
	    validationResponse.setStatus("ERROR");
	    validationResponse.setErrorMessages(mapErros);
		return validationResponse;
	}
	
	public ValidationResponse fieldsErrorsToValidationResponse(FieldError fieldError) {
		ValidationResponse validationResponse = new ValidationResponse();
	    Map<String, String> mapErros = new HashMap<String, String>();
    	mapErros.put(fieldError.getField(), fieldError.getDefaultMessage());
    	System.out.println("-----------------------");
    	System.out.println(fieldError.getField() + ": " + fieldError.getDefaultMessage());
    	System.out.println("-----------------------");
	    validationResponse.setStatus("ERROR");
	    validationResponse.setErrorMessages(mapErros);
		return validationResponse;
	}	
	
}
