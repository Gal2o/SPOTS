package com.spots.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.UserInfo;


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
	


}
