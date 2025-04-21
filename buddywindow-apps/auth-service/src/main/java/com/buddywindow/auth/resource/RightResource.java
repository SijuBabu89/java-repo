package com.buddywindow.auth.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddywindow.auth.dto.RightDTO;
import com.buddywindow.auth.entity.Right;
import com.buddywindow.auth.service.IRightService;
import com.buddywindow.core.service.annotation.RequiresPermission;

@RestController
@RequestMapping("/api")
public class RightResource implements IRightResource{

	@Autowired
	private IRightService rightService;
	
	@Override
	@GetMapping("/right")
	public RightDTO getRight(String rightId) {
		Right right = rightService.getById(rightId);
		return new RightDTO(right.getName(), right.getDescription());
	}

	@Override
	@GetMapping("/right/by_name")
	@RequiresPermission("view.right")
	public RightDTO getRightByName(String name) {
		Right right = rightService.getByName(name);
		return new RightDTO(right.getName(), right.getDescription());
	}
	
	@Override
	@GetMapping("/right/get_all")
	public List<RightDTO> getList() {
		List<Right> rights = rightService.getList();
		List<RightDTO> rightDTOList = rights.stream()
				.map(right -> new RightDTO(right.getName(), right.getDescription())).collect(Collectors.toList());
		return rightDTOList;
	}

	@Override
	@PostMapping("/right")
	public String createRight(@RequestBody RightDTO rightDTO) {
		Right right = new Right(rightDTO.getName(), rightDTO.getDescription());
		right = rightService.save(right);
		return right.getId();
	}
}
