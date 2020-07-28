package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value="/cityList")
	public List<SidoInfo> cityList() {
		System.out.println("asd");
		return sidoInfoService.cityList();
	}
	@GetMapping(value="/stateList")
	public List<SidoInfo> stateList() {
		return sidoInfoService.stateList();
	}
	@GetMapping(value="/dongList")
	public List<SidoInfo> dongList() {
		return sidoInfoService.dongList();
	}
	
}

