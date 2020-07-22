package com.spots.service;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.FMatchInfo;
import com.spots.mapper.MatchMapper;

public class FreeMatchServiceImpl implements FreeMatchService {

	@Mapper
	MatchMapper mapper;
	
	@Override
	public FMatchInfo FList() {
		return mapper.FList();
	}
}
