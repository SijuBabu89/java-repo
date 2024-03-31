package com.buddywindow.auth.constant;

public enum TokenType {

	BEARER("Bearer");
	
	private String tokenType;
	
	TokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
