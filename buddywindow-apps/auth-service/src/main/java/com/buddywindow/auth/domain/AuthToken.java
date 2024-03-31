package com.buddywindow.auth.domain;

import java.time.LocalDateTime;

import com.buddywindow.auth.constant.TokenType;


public class AuthToken {

	private final String accessToken;
    private final String refreshToken;
    private final TokenType tokenType;
    private final int accessTokenExpiryInSec;
    private final int refreshTokenExpiryInSec;
    private final LocalDateTime createdAt;
    
	public AuthToken(String accessToken, String refreshToken, TokenType tokenType, int accessTokenExpiryInSec,
			int refreshTokenExpiryInSec, LocalDateTime createdAt) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenType = tokenType;
		this.accessTokenExpiryInSec = accessTokenExpiryInSec;
		this.refreshTokenExpiryInSec = refreshTokenExpiryInSec;
		this.createdAt = createdAt;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public int getAccessTokenExpiryInSec() {
		return accessTokenExpiryInSec;
	}

	public int getRefreshTokenExpiryInSec() {
		return refreshTokenExpiryInSec;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	
    
    
}
