package com.spots.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.service.FreeMatchService;
import com.spots.service.TeamMatchService;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class MatchController {

	@Autowired
	TeamMatchService TeamMatch;

	@Autowired
	FreeMatchService FreeMatch;
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}

	// 메인페이지 5개 리스트
	@GetMapping(value = "/TeamMatchMain")
	public List <TMatchInfo> TListMain() {
		System.out.println(TeamMatch.TListMain());
		return TeamMatch.TListMain();
	}

	@GetMapping(value = "/FreeMatchMain")
	public List <FMatchInfo> FListMain() {
		System.out.println(FreeMatch.FListMain());
		return FreeMatch.FListMain();
	}

	// 매칭 페이지 리스트
	@PostMapping(value = "/TeamMatchAll")
	public List <TMatchInfo> TListAll() {
		return TeamMatch.TListAll();
	}

	@PostMapping(value = "/FreeMatchAll")
	public List <FMatchInfo> FListAll() {
		return FreeMatch.FListAll();
	}

	// 방 1개 상세정보
	@PostMapping(value = "/TeamMatchRoom")
	public List<TMatchInfo> TListRoom(@RequestParam int uid) {
		return TeamMatch.TListRoom(uid);
	}
	
	@PostMapping(value = "/FreeMatchRoom")
	public List<FMatchInfo> FListRoom(@RequestParam int uid) {
		return FreeMatch.FListRoom(uid);
	}
	
	// 방만들기 
	@PostMapping(value ="/TRoomCreate")
	public void InsertT(TeamInfo info) {
		
	}
}
