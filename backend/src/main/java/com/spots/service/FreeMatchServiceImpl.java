package com.spots.service;

import java.util.List;

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
	public List<FMatchInfo> FListMain() {
		return mapper.FListMain();
	}

	@Override
	public List<FMatchInfo> FListAll() {
		return mapper.FListAll();
	}

	@Override
	public List<FMatchInfo> FListRoom(int uid) {
		return mapper.FListRoom(uid);
	}
	
	
}
