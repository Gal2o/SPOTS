package com.spots.service;

import java.util.List;

import com.spots.dto.FMatchInfo;
import com.spots.dto.TMatchInfo;

public interface TeamMatchService {

	public List<TMatchInfo> TList();
	
	public List<FMatchInfo> FList();
}
