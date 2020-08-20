package com.spots.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.KakaoPayReadyVO;
import com.spots.dto.UserInfo;
import com.spots.service.UserInfoService;

@CrossOrigin(origins = {"*"})
@RestController
public class KakaoPayController {
	
	String aws = "http://i3a408.p.ssafy.io";
	
	@ExceptionHandler
	public ModelAndView handler(Exception ex) {
		ModelAndView  mav = new ModelAndView("error/errorHandler");
		mav.addObject("msg", ex.getMessage());
		ex.printStackTrace();
		return mav;
	}
	
	@PostMapping(value="/kakaoPay")
	public String login(@RequestParam String price,@RequestParam int team_entry_uid, @RequestParam int uid, @RequestParam int positionnum,  @RequestParam int room_uid) {
		//System.out.println("통신"); 
		KakaoPayReadyVO kakaoPayReadyVO = new KakaoPayReadyVO();
		RestTemplate restTemplate = new RestTemplate();
	    String HOST = "https://kapi.kakao.com";

	    // 서버로 요청할 Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "9270210e4065b410d1a6982e70e3713e");
	    headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+";charset=UTF-8");
	    
	    
	    // 서버로 요청할 Body
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id", "1001");
	    params.add("partner_user_id", "zxcsf");
	    params.add("item_name", "축구장 대여료");
	    params.add("quantity", "1");
	    params.add("total_amount", price);
	    params.add("tax_free_amount", "0");
	    params.add("approval_url", "http://localhost:3000/#/success/"+room_uid+"/"+uid+"/"+positionnum+"/"+team_entry_uid);
	    params.add("cancel_url", "http://localhost:8080/");
	    params.add("fail_url", "http://localhost:8080/");
	    

	    HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

	    try {
	    	kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
	        //System.out.println(kakaoPayReadyVO.getNext_redirect_pc_url());
	        return kakaoPayReadyVO.getNext_redirect_pc_url();

	    } catch (RestClientException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (URISyntaxException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return kakaoPayReadyVO.getNext_redirect_pc_url();
	}
	
}

