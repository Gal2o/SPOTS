package com.spots.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

@Mapper
public interface MatchMapper {

	public TMatchInfo TList();
	
	public FMatchInfo FList();
}
