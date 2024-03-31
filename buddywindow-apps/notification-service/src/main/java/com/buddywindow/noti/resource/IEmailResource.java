package com.buddywindow.noti.resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.buddywindow.noti.dto.EmailDTO;

@RequestMapping("api/noti")
public interface IEmailResource {

	@PostMapping("/send/email")
	public String sendEmail(@RequestBody(required = true) EmailDTO emailDTO,  @RequestParam(name = "file", required = false) MultipartFile file );

	@GetMapping("welcome-msg")
	public String welcome();
}
