package com.spots.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.TeamInfo;

@Mapper
public interface TeamInfoMapper {

	int registTeam(TeamInfo team);

	int modifyTeam(TeamInfo team);

	TeamInfo detailTeam(int uid);

	int deleteTeam(int uid);

	TeamInfo listTeam();

}
