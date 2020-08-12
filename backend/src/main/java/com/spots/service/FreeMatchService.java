package com.spots.service;

import java.util.List;

import com.spots.dto.FMatchInfo;

public interface FreeMatchService {

	public List <FMatchInfo> FListMain();
	
	public List <FMatchInfo> FListAll(String doe, String si, String dong, String word);

	public List <FMatchInfo> FListRoom(int uid);

	public void FRoomCreate(FMatchInfo info);

	public void FRoomUpdate(FMatchInfo info);

	public List<FMatchInfo> FRoomCheck(int head_uid);

	public int matchEnd(int uid, int mvp);
}