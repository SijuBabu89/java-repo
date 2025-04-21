package com.buddywindow.auth.service;

import com.buddywindow.auth.entity.TokenUserProfile;

public interface ITokenGeneratorService {

	public String generateAccessTokenFormUsername(String username, TokenUserProfile profiles);
	public String generateRefreshTokenFormUsername(String username, TokenUserProfile profiles);
	public String getUserNameFromAccessToken(String token);
	public boolean validateToken(final String authToken);
	public int getAccessTokenExpirationMs();
	public int getRefershTokenExpirationMs();
	
}
