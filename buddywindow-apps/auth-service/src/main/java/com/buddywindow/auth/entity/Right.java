package com.buddywindow.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Right")
public class Right {

	@Id
	private String id;
	private String name;
	private String description;
	
	public Right(String name, String description) {
		this.name = name;
		this.description = description;
	}
		
}
