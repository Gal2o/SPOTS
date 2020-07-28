package com.spots.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.service.FreeMatchService;
import com.spots.service.TeamMatchService;

@CrossOrigin(origins = { "http://localhost:3000" })
@Controller
public class MatchController {

	@Autowired
	TeamMatchService TeamMatch;

	@Autowired
	FreeMatchService FreeMatch;

	@PostMapping(value = "/TeamMatchMain")
	public TMatchInfo TListMain() {
		System.out.println("들어옴");
		return TeamMatch.TListMain();
	}

	@PostMapping(value = "/FreeMatchMain")
	public FMatchInfo FListMain() {
		System.out.println("들어옴");
		return FreeMatch.FListMain();
	}

	@PostMapping(value = "/TeamMatchAll")
	public TMatchInfo TListAll() {
		return TeamMatch.TListAll();
	}

	@PostMapping(value = "/FreeMatchAll")
	public FMatchInfo FListAll() {
		return FreeMatch.FListAll();
	}

	@PostMapping(value = "/TeamMatchRoom")
	public TMatchInfo TListRoom(@RequestParam int uid, @RequestParam boolean isLogined) {
		if (isLogined)
			return null;
		else
			return TeamMatch.TListRoom(uid);
	}
	
	@PostMapping(value = "/FreeMatchRoom")
	public FMatchInfo FListRoom(@RequestParam int uid, @RequestParam boolean isLogined) {
		if (isLogined)
			return null;
		else
			return FreeMatch.FListRoom(uid);
	}
}
