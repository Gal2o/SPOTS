package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.TeamInfo;
import com.spots.mapper.TeamInfoMapper;

@Service
public class TeamInfoServiceImpl implements TeamInfoService {
	
	@Autowired
	TeamInfoMapper mapper;
	
	@Override
	public int registTeam(TeamInfo team) {
		return mapper.registTeam(team);
	}

	@Override
	public int modifyTeam(TeamInfo team) {
		return mapper.modifyTeam(team);
	}

	@Override
	public TeamInfo detailTeam(int uid) {
		return mapper.detailTeam(uid);
	}

	@Override
	public int deleteTeam(int uid) {
		return mapper.deleteTeam(uid);
	}

	@Override
	public <List>TeamInfo listTeam() {
		return mapper.listTeam();
	}
	

	 
}
