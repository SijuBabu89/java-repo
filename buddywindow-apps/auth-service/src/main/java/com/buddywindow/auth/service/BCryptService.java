package com.buddywindow.auth.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BCryptService implements IBCryptService{

	@Override
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean checkPassword(String password, String hashedPassword) {
		return BCrypt.checkpw(password, hashedPassword);
	}

}
