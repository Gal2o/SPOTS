package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;
import com.spots.mapper.TeamMapper;

@Service
public class TeamInfoServiceImpl implements TeamInfoService {
	
	@Autowired
	TeamMapper mapper;
	
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
	public List<TeamInfo> listTeam() {
		return mapper.listTeam();
	}

	@Override
	public List<ApplyTeamInfo> applyList(int team_uid) {
		return mapper.applyList(team_uid);
	}

	@Override
	public List<UserInfo> userList(int uid) {
		return mapper.userList(uid);
	}

	@Override
	public List<UserInfo> searchTeam(String text) {
		return mapper.searchTeam(text);
	}

	@Override
	public List<UserInfo> searchState(String state_code) {
		return mapper.searchState(state_code);
	}

	@Override
	public List<UserInfo> searchCity(String city_code) {
		return mapper.searchCity(city_code);
	}
	

	 
}
