package com.spots.service;

import com.ssafy.happyhouse.dto.UserInfo;

public interface LoginService {

	public UserInfo login(String userid, String userpwd);
	
}
