package com.buddywindow.noti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.buddywindow.noti.entity.Email;

@Component
public class EmailService implements IEmailService{

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public String sendEmail(Email email, MultipartFile attachments) {		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(email.getFrom());
		mailMessage.setTo(email.getTo());
		mailMessage.setText(email.getBody());
		mailMessage.setSubject(email.getSubject());
		mailMessage.setCc(email.getCc());
		javaMailSender.send(mailMessage);
		return "Sent";		
	}
}
