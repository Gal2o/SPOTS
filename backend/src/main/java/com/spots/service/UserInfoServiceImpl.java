package com.spots.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.UserInfo;
import com.spots.mapper.UserMapper;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	UserMapper mapper;

	
	@Override 
	public int signUp(UserInfo user) { 
		return mapper.signUp(user); 
	}
	
	@Override 
	public int modify(UserInfo dto) { 
		return mapper.modify(dto); 
	}
	 
	@Override 
	public UserInfo detail(String email) { 
		return mapper.detail(email); 
	}
	  
	@Override
	public UserInfo findPwdByEmail(String email) { 
		return mapper.findPwdByEmail(email); 
	}

	@Override
	public int joinTeam(int uid, int team_uid) {
		return mapper.joinTeam(uid, team_uid); 
	}

	@Override
	public int outTeam(int uid, int team_uid) {
		return mapper.outTeam(uid, team_uid);
	}

	@Override
	public int applyTeam(ApplyTeamInfo dto) {
		return mapper.applyTeam(dto);
	}

	@Override
	public UserInfo detail2(int uid) {
		return mapper.detail2(uid); 
	}

	@Override
	public int rejectTeam(int uid, int team_uid) {
		return mapper.rejectTeam(uid, team_uid);
	}
	 
}
