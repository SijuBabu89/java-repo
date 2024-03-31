package com.buddywindow.auth.builder;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenBuilder {

	private static TokenBuilder INSTANCE = new TokenBuilder();
	private String secretKey;
	private int tokenExpirationMs;
	private String subject;
	private Map<String, Object> claims;
	
	public static TokenBuilder getInstance() {
		return INSTANCE;
	}
	public TokenBuilder() {
		
	}
	
	public TokenBuilder setSecretKey(String secretKey) {
		this.secretKey = secretKey;
		return this;
	}
	
	public TokenBuilder setTokenExpirationMs(int tokenExpirationMs) {
		this.tokenExpirationMs = tokenExpirationMs;
		return this;
	}
	
	public TokenBuilder setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public TokenBuilder setClaims(Map<String, Object> claims) {
		this.claims = claims;
		return this;
	}
	
	public String build() {
		return Jwts.builder()
		.setClaims(this.claims)
        .setSubject(this.subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + this.tokenExpirationMs * 1000))
        .signWith(SignatureAlgorithm.HS512, this.secretKey)
        .compact();
	}
	
	
}
