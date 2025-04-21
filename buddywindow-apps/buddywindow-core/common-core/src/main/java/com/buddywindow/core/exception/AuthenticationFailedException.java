package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationFailedException extends BaseException {

	private static final long serialVersionUID = 1L;

	public AuthenticationFailedException(String message) {
        super(message, "AUTH_001", HttpStatus.UNAUTHORIZED);
    }
}
