package com.spots.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.FMatchInfo;
import com.spots.mapper.MatchMapper;

@Service
public class FreeMatchServiceImpl implements FreeMatchService {

	@Autowired
	MatchMapper mapper;
	
	@Override
	public FMatchInfo FList() {
		return mapper.FList();
	}
}
