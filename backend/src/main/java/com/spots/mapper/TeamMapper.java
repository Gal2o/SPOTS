package com.spots.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.ApplyTeamInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;


@Mapper
public interface TeamMapper {

	public int registTeam(TeamInfo team);

	public int modifyTeam(TeamInfo team);

	public TeamInfo detailTeam(int uid);

	public int deleteTeam(int uid);

	public List<TeamInfo> listTeam(String where);

	public List<ApplyTeamInfo> applyList(int team_uid);

	public List<UserInfo> userList(int uid);

	public List<UserInfo> searchTeam(String text);

	public List<UserInfo> searchState(String state_code);

	public List<UserInfo> searchCity(String city_code);

}
