package com.spots.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender emailSender;
	
    public int sendSimpleMessage(String email) {
    	Random r = new Random();
    	int dice = r.nextInt(89999) + 10000;
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);//보낼 대상
        message.setSubject("SPOTs 인증번호입니다");//제목
        message.setText("SPOTs입니다\n 인증번호 : " + dice + "\n 인증번호 확인 창에 입력해주세요");//내용
    	try{//예외처리
        	emailSender.send(message);
        }catch(MailException es){
        	es.printStackTrace();
            throw new IllegalArgumentException();
        }
    	return dice;
    }
  
}