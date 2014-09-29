package com.quartashow.jchampionship.controller.common;

import java.util.Map;

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
    
    
	
}
