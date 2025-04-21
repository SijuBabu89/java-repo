package com.buddywindow.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.buddywindow.auth.entity.Right;

@Repository
public interface IRightRepository extends MongoRepository<Right, String> {

	public Optional<Right> getByName(String name);
	public Optional<Right> getById(String id);
//	public List<Right> getAllRights();
	
}
