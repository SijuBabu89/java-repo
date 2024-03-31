package com.buddywindow.auth.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddywindow.auth.domain.AuthToken;
import com.buddywindow.auth.dto.AuthTokenDTO;
import com.buddywindow.auth.dto.LoginRequestDTO;
import com.buddywindow.auth.entity.Address;
import com.buddywindow.auth.entity.Contact;
import com.buddywindow.auth.entity.Phone;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.service.IAuthService;
import com.buddywindow.auth.service.IUserService;
import com.buddywindow.auth.util.JWTUtil;


@RestController
@RequestMapping("/api/auth")
public class AuthResource implements IAuthResource{

//	@Autowired
//	private JWTUtil jwtUtil;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAuthService authService;
	
	@Override
	public String getUserAuthToken(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@GetMapping("/welcome-msg")
	public String welcomeMessage() {
		return "Hi Welcome to Buddyindow Auth Service .....!";
	}
	
	@PostMapping("/login")
	@Override
	public AuthTokenDTO login(LoginRequestDTO loginRequestDTO) {
		AuthToken authToken = authService.getAuthToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
		AuthTokenDTO authTokenDTO = new AuthTokenDTO(authToken.getAccessToken(), authToken.getRefreshToken(), 
				authToken.getTokenType(),authToken.getAccessTokenExpiryInSec(), authToken.getAccessTokenExpiryInSec(), authToken.getCreatedAt());
		return authTokenDTO;
	}
	
	@GetMapping("/user")
	public String createUser() {
		User user = new User();
		user.setFirstName("Samual");
		user.setMiddleName("Galilo");
		user.setLastName("Martin");
		user.setTitle("Er.");
		user.setUsername("samualM");
		Phone phone = new Phone();
		phone.setHome("9895951345");
		phone.setWork("9895951345");
		Address addr = new Address();
		addr.setAddr1("#997 Flat Ocean View");
		addr.setAddr2("Garfee Street");
		addr.setCity("Manuffa");
		addr.setCountry("Dabba");
		addr.setState("Cliff");
		addr.setStreet("Mardona");
		addr.setZip("787899");	
		Contact contact = new Contact();
		contact.setAddress(addr);
		contact.setPhone(phone);
		contact.setAddress(addr);
		contact.setEmail("samual.m@gmail.com");
		user.setContact(contact);
		user = userService.createUser(user);
		System.out.println(user.getId());
		return user.getId();
	}
	
	
}
