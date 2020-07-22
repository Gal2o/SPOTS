package com.spots.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.UserInfo;
import com.spots.mapper.UserMapper;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	UserMapper mapper;

	/*
	 * @Override public int signUp(UserInfo user) { return 1; //return
	 * mapper.signUp(user); }
	 * 
	 * @Override public int modify(UserInfo dto) { return mapper.modify(dto); }
	 * 
	 * @Override public int delete(String id) { return mapper.delete(id); }
	 * 
	 * @Override public UserInfo detail(String id) { return mapper.detail(id); }
	 * 
	 * @Override public UserInfo findPwdById(String id) { return
	 * mapper.findPwdById(id); }
	 */
}
