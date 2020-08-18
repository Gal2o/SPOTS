package com.spots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.UserInfo;
import com.spots.service.EmailService;
import com.spots.service.UserInfoService;


@CrossOrigin(origins = {"*"})
@RestController
public class userController {
		
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	EmailService emailService;

	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
	@PostMapping(value="/user/signUp")
	public int insert(UserInfo dto) {
		return userInfoService.signUp(dto);
	}
	
	@PostMapping(value="/user/findPwdByEmail")
	public UserInfo findPwdByEmail(@PathVariable String email) {
		return userInfoService.findPwdByEmail(email);
	}
	
	@PostMapping(value="/user/modify")
	public int modify(UserInfo dto) {
		return userInfoService.modify(dto);
	}
	
	@PostMapping(value="/user/detail")
	public UserInfo detail(@RequestParam String email) {
		return userInfoService.detail(email);
	}
	
	@PostMapping(value="/user/detail2")
	public UserInfo detail2(@RequestParam int uid) {
		return userInfoService.detail2(uid);
	}
	
	@PostMapping(value="/user/joinTeam")
	public int joinTeam(@RequestParam int uid, @RequestParam int team_uid) {
		return userInfoService.joinTeam(uid, team_uid);
	}
	
	@PostMapping(value="/user/outTeam")
	public int outTeam(@RequestParam int uid, @RequestParam int team_uid) {
		return userInfoService.outTeam(uid, team_uid);
	}
	
	@PostMapping(value="/user/rejectTeam")
	public int rejectTeam(@RequestParam int uid, @RequestParam int team_uid) {
		return userInfoService.rejectTeam(uid, team_uid);
	}

	@PostMapping(value="/user/applyTeam")
	public int applyTeam(ApplyTeamInfo dto) {
		return userInfoService.applyTeam(dto);
	}
	
	@PostMapping(value="/user/email")
	public int sendEmail(@RequestParam String email) {
		return emailService.sendSimpleMessage(email);		
	}
	
}

