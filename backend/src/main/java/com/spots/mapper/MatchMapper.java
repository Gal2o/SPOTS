package com.spots.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

@Mapper
public interface MatchMapper {

	List <TMatchInfo> TListMain();
	
	List <TMatchInfo> TListAll();
	
	List <TMatchInfo> TListRoom(int uid);

	void TRoomCreate(TMatchInfo info);
	
	void TRoomModify(TMatchInfo info);
	
	List<FMatchInfo> FListMain();

	List<FMatchInfo> FListAll();

	List<FMatchInfo> FListRoom(int uid);

	void FRoomCreate(FMatchInfo info);

	void FRoomModify(FMatchInfo info);
}
