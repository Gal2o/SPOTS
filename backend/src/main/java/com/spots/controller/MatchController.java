package com.spots.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.service.FreeMatchService;
import com.spots.service.TeamMatchService;

@CrossOrigin(origins = {"http://localhost:3000"})
@Controller
public class MatchController {

	@Autowired
	TeamMatchService TeamMatch;
	
	@Autowired
	FreeMatchService FreeMatch;
	
	@PostMapping(value="/TeamMatch")
	public TMatchInfo TeamMatchList(){
		System.out.println("들어옴");
		return TeamMatch.TList();
	}
	
	@PostMapping(value="/FreeMatch")
	public FMatchInfo FreeMatchList() {
		System.out.println("들어옴");
		return FreeMatch.FList();
	}
}

