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
	
	@GetMapping(value="/team/detail")
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
	
	@GetMapping(value="/team/applyList")
	public List<ApplyTeamInfo> applyList(@RequestParam int team_uid) {
		return teamInfoService.applyList(team_uid);
	}
	
}

