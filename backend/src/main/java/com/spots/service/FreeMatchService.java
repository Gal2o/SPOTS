package com.spots.service;

import java.util.List;

import com.spots.dto.FMatchInfo;

public interface FreeMatchService {
	
	public List <FMatchInfo> FListMain();

	public List <FMatchInfo> FListAll();

	public List <FMatchInfo> FListRoom(int uid);
}
