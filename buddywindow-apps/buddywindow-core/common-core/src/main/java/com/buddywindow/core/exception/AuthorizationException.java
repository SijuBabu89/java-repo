package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends BaseException {
	
    private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
        super(message, "AUTH_002", HttpStatus.FORBIDDEN);
    }
}
