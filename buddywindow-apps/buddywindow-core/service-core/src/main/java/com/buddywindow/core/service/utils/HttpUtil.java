package com.buddywindow.core.service.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

	public static Boolean invokeValidateAuthTokenPostUrl(final String authToken) {
		
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:9001/api/auth/token/authenticate";
        HttpHeaders headers = new HttpHeaders();
        headers.set("access_token", authToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> response = restTemplate.postForEntity(apiUrl, entity, Boolean.class);
        return response.getBody();
	}
}
