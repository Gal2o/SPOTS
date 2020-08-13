package com.spots.service;

import java.util.List;

import com.spots.dto.TMatchInfo;

public interface TeamMatchService {

	public List <TMatchInfo> TListMain();
	
	public List <TMatchInfo> TListAll(String doe, String si, String dong, String word);

	public List <TMatchInfo> TListRoom(int uid);

	public void TRoomCreate(TMatchInfo info);

	public void TRoomUpdate(TMatchInfo info);

	public List<TMatchInfo> TRoomCheck(int head_uid);

	public int TRoomJoin(int uid, int room_uid);

	
}