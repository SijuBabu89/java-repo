package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BaseException {
	
    private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
        super(message, "VALIDATION_001", HttpStatus.BAD_REQUEST);
    }
}
