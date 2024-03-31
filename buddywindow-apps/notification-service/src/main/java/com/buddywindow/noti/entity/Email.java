package com.buddywindow.noti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email {
	
	private String from;
	private String to;
	private String subject;
	private String body;
	private String cc;

}