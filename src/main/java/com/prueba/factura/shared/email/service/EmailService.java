package com.prueba.factura.shared.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String toMail,
			String subject,
			String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ajota.3794@gmail.com");
		message.setTo(toMail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		System.out.println("mensaje enviado");
	}
}
