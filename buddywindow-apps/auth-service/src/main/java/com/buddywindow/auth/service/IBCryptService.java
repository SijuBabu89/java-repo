package com.buddywindow.auth.service;

public interface IBCryptService {

	public String hashPassword(String password);
	public boolean checkPassword(String password, String passed_password);
}
