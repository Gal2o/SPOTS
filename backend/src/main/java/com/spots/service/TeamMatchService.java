package com.spots.service;

import java.util.List;

import com.spots.dto.TMatchInfo;

public interface TeamMatchService {

	public List <TMatchInfo> TListMain();

	public List <TMatchInfo> TListAll();

	public List <TMatchInfo> TListRoom(int uid);
	
}
