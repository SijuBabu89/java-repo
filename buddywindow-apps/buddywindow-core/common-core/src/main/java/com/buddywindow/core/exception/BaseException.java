package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	private final String errorCode;
    private final HttpStatus httpStatus;

    public BaseException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

	public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
