package com.buddywindow.auth.service;

import com.buddywindow.auth.entity.User;

public interface IUserService {

	public User getUserById(String id);
	public User createUser(User user);
	public User getUserByUsernameAndPassword(String username, String password);
	public User getUserByUsername(String username);
	public String addRole(String userId, String roleId);
}
