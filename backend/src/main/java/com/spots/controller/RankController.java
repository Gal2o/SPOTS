package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.service.RankService;
import com.spots.service.TeamInfoService;

@CrossOrigin(origins = { "http://localhost:3000" })
@Controller
public class RankController {

	@Autowired
	RankService TeamMatch;

	
	@GetMapping(value="/TeamMatch/win")
	public int TeamMatchWin(TMatchInfo dto) {
		System.out.println("이긴경우");
		return TeamMatch.TWin(dto);
	}
	
	@GetMapping(value="/TeamMatch/lose")
	public int TeamMatchLose(TMatchInfo dto) {
		System.out.println("진경우");
		return TeamMatch.TLose(dto);
	}
	
	@GetMapping(value="/TeamMatch/draw")
	public int TeamMatchDraw(TMatchInfo dto) {
		System.out.println("비긴경우");
		return TeamMatch.TDraw(dto);
	}
	
	@GetMapping(value="/rank")
	public List<TeamInfo> listTeam() {
		return TeamMatch.listTeam();
	}

}
