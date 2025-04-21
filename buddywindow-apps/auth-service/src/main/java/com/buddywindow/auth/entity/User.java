package com.buddywindow.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {

	@Id
	private String id;
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailId;
	private Contact contact;
	private String username;
	private String password;
	private Role role;
	
}
