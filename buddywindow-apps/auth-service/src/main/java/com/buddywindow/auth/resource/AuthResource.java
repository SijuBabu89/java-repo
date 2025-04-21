package com.buddywindow.auth.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buddywindow.auth.domain.AuthToken;
import com.buddywindow.auth.dto.AddressDTO;
import com.buddywindow.auth.dto.AuthTokenDTO;
import com.buddywindow.auth.dto.ContactDTO;
import com.buddywindow.auth.dto.LoginRequestDTO;
import com.buddywindow.auth.dto.PhoneDTO;
import com.buddywindow.auth.dto.UserDTO;
import com.buddywindow.auth.entity.Address;
import com.buddywindow.auth.entity.Contact;
import com.buddywindow.auth.entity.Phone;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.service.IAuthService;
import com.buddywindow.auth.service.IUserService;


@RestController
@RequestMapping("/api")
public class AuthResource implements IAuthResource{

	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthService authService;
	
	@Override
	public String getUserAuthToken(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("/auth/welcome-msg")
	public String welcomeMessage() {
		return "Hi Welcome to Buddyindow Auth Service .....!";
	}
	
	@PostMapping("/auth/login")
	@Override
	public AuthTokenDTO login(LoginRequestDTO loginRequestDTO) {
		AuthToken authToken = authService.getAuthToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		AuthTokenDTO authTokenDTO = new AuthTokenDTO(authToken.getAccessToken(), authToken.getRefreshToken(), 
				authToken.getTokenType(),authToken.getAccessTokenExpiryInSec(), authToken.getAccessTokenExpiryInSec(), authToken.getCreatedAt());
		return authTokenDTO;
	}

	@Override
	@PostMapping("/auth/token/authenticate")
	public boolean authenticateToken(@RequestHeader("access_token") final String authorizationHeader) {
	    boolean response = authService.validateAuthToken(authorizationHeader);
	    return response;
	}
	
}
