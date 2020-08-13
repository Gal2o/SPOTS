package com.spots.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

@Mapper
public interface MatchMapper {
	
	List<TMatchInfo> TListMain();
	
	List<FMatchInfo> FListMain();
	
	List <TMatchInfo> TListAll(
			@Param("doe") String doe, 
			@Param("si") String si, 
			@Param("dong") String dong,
			@Param("word") String word);
	
	List <TMatchInfo> TListRoom(@Param("uid") int uid);

	void TRoomCreate(TMatchInfo info);
	
	void TRoomModify(TMatchInfo info);

	List<TMatchInfo> TRoomCheck(@Param("head_uid") int head_uid);
	
	List<FMatchInfo> FListAll(
			@Param("doe") String doe, 
			@Param("si") String si, 
			@Param("dong") String dong,
			@Param("word") String word);

	List<FMatchInfo> FListRoom(@Param("uid") int uid);

	void FRoomCreate(FMatchInfo info);

	void FRoomModify(FMatchInfo info);

	List<FMatchInfo> FRoomCheck(@Param("head_uid") int head_uid);

	int matchEnd(@Param("uid") int uid, @Param("mvp")int mvp);

	int TRoomJoin(@Param("uid")int uid, @Param("room_uid") int room_uid);


}