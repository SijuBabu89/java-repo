package com.buddywindow.auth.service;

import java.util.List;

import com.buddywindow.auth.entity.Role;

public interface IRoleService {

	public Role getById(String id);
	public Role getByName(String name);
	public List<Role> getLits();
	public Role save(Role role);
	public String addRight(String roleId, String rightId);
}
