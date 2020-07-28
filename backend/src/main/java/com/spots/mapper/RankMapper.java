package com.spots.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.TMatchInfo;

@Mapper
public interface RankMapper {

	public int TWin(TMatchInfo dto);
	
	public int TLose(TMatchInfo dto);
	
	public int TDraw(TMatchInfo dto);
}
