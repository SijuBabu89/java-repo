package com.buddywindow.noti.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.buddywindow.noti.dto.EmailDTO;
import com.buddywindow.noti.entity.Email;
import com.buddywindow.noti.service.IEmailService;

@RestController
public class EmailResource implements IEmailResource{

	@Autowired
	private IEmailService emailService;
	
	@Override
	public String sendEmail(EmailDTO emailDTO, MultipartFile file) {
		Email email = new Email();
		email.setBody(emailDTO.getBody());
		email.setCc(emailDTO.getCc());
		email.setFrom(emailDTO.getFrom());
		email.setSubject(emailDTO.getSubject());
		email.setTo(emailDTO.getTo());
		return emailService.sendEmail(email, file);
		
	}

	@Override
	public String welcome() {
		return "Hi, Welcome to notification app";
	}

}
