package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping(value="/stateList")
	public List<SidoInfo> stateList(@RequestParam String city_code) {
		city_code = city_code.substring(0,2);
		return sidoInfoService.stateList(city_code);
	}
	@PostMapping(value="/dongList")
	public List<SidoInfo> dongList(@RequestParam String state_code) {
		state_code = state_code.substring(0,4);
		return sidoInfoService.dongList(state_code);
	}
	
}

