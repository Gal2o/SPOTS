package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;
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

	@Override
	public List<TeamInfo> listTeam() {
		return mapper.listTeam();
	}

	@Override
	public List<UserInfo> listFree() {
		return mapper.listFree();
	}
}
