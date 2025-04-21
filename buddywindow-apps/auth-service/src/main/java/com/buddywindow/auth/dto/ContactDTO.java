package com.buddywindow.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

	private String email;
	private AddressDTO address;
	private PhoneDTO phone;
	
}
