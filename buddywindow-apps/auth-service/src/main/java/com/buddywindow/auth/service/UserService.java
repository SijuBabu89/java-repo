package com.buddywindow.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.entity.Role;
import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.repository.IRoleRepository;
import com.buddywindow.auth.repository.IUserRepository;
import com.buddywindow.core.service.entity.UserProfile;
import com.buddywindow.core.service.request.RequestContext;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IRoleRepository roleRepository;
	@Autowired
	private IBCryptService cryptService;
	
	
	@Override
	public User getUserById(String id) {
		
		Optional<User> user= userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new RuntimeException("User not found");
		}
	}
	
	@Override
	public User createUser(User user) {
		user.setPassword(user.getPassword() != null ? cryptService.hashPassword(user.getPassword()) : "");
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		Optional<User> userOptional = userRepository.getUserByUsername(username);
		if(userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		User user = userOptional.get();
		if (!cryptService.checkPassword(password, user.getPassword())) {
			throw new RuntimeException("username or password not matching");
		}
		return userOptional.get();
	}

	@Override
	public User getUserByUsername(String username) {
		UserProfile userProfile = RequestContext.getUserProfile();
		System.out.println(" User Profile ::::: "+userProfile);
		Optional<User> userOptional = userRepository.getUserByUsername(username);
		if(userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return userOptional.get();
	}

	@Override
	public String addRole(String userId, String roleId) {
		Optional<User> userOption = userRepository.findById(userId);
		Optional<Role> roleOption = roleRepository.findById(roleId);
		if(userOption.isEmpty() || roleOption.isEmpty()) {
			throw new RuntimeException("User/Role not found");
		}
		User user = userOption.get();
		user.setRole(roleOption.get());
		user = userRepository.save(user);
		return user.getId();
	}

}
