package com.buddywindow.auth.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.buddywindow.auth.entity.Right;
import com.buddywindow.auth.entity.Role;
import com.buddywindow.auth.repository.IRightRepository;
import com.buddywindow.auth.repository.IRoleRepository;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private IRightRepository rightRepository;
	
	@Override
	public Role getById(String id) {
		Optional<Role> optionalRole = roleRepository.findById(id);
		if (optionalRole.isEmpty()) {
			throw new RuntimeException("Role not found for :: "+id);
		}
		return optionalRole.get();
	}

	@Override
	public Role getByName(String name) {
		Optional<Role> optionalRole = roleRepository.getByName(name);
		if (optionalRole.isEmpty()) {
			throw new RuntimeException("Role not found for :: "+name);
		}
		return optionalRole.get();
	}

	@Override
	public List<Role> getLits() {
//		return roleRepository.getAllRoles();
		return Collections.emptyList();
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public String addRight(String roleId, String rightId) {
		Optional<Role> roleOption = roleRepository.findById(roleId);
		Optional<Right> rightOption = rightRepository.findById(rightId);
		if(roleOption.isEmpty() || rightOption.isEmpty()) {
			throw new RuntimeException("Role/Right not found");
		}
		Role role = roleOption.get();
		List<Right> rights = role.getRights();
		if(CollectionUtils.isEmpty(rights)) {
			rights = new ArrayList<Right>();
		}
		rights.add(rightOption.get());
		role.setRights(rights);
		roleRepository.save(role);
		return role.getId();
	}

}
