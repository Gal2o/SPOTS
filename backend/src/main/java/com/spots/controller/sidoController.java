package com.spots.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.SidoInfo;
import com.spots.service.SidoInfoService;


@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@RestController
public class sidoController {
	@Autowired
	SidoInfoService sidoInfoService;
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
	@PostMapping(value="/cityList")
	public <List>SidoInfo cityList() {
		return sidoInfoService.cityList();
	}
	@PostMapping(value="/stateList")
	public <List>SidoInfo stateList() {
		return sidoInfoService.stateList();
	}
	@PostMapping(value="/dongList")
	public <List>SidoInfo dongList() {
		return sidoInfoService.dongList();
	}
	
}

