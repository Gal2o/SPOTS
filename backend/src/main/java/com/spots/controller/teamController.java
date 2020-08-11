package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;
import com.spots.service.TeamInfoService;



@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@RestController
public class teamController {
	@Autowired
	TeamInfoService teamInfoService;
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
	@PostMapping(value="/team/regist")
	public int registTeam(TeamInfo dto) {
		return teamInfoService.registTeam(dto);
	}
	
	@PostMapping(value="/team/modify")
	public int modifyTeam(TeamInfo dto) {
		return teamInfoService.modifyTeam(dto);
	}
	
	@PostMapping(value="/team/detail")
	public TeamInfo detailTeam(@RequestParam int uid) {
		return teamInfoService.detailTeam(uid);
	}
	
	@GetMapping(value="/team/delete")
	public int deleteTeam(@RequestParam int uid) {
		return teamInfoService.deleteTeam(uid);
	}
	
	@GetMapping(value="/team/list")
	public List<TeamInfo> listTeam() {
		return teamInfoService.listTeam();
	}
	
	@PostMapping(value="/team/userList")
	public List<UserInfo> userList(@RequestParam int uid) {
		return teamInfoService.userList(uid);
	}
	
	@PostMapping(value="/team/applyList")
	public List<ApplyTeamInfo> applyList(@RequestParam int uid) {
		return teamInfoService.applyList(uid);
	}
	
	@PostMapping(value="/team/searchTeam")
	public List<UserInfo> searchTeam(@RequestParam String text) {
		return teamInfoService.searchTeam(text);
	}
	
	@PostMapping(value="/team/searchState")
	public List<UserInfo> searchState(@RequestParam String state_code) {
		state_code = state_code.substring(0,2);
		return teamInfoService.searchState(state_code);
	}
	
	@PostMapping(value="/team/searchCity")
	public List<UserInfo> searchCity(@RequestParam String city_code) {
		city_code = city_code.substring(0,4);
		return teamInfoService.searchCity(city_code);
	}
	
}

