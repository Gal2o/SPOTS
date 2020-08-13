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


@CrossOrigin(origins = {"*"})
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
	
	@GetMapping(value="/stateList")
	public List<SidoInfo> stateList() {
		return sidoInfoService.stateList();
	}
	@PostMapping(value="/cityList")
	public List<SidoInfo> cityList(@RequestParam String state_code) {
		state_code = state_code.substring(0,2);
		return sidoInfoService.cityList(state_code);
	}
	@PostMapping(value="/dongList")
	public List<SidoInfo> dongList(@RequestParam String city_code) {
		city_code = city_code.substring(0,4);
		return sidoInfoService.dongList(city_code);
	}
	
}

