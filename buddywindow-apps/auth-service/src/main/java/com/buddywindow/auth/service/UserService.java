package com.buddywindow.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.entity.User;
import com.buddywindow.auth.repository.IUserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public User getUserById(Long id) {
		Optional<User> user= userRepository.findById("");
		if(user.isPresent()) {
			return user.get();
		} else {
			throw new RuntimeException("User not found");
		}
	}
	
	@Override
	public User createUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		Optional<User> userOptional = userRepository.getUserByUsernameAndPassword(username, password);
		if(userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		return userOptional.get();
	}

}
