package com.spots.service;

import java.util.List;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;

public interface TeamInfoService {
	
	public int registTeam(TeamInfo team);
	
	public int modifyTeam(TeamInfo team);
	 
	public TeamInfo detailTeam(int uid);
	  
	public int deleteTeam(int uid);

	public List<TeamInfo> listTeam(String where);

	public List<ApplyTeamInfo> applyList(int team_uid);

	public List<UserInfo> userList(int uid);
	
}
