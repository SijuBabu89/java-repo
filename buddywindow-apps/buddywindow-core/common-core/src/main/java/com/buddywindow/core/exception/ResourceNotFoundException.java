package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
	
    private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resourceName, String identifier) {
        super(String.format("%s not found with identifier: %s", resourceName, identifier), 
              "RESOURCE_001", 
              HttpStatus.NOT_FOUND);
    }
}
