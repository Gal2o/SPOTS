package com.spots.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.UserInfo;
import com.spots.service.UserInfoService;


@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@RestController
public class userController {
	@Autowired
	UserInfoService userInfoService;
	
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
	
	@GetMapping(value="/user/findPwdByEmail")
	public UserInfo findPwdByEmail(@PathVariable String email) {
		return userInfoService.findPwdByEmail(email);
	}
	
	@PutMapping(value="/user/modify")
	public int modify(UserInfo dto) {
		return userInfoService.modify(dto);
	}
	
	@GetMapping(value="/user/detail")
	public UserInfo detail(@PathVariable String email) {
		return userInfoService.detail(email);
	}
	
	@GetMapping(value="/user/joinTeam")
	public int joinTeam(@RequestParam Map<String, Integer> param) {
		int uid= param.get("uid");
		int team_uid= param.get("team_uid");
		return userInfoService.joinTeam(uid, team_uid);
	}
	
	@GetMapping(value="/user/outTeam")
	public int outTeam(@PathVariable int uid) {
		return userInfoService.outTeam(uid);
	}

	
}
