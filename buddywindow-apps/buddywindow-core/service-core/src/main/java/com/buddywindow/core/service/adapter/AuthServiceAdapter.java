package com.buddywindow.core.service.adapter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.buddywindow.core.exception.AuthenticationFailedException;
import com.buddywindow.core.service.utils.HttpUtil;

@Component
public class AuthServiceAdapter implements IAuthServiceAdapter{
	
    @Value("${buddywindow.security.auth.url:}")
    private String securityAuthUrl;

	@Override
	public void authenticateToken(String authToken) throws AuthenticationFailedException{
		Map<String, String> headers = new HashMap<>();
        headers.put("access_token", authToken);
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        boolean response = HttpUtil.invokeValidateAuthTokenPostUrl(authToken);
        if (!response) {
        	throw new AuthenticationFailedException("Authentication Failed");
        }
		
	}

}
