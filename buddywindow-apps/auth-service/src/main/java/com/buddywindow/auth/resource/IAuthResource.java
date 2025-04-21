package com.buddywindow.auth.resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.buddywindow.auth.dto.AuthTokenDTO;
import com.buddywindow.auth.dto.LoginRequestDTO;
import com.buddywindow.auth.dto.UserDTO;


public interface IAuthResource {

	public String getUserAuthToken(String username, String password);
	
	public AuthTokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO);
	
	public boolean authenticateToken(@RequestHeader(value = "access_token", required = true) final String authorizationHeader);
	
}
