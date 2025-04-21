package com.buddywindow.auth.resource;

import java.util.List;

import com.buddywindow.auth.dto.RightDTO;

public interface IRightResource {

	public RightDTO getRight(String rightId);
	public RightDTO getRightByName(String rightId);
	public List<RightDTO> getList();
	public String createRight(RightDTO rightDTO);
}
