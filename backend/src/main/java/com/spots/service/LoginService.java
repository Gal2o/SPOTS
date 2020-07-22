package com.spots.service;

import com.spots.dto.UserInfo;

public interface LoginService {

	public UserInfo login(String email, String password);
	
}
