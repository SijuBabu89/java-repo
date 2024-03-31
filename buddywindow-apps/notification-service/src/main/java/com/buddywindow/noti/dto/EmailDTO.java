package com.buddywindow.noti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDTO {
	
	private String from;
	private String to;
	private String subject;
	private String body;
	private String cc;

}
