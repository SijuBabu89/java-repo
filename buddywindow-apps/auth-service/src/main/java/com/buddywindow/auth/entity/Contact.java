package com.buddywindow.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	private String email;
	private Address address;
	private Phone phone;
	
}
