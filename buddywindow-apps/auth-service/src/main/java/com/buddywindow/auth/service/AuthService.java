package com.buddywindow.auth.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.constant.TokenType;
import com.buddywindow.auth.domain.AuthToken;
import com.buddywindow.auth.entity.TokenUserProfile;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.util.JWTUtil;
import com.buddywindow.auth.util.JsonUtil;



@Service
public class AuthService implements IAuthService{


	@Autowired
	private IUserService userService;
	@Autowired
	private TokenGeneratorService tokenGeneratorService;
	
	@Override
	public AuthToken getAuthToken(String username, String password) {
		User user = userService.getUserByUsernameAndPassword(username, password);
		TokenUserProfile tokenUserProfile = toUserProfile(user);
		String accessToken = tokenGeneratorService.generateAccessTokenFormUsername(username, tokenUserProfile);
		String refreshToken = tokenGeneratorService.generateRefreshTokenFormUsername(username, tokenUserProfile);
		AuthToken authToken = new AuthToken(accessToken, refreshToken, TokenType.BEARER, tokenGeneratorService.getAccessTokenExpirationMs(), 
				tokenGeneratorService.getRefershTokenExpirationMs(), LocalDateTime.now());
		return authToken;
	}
	
    private static TokenUserProfile toUserProfile(User user) {
		return new TokenUserProfile(user.getId(), user.getUsername(), user.getContact().getEmail(), 
				user.getTitle(), user.getFirstName(), user.getMiddleName(), user.getLastName());
    }
    
    

}
