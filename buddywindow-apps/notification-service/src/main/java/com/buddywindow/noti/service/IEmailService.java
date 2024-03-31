package com.buddywindow.noti.service;

import org.springframework.web.multipart.MultipartFile;

import com.buddywindow.noti.entity.Email;

public interface IEmailService {

	public String sendEmail(Email email, MultipartFile attachments);
}
