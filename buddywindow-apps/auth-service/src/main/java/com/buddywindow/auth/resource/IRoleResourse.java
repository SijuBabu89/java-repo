package com.buddywindow.auth.resource;

import java.util.List;

import com.buddywindow.auth.dto.RoleDTO;

public interface IRoleResourse {

	public RoleDTO getRole(String id);
	public RoleDTO getRoleByName(String name);
	public List<RoleDTO> getList();
	public String saveRole(RoleDTO role);
	public String addRight(String roleId, String rightId);
}
