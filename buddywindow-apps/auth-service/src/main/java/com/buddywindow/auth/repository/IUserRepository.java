package com.buddywindow.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.buddywindow.auth.entity.User;

public interface IUserRepository extends MongoRepository<User, String> {
	public Optional<User> getUserByUsernameAndPassword(String username, String password);

}
