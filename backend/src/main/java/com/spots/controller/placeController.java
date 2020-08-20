package com.spots.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.PlaceInfo;
import com.spots.dto.UserInfo;
import com.spots.service.PlaceInfoService;


@CrossOrigin(origins = {"*"})
@RestController
public class placeController {
   @Autowired
   PlaceInfoService placeInfoService;
   
   @ExceptionHandler
   public ModelAndView handler(Exception ex) {
      ModelAndView  mav = new ModelAndView("error/errorHandler");
      mav.addObject("msg", ex.getMessage());
      ex.printStackTrace();
      return mav;
   }
   
   @GetMapping(value="/place/list")
   public List<PlaceInfo> placeList() {
      return placeInfoService.placeList();
   }
   
   @PostMapping(value="/place/detail")
   public PlaceInfo placeDetail(@RequestParam int uid) {
      //System.out.print(uid);
      return placeInfoService.placeDetail(uid);
   }
   
   @PostMapping(value="/place/regist")
   public int placeRegist(PlaceInfo dto) {
      return placeInfoService.placeRegist(dto);
   }
   

   
}
