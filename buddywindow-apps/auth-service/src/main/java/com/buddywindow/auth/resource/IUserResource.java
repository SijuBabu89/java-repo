package com.buddywindow.auth.resource;

import java.util.List;

import com.buddywindow.auth.dto.UserDTO;

public interface IUserResource {

	public UserDTO getUser(String userId);
	public UserDTO getUserByUsername(String username);
	public String createUser(UserDTO user);
	public List<UserDTO> getList();
	public String addRole(String userId, String roleId);
	
}
