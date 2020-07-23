package com.spots.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.service.FreeMatchService;
import com.spots.service.TeamMatchService;

@Controller
public class MatchController {

	@Autowired
	TeamMatchService TeamMatch;
	
	@Autowired
	FreeMatchService FreeMatch;
	
	@PostMapping(value="/TeamMatch")
	public String TeamMatchList(HttpSession session){
		TMatchInfo TMatch = TeamMatch.TList();
		
		session.setAttribute("TeamMatch", TMatch);
		return "index";
	}
	
	@PostMapping(value="/FreeMatch")
	public String FreeMatchList(HttpSession session){
		FMatchInfo FMatch = FreeMatch.FList();
		
		session.setAttribute("FreeMatch", FMatch);
		return "index";
	}
}
