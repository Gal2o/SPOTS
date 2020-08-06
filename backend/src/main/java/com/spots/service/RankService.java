package com.spots.service;

import java.util.List;

import com.spots.dto.TMatchInfo;
import com.spots.dto.TeamInfo;
import com.spots.dto.UserInfo;

public interface RankService {

	public int TWin(TMatchInfo dto);
	public int TLose(TMatchInfo dto);
	public int TDraw(TMatchInfo dto);
	public List<TeamInfo> listTeam();
	public List<UserInfo> listFree();
}
