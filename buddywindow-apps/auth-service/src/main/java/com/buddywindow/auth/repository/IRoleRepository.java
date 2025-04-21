package com.buddywindow.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.buddywindow.auth.entity.Role;

@Repository
public interface IRoleRepository extends MongoRepository<Role, String> {

	public Optional<Role> getByName(String username);
	public Optional<Role> getById(String id);
//	public List<Role> getAllRoles();
	
}
