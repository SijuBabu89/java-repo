package com.buddywindow.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

		private String id;
		private String title;
		private String firstName;
		private String middleName;
		private String lastName;
		private String username;
		private String password;
		private ContactDTO contact;
			
	}
