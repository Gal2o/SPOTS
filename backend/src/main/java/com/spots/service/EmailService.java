package com.spots.service;

import org.springframework.mail.SimpleMailMessage;



public interface EmailService {

	public int sendSimpleMessage(String email);
	
}
