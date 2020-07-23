package com.spots.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

@Mapper
public interface MatchMapper {

	public List<TMatchInfo> TList();
	
	public List<FMatchInfo> FList();
}
