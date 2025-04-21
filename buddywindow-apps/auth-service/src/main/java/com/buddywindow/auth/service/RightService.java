package com.buddywindow.auth.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddywindow.auth.entity.Right;
import com.buddywindow.auth.repository.IRightRepository;

@Service
public class RightService implements IRightService {
	
	@Autowired
	private IRightRepository rightRepository;

	@Override
	public Right getById(String id) {
		Optional<Right> optionalRight = rightRepository.findById(id);
		if (optionalRight.isEmpty()) {
			throw new RuntimeException("Right Not found for :: "+id);
		}
		return optionalRight.get();
	}

	@Override
	public Right getByName(String name) {
		Optional<Right> optionalRight = rightRepository.getByName(name);
		if (optionalRight.isEmpty()) {
			throw new RuntimeException("Right Not found for :: "+name);
		}
		return optionalRight.get();
	}

	@Override
	public List<Right> getList() {
//		return rightRepository.getAllRights();
		return Collections.emptyList();
	}

	@Override
	public Right save(Right right) {
		return rightRepository.save(right);
	}

}
