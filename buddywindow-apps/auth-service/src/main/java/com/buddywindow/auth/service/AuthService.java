package com.buddywindow.auth.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.constant.TokenType;
import com.buddywindow.auth.domain.AuthToken;
import com.buddywindow.auth.entity.Right;
import com.buddywindow.auth.entity.TokenUserProfile;
import com.buddywindow.auth.entity.User;

@Service
public class AuthService implements IAuthService{


	@Autowired
	private IUserService userService;
	@Autowired
	private ITokenGeneratorService tokenGeneratorService;
	
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

	@Override
	public boolean validateAuthToken(String authToken) {
		return tokenGeneratorService.validateToken(authToken);
	}
    
    private static TokenUserProfile toUserProfile(User user) {
    	String role = user.getRole() != null ? user.getRole().getName() : "NoRole";
    	List<Right> rights = user.getRole() != null && user.getRole().getRights() != null ? user.getRole().getRights() : new ArrayList<Right>();
    	List<String> rightList = rights.stream().map(r -> r.getName()).collect(Collectors.toList());
		return new TokenUserProfile(user.getId(), user.getUsername(), user.getContact().getEmail(), 
				user.getTitle(), user.getFirstName(), user.getMiddleName(), user.getLastName(), role, rightList);
    }

}
