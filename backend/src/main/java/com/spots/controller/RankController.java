package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;
import com.spots.service.RankService;

@CrossOrigin(origins = {"*"})
@RestController
public class RankController {

	@Autowired
	RankService Rank;

	@GetMapping(value="/TeamMatch/win")
	public int TeamMatchWin(TMatchInfo dto) {
		//System.out.println("이긴경우");
		return Rank.TWin(dto);
	}
	
	@GetMapping(value="/TeamMatch/lose")
	public int TeamMatchLose(TMatchInfo dto) {
		//System.out.println("진경우");
		return Rank.TLose(dto);
	}
	
	@GetMapping(value="/TeamMatch/draw")
	public int TeamMatchDraw(TMatchInfo dto) {
		//System.out.println("비긴경우");
		return Rank.TDraw(dto);
	}
	
	@GetMapping(value="/rank")
	public List<TeamInfo> listTeam() {
		return Rank.listTeam();
	}

	@GetMapping(value="/rank/free")
	public List<UserInfo> listFree() {
		return Rank.listFree();
	}
}
