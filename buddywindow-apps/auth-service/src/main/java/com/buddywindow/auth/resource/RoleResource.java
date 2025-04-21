package com.buddywindow.auth.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buddywindow.auth.dto.RightDTO;
import com.buddywindow.auth.dto.RoleDTO;
import com.buddywindow.auth.entity.Right;
import com.buddywindow.auth.entity.Role;
import com.buddywindow.auth.service.IRoleService;
import com.buddywindow.core.service.annotation.RequiresPermission;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("/api")
public class RoleResource implements IRoleResourse{
	
	@Autowired
	private IRoleService roleService;

	@Override
	@GetMapping("/role")
	public RoleDTO getRole(@RequestParam String id) {
		Role role = roleService.getById(id);
		List<Right> rightList = role.getRights();
		List<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		if(!Collections.isEmpty(rightList)) {
			for(Right e: rightList) {
				rightDTOs.add(new RightDTO(e.getName(), e.getDescription()));
			}
		}
		RoleDTO roleDTO = new RoleDTO(role.getName(), role.getDescription(), rightDTOs);
		return roleDTO;
	}
	
	@Override
	@GetMapping("/role/by_name")
	@RequiresPermission("view.role")
	public RoleDTO getRoleByName(@RequestParam String name) {
		Role role = roleService.getByName(name);
		List<Right> rightList = role.getRights();
		List<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		if(!Collections.isEmpty(rightList)) {
			for(Right e: rightList) {
				rightDTOs.add(new RightDTO(e.getName(), e.getDescription()));
			}
		}
		RoleDTO roleDTO = new RoleDTO(role.getName(), role.getDescription(), rightDTOs);
		return roleDTO;
	}

	@Override
	@GetMapping("/role/get_all")
	public List<RoleDTO> getList() {
		List<Role> roleList = roleService.getLits();
		List<RoleDTO> roleDTOList = new ArrayList<RoleDTO>();
		for(Role role: roleList) {
		List<Right> rightList = role.getRights();
		List<RightDTO> rightDTOs = new ArrayList<RightDTO>();
		if(!Collections.isEmpty(rightList)) {
			for(Right e: rightList) {
				rightDTOs.add(new RightDTO(e.getName(), e.getDescription()));
			}
		}
		roleDTOList.add(new RoleDTO(role.getName(), role.getDescription(), rightDTOs));
		}
		return roleDTOList;
	}

	@Override
	@PostMapping("/role")
	public String saveRole(@RequestBody RoleDTO roleDTO) {
		List<RightDTO> rightDTOlist = roleDTO.getRights();
		List<Right> rights = new ArrayList<Right>();
		if (!CollectionUtils.isEmpty(rights)) {
			for (RightDTO rightDTO : rightDTOlist) {
				rights.add(new Right(rightDTO.getName(), rightDTO.getDescription()));
			}
		}
		Role role = new Role(roleDTO.getName(), roleDTO.getDescription(), rights);
		role = roleService.save(role);
		return role.getId();
	}

	@Override
	@PutMapping("/role/add_right")
	public String addRight(@RequestParam String roleId, @RequestParam String rightId) {
		String id = roleService.addRight(roleId, rightId);
		return id;
	}

}
