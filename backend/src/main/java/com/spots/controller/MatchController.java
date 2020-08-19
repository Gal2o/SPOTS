package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.service.FreeMatchService;
import com.spots.service.TeamMatchService;

@CrossOrigin(origins = { "*" })
@RestController
public class MatchController {

   @Autowired
   TeamMatchService TeamMatch;

   @Autowired
   FreeMatchService FreeMatch;

   @ExceptionHandler
   public ModelAndView handler(Exception ex) {
      ModelAndView mav = new ModelAndView("error/errorHandler");
      mav.addObject("msg", ex.getMessage());
      ex.printStackTrace();
      return mav;
   }

   // 메인페이지 GET (Created)
   @GetMapping(value = "/TeamMatchAll")
   public List<TMatchInfo> TListMain() {
      //System.out.println(TeamMatch.TListMain());
      return TeamMatch.TListMain();
   }

   @GetMapping(value = "/FreeMatchAll")
   public List<FMatchInfo> FListMain() {
      //System.out.println(FreeMatch.FListMain());
      return FreeMatch.FListMain();
   }

   // 매칭 페이지 리스트
   @PostMapping(value = "/TeamMatchAll")
   public List<TMatchInfo> TListAll(
         @RequestParam String doe, @RequestParam String si,
         @RequestParam String dong, @RequestParam String word) {

      if(doe.equals("null")) doe = null;
      else doe = doe.substring(0,2);
      
      if(si.equals("null")) si = null;
      else si = si.substring(0,4);
      
      if(dong.equals("null"))
         dong = null;
      
      if(word.equals("null"))
         word = null;
      
      return TeamMatch.TListAll(doe, si, dong, word);
   }

   @PostMapping(value = "/FreeMatchAll")
   public List<FMatchInfo> FListAll(
         @RequestParam String doe, @RequestParam String si,
         @RequestParam String dong, @RequestParam String word) {
      
      if(doe.equals("null")) doe = null;
      else doe = doe.substring(0,2);
      
      if(si.equals("null")) si = null;
      else si = si.substring(0,4);
      
      if(dong.equals("null"))
         dong = null;
      
      if(word.equals("null"))
         word = null;
      
      //System.out.println(FreeMatch.FListAll(doe, si, dong, word));
      return FreeMatch.FListAll(doe, si, dong, word);
   }

   // 방 1개 상세정보 UID
   @PostMapping(value = "/TeamMatchRoom")
   public List<TMatchInfo> TListRoom(@RequestParam int uid) {
      return TeamMatch.TListRoom(uid);
   }

   @PostMapping(value = "/FreeMatchRoom")
   public List<FMatchInfo> FListRoom(@RequestParam int uid) {
      return FreeMatch.FListRoom(uid);
   }

   // 방 중복 체크 head_UID
   @PostMapping(value = "/TRoomCheck")
   public List<TMatchInfo> CheckT(@RequestParam int head_uid) {
      return TeamMatch.TRoomCheck(head_uid);
   }

   @PostMapping(value = "/FRoomCheck")
   public List<FMatchInfo> CheckF(@RequestParam int head_uid) {
      return FreeMatch.FRoomCheck(head_uid);
   }

   // 방만들기
   @PostMapping(value = "/TRoomCreate")
   public List<TMatchInfo> InsertT(TMatchInfo info) {
      TeamMatch.TRoomCreate(info);

      return TeamMatch.TRoomCheck(info.getHead_uid());
   }

   @PostMapping(value = "/FRoomCreate")
   public List<FMatchInfo> InsertF(FMatchInfo info) {
      FreeMatch.FRoomCreate(info);

      //System.out.println(FreeMatch.FListRoom(info.getHead_uid()));
      return FreeMatch.FRoomCheck(info.getHead_uid());
   }

   // 방 정보 수정 (미완료)
   @PostMapping(value = "/TRoomModify")
   public List<TMatchInfo> UpdateT(TMatchInfo info) {
      TeamMatch.TRoomUpdate(info);

      return TeamMatch.TListRoom(info.getUid());
   }

   @PostMapping(value = "/FRoomModify")
   public List<FMatchInfo> UpdateF(FMatchInfo info) {
      FreeMatch.FRoomUpdate(info);

      return FreeMatch.FListRoom(info.getUid());
   }
   
   @PostMapping(value = "/matchEnd")
   public int matchEnd(@RequestParam int uid, @RequestParam int mvp) {

      return FreeMatch.matchEnd(uid, mvp);
   }
   
   @PostMapping(value = "/TRoomJoin")
   public int TRoomJoin(@RequestParam int uid, @RequestParam int room_uid) {
      return TeamMatch.TRoomJoin(uid, room_uid);
   }
}