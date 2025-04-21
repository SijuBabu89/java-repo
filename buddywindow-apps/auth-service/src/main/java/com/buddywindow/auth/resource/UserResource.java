package com.buddywindow.auth.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buddywindow.auth.dto.AddressDTO;
import com.buddywindow.auth.dto.ContactDTO;
import com.buddywindow.auth.dto.PhoneDTO;
import com.buddywindow.auth.dto.UserDTO;
import com.buddywindow.auth.entity.Address;
import com.buddywindow.auth.entity.Contact;
import com.buddywindow.auth.entity.Phone;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.service.IUserService;
import com.buddywindow.core.service.annotation.RequiresPermission;

@RestController
@RequestMapping("/api")
public class UserResource implements IUserResource{

	@Autowired
	private IUserService userService;

	@Override
	@GetMapping("/user")
	public UserDTO getUser(@RequestParam String id) {
		User user = userService.getUserById(id);
		UserDTO dto = new UserDTO();
		dto.setFirstName(user.getFirstName());
		dto.setMiddleName(user.getMiddleName());
		dto.setLastName(user.getLastName());
		return dto;
	}
	
	@Override
	@GetMapping("/user/by_username")
	@RequiresPermission("view.user")
	public UserDTO getUserByUsername(@RequestParam String username) {
		User user = userService.getUserByUsername(username);
		UserDTO dto = new UserDTO();
		dto.setFirstName(user.getFirstName());
		dto.setMiddleName(user.getMiddleName());
		dto.setLastName(user.getLastName());
		return dto;
	}
	
	@PostMapping("/user")
	@Override
	public String createUser(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setMiddleName(userDTO.getMiddleName());
		user.setLastName(userDTO.getLastName());
		user.setTitle(userDTO.getTitle());
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		ContactDTO contactDTO = userDTO.getContact();
		AddressDTO addressDTO = contactDTO.getAddress();
		PhoneDTO phoneDTO = contactDTO.getPhone();
		Address address = new Address(addressDTO.getAddr1(), addressDTO.getAddr2(), addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getZip(), addressDTO.getCountry());
		Phone phone = new Phone(phoneDTO.getHome(), phoneDTO.getWork());
		Contact contact = new Contact(contactDTO.getEmail(), address, phone);
		user.setContact(contact);
		user = userService.createUser(user);
		return user.getId();
	}

	@Override
	@GetMapping("/user/get_all")
	public List<UserDTO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping("/user/add_role")
	public String addRole(@RequestParam String userId, @RequestParam String roleId) {
		String id = userService.addRole(userId, roleId);
		return id;
	}

}
