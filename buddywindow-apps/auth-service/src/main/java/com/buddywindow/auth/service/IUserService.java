package com.buddywindow.auth.service;

import com.buddywindow.auth.entity.User;

public interface IUserService {

	public User getUserById(Long id);
	public User createUser(User user);
	public User getUserByUsernameAndPassword(String username, String password);
}
