package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.TMatchInfo;
import com.spots.mapper.RankMapper;

@Service
public class RankServiceImpl implements RankService {

	@Autowired
	RankMapper mapper;
	
	@Override
	public int TWin(TMatchInfo dto) {
		return mapper.TWin(dto);
	}

	@Override
	public int TLose(TMatchInfo dto) {
		return mapper.TLose(dto);
	}

	@Override
	public int TDraw(TMatchInfo dto) {
		return mapper.TDraw(dto);
	}


}
