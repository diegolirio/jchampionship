package com.quartashow.jchampionship.controller.common;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ValidationResponse {

    private String status;
    private Map<String, String> errorMessages;
    
    public String getStatus() {
            return status;
    }
    public void setStatus(String status) {
            this.status = status;
    }
    public Map<String, String> getErrorMessages() {
            return errorMessages;
    }
    public void setErrorMessages(Map<String, String> errorMessages) {
            this.errorMessages = errorMessages;
    }
    
    public String toJSON() throws JsonGenerationException, JsonMappingException, IOException {
    	return new ObjectMapper().writeValueAsString(this);
    }
	
}
