package com.spots.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.UserInfo;
import com.spots.service.LoginService;



@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
	//get방식으로 /login을 받으면 application.properties에서 지정한 경로인 /WEB-INF/views/의 loginForm.jsp로 보내주는 코드
//	@GetMapping(value="/login")
//	public String loginForm() {
//		return "loginForm";
//	}
	
	@GetMapping(value="/") 
	public String loginForm() {
		return "user/login";
	}
	
	@GetMapping(value="/login") 
	public String test() {
		System.out.println("들어옴3sp");
		return "들어옴";
	}
	
	//post방식으로 /login을 받았을때 같이 보내진 id와 password를 가지고 회원 확인함
	@PostMapping(value="/login")
	public UserInfo login(@RequestParam Map<String, String> param) {
		System.out.println("들어옴");
		System.out.println(param.get("email"));
		String email =  param.get("email");
		String password = param.get("password");

		return loginService.login(email, password);

	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {

		return "user/login";
	}
}
