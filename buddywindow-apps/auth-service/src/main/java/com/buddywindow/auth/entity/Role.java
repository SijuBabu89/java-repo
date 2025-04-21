package com.buddywindow.auth.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Role")
public class Role {

	@Id
	private String id;
	private String name;
	private String description;
	private List<Right> rights;
	
	public Role(String name, String description, List<Right> rights) {
		this.name = name;
		this.description = description;
		this.rights = rights;
	}
}
