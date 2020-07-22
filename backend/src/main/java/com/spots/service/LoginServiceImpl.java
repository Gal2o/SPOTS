package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.UserInfo;
import com.spots.mapper.UserMapper;



@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public UserInfo login(String email, String password){
		if(email == null || password == null)
			return null;
		return mapper.login(email, password);
	}
	
}
