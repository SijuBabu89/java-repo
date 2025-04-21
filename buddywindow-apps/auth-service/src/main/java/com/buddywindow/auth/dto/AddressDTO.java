package com.buddywindow.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	private String addr1;
	private String addr2;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
}
