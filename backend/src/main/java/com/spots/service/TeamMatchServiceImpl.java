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
	public TMatchInfo TList() {
		return mapper.TList();
	}

}
