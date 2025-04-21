package com.buddywindow.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.buddywindow.auth.entity.User;

@Repository
public interface IUserRepository extends MongoRepository<User, String> {
	public Optional<User> getUserByUsernameAndPassword(String username, String password);
	public Optional<User> getUserByUsername(String username);
	//public List<User> getAllUsers(String username);

}
