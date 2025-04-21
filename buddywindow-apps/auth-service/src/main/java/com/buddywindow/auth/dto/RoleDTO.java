package com.buddywindow.auth.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {

	private String name;
	private String description;
	private List<RightDTO> rights;
}
