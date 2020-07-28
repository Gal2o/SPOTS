package com.spots.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

@Mapper
public interface MatchMapper {

	public TMatchInfo TListMain();
	
	public FMatchInfo FListMain();

	public TMatchInfo TListAll();

	public FMatchInfo FListAll();

	public TMatchInfo TListRoom(int uid);

	public FMatchInfo FListRoom(int uid);
}
