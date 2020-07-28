package com.spots.service;

import java.util.List;

import com.spots.dto.TeamInfo;

public interface TeamInfoService {
	
	public int registTeam(TeamInfo team);
	
	public int modifyTeam(TeamInfo team);
	 
	public TeamInfo detailTeam(int uid);
	  
	public int deleteTeam(int uid);

	public List<TeamInfo> listTeam();
	
}
