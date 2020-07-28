package com.spots.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;

@Mapper
public interface RankMapper {

	public int TWin(TMatchInfo dto);
	
	public int TLose(TMatchInfo dto);
	
	public int TDraw(TMatchInfo dto);
	
	public List<TeamInfo> listTeam();
}
