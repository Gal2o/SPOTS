package com.spots.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.UserInfo;
import com.spots.service.LoginService;

@Controller
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
	
	//post방식으로 /login을 받았을때 같이 보내진 id와 password를 가지고 회원 확인함
	@PostMapping(value="/login")
	public String login(String email, String password, HttpSession session) {
		System.out.println("들어옴");
		// #1
		UserInfo userinfo = loginService.login(email, password);
		
		
		//해당 회원 없을시 result에 fail을 담아서 login을 호출한다
		if( userinfo == null ) {
			return "user/login";
		}else {
			session.setAttribute("userinfo", userinfo);
			return "index";
		}
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {

		return "user/login";
	}
}
