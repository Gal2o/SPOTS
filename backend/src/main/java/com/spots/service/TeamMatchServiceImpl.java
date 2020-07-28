package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.TMatchInfo;
import com.spots.mapper.MatchMapper;

@Service
public class TeamMatchServiceImpl implements TeamMatchService{

	@Autowired
	MatchMapper mapper;
	
	@Override
	public TMatchInfo TListMain() {
		return mapper.TListMain();
	}

	@Override
	public TMatchInfo TListAll() {
		return mapper.TListAll();
	}

	@Override
	public TMatchInfo TListRoom(int uid) {
		return mapper.TListRoom(uid);
	}

}

