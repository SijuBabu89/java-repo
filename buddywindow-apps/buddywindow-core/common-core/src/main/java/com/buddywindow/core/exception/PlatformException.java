package com.buddywindow.core.exception;

import org.springframework.http.HttpStatus;

public class PlatformException extends BaseException {
    private static final long serialVersionUID = 1L;

	public PlatformException(String message) {
        super(message, "PLATFORM_001", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
