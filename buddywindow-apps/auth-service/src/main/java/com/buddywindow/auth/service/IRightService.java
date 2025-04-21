package com.buddywindow.auth.service;

import java.util.List;

import com.buddywindow.auth.entity.Right;

public interface IRightService {

	public Right getById(String id);
	public Right getByName(String name);
	public List<Right> getList();
	public Right save(Right right);
}
