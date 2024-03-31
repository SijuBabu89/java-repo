package com.buddywindow.auth.resource;

import org.springframework.web.bind.annotation.RequestBody;

import com.buddywindow.auth.dto.AuthTokenDTO;
import com.buddywindow.auth.dto.LoginRequestDTO;


public interface IAuthResource {

	public String getUserAuthToken(String username, String password);
	
	public AuthTokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO);
}
