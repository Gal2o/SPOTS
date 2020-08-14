package com.spots.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.DataInfo;
import com.spots.dto.EntryInfo;
import com.spots.dto.FMatchInfo;


@Mapper
public interface EntryMapper {

	public int S1(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S2(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S3(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S4(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S5(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S6(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S7(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S8(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S9(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S10(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S11(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S12(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int S13(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	
	public int D1(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D2(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D3(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D4(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D5(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D6(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D7(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D8(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D9(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D10(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D11(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D12(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public int D13(@Param("team_entry_uid") int team_entry_uid, @Param("uid") int uid);
	public EntryInfo entrylist(@Param("team_entry_uid") int team_entry_uid);
	public int FWin(FMatchInfo dto);
	public int FLose(FMatchInfo dto);
	public int FDraw(FMatchInfo dto);
	public int Upuser(DataInfo d);

	public int TWin(@Param("h_uid") int h_uid, @Param("a_uid") int a_uid);
	public int TLose(@Param("h_uid") int h_uid, @Param("a_uid") int a_uid);
	public int TDraw(@Param("h_uid") int h_uid, @Param("a_uid") int a_uid);
	public int TD(@Param("team_matching_uid") int team_matching_uid, @Param("away_team_uid") int away_team_uid);
}
