package com.spots.service;

import com.spots.dto.FMatchInfo;

public interface FreeMatchService {
	
	public FMatchInfo FListMain();

	public FMatchInfo FListAll();

	public FMatchInfo FListRoom(int uid);
}
