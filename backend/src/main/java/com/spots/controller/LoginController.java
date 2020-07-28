package com.spots.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.spots.dto.UserInfo;
import com.spots.service.LoginService;

@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	private static RestTemplate restTemplate;
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	

	
	@GetMapping(value="/") 
	public String loginForm() {
		return "user/login";
	}
		
	//post諛⑹떇�쑝濡� /login�쓣 諛쏆븯�쓣�븣 媛숈씠 蹂대궡吏� id�� password瑜� 媛�吏�怨� �쉶�썝 �솗�씤�븿
	@PostMapping(value="/login")
	public UserInfo login(@RequestParam String email, @RequestParam String password) {
	    
		System.out.println("�뱾�뼱�샂");

		System.out.println(loginService.login(email, password));

        return loginService.login(email, password);
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {

		return "user/login";
	}
}
