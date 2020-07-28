package com.spots.service;

import java.util.List;

import com.spots.dto.TMatchInfo;

public interface TeamMatchService {

	public TMatchInfo TListMain();

	public TMatchInfo TListAll();

	public TMatchInfo TListRoom(int uid);
	
}
