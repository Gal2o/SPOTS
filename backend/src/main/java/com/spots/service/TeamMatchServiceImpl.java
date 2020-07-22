package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;
import com.spots.mapper.MatchMapper;

@Service
public class TeamMatchServiceImpl implements TeamMatchService{

	@Autowired
	MatchMapper mapper;
	
	@Override
	public List<TMatchInfo> TList() {
		return mapper.TList();
	}

	@Override
	public List<FMatchInfo> FList() {
		return mapper.FList();
	}

}
