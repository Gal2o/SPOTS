package com.spots.service;

import com.spots.dto.DataInfo;
import com.spots.dto.EntryInfo;
import com.spots.dto.FMatchInfo;

public interface EntryService {

	public int S1(int team_entry_uid, int uid);
	public int S2(int team_entry_uid, int uid);
	public int S3(int team_entry_uid, int uid);
	public int S4(int team_entry_uid, int uid);
	public int S5(int team_entry_uid, int uid);
	public int S6(int team_entry_uid, int uid);
	public int S7(int team_entry_uid, int uid);
	public int S8(int team_entry_uid, int uid);
	public int S9(int team_entry_uid, int uid);
	public int S10(int team_entry_uid, int uid);
	public int S11(int team_entry_uid, int uid);
	public int S12(int team_entry_uid, int uid);
	public int S13(int team_entry_uid, int uid);
	
	public int D1(int team_entry_uid, int uid);
	public int D2(int team_entry_uid, int uid);
	public int D3(int team_entry_uid, int uid);
	public int D4(int team_entry_uid, int uid);
	public int D5(int team_entry_uid, int uid);
	public int D6(int team_entry_uid, int uid);
	public int D7(int team_entry_uid, int uid);
	public int D8(int team_entry_uid, int uid);
	public int D9(int team_entry_uid, int uid);
	public int D10(int team_entry_uid, int uid);
	public int D11(int team_entry_uid, int uid);
	public int D12(int team_entry_uid, int uid);
	public int D13(int team_entry_uid, int uid);
	public EntryInfo entrylist(int team_entry_uid);
	public int FWin(FMatchInfo dto);
	public int FLose(FMatchInfo dto);
	public int FDraw(FMatchInfo dto);
	public int Upuser(DataInfo d);
	
	public int TWin(int h_uid, int a_uid);
	public int TLose(int h_uid, int a_uid);
	public int TDraw(int h_uid, int a_uid);
	public int TD(int team_matching_uid, int away_team_uid);
	
}
