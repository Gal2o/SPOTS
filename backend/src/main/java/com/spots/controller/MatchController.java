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

@CrossOrigin(origins = {"http://192.168.0.30:3000", "http://localhost:3000"})
@Controller
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
	
	//방 중복 체크
	@PostMapping(value ="/TRoomCheck")
	public boolean CheckT(@RequestParam int uid) {
		List<TMatchInfo> info = TeamMatch.TListRoom(uid);
		
		if(info.isEmpty())
			return false;
		else
			return true;
	}
	
	@PostMapping(value ="/FRoomCheck")
	public boolean CheckF(@RequestParam int uid) {
		List<FMatchInfo> info = FreeMatch.FListRoom(uid);
		
		if(info.isEmpty())
			return false;
		else
			return true;
	}
	
	// 방만들기 
	@PostMapping(value ="/TRoomCreate")
	public List<TMatchInfo> InsertT(TMatchInfo info) {
		TeamMatch.TRoomCreate(info);
		
		return TeamMatch.TListRoom(info.getUid());
	}
	
	@PostMapping(value="/FRoomCreate")
	public List<FMatchInfo> InsertF(FMatchInfo info) {
		FreeMatch.FRoomCreate(info);
		
		return FreeMatch.FListRoom(info.getUid());
	}
	
	//방 정보 수정 (미완료)
	@PostMapping(value="/TRoomModify")
	public List<TMatchInfo> UpdateT(TMatchInfo info){
		TeamMatch.TRoomUpdate(info);
		
		return TeamMatch.TListRoom(info.getUid());
	}
	
	@PostMapping(value="/FRoomModify")
	public List<FMatchInfo> UpdateF(FMatchInfo info){
		FreeMatch.FRoomUpdate(info);
		
		return FreeMatch.FListRoom(info.getUid());
	}
	
	
	
	
	
}
