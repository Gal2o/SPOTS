package com.spots.service;

import com.spots.dto.TMatchInfo;

public interface RankService {

	public int TWin(TMatchInfo dto);
	public int TLose(TMatchInfo dto);
	public int TDraw(TMatchInfo dto);

}
