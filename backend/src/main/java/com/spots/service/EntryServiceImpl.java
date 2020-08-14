package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.DataInfo;
import com.spots.dto.EntryInfo;
import com.spots.dto.FMatchInfo;
import com.spots.mapper.EntryMapper;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	EntryMapper mapper;
	
	@Override
	public int S1(int team_entry_uid, int uid) {
		return mapper.S1(team_entry_uid, uid);
	}

	@Override
	public int S2(int team_entry_uid, int uid) {
		return mapper.S2(team_entry_uid, uid);
	}

	@Override
	public int S3(int team_entry_uid, int uid) {
		return mapper.S3(team_entry_uid, uid);
	}

	@Override
	public int S4(int team_entry_uid, int uid) {
		return mapper.S4(team_entry_uid, uid);
	}

	@Override
	public int S5(int team_entry_uid, int uid) {
		return mapper.S5(team_entry_uid, uid);
	}

	@Override
	public int S6(int team_entry_uid, int uid) {
		return mapper.S6(team_entry_uid, uid);
	}

	@Override
	public int S7(int team_entry_uid, int uid) {
		return mapper.S7(team_entry_uid, uid);
	}

	@Override
	public int S8(int team_entry_uid, int uid) {
		return mapper.S8(team_entry_uid, uid);
	}

	@Override
	public int S9(int team_entry_uid, int uid) {
		return mapper.S9(team_entry_uid, uid);
	}

	@Override
	public int S10(int team_entry_uid, int uid) {
		return mapper.S10(team_entry_uid, uid);
	}

	@Override
	public int S11(int team_entry_uid, int uid) {
		return mapper.S11(team_entry_uid, uid);
	}

	@Override
	public int S12(int team_entry_uid, int uid) {
		return mapper.S12(team_entry_uid, uid);
	}
	
	@Override
	public int S13(int team_entry_uid, int uid) {
		return mapper.S13(team_entry_uid, uid);
	}
	
	
	@Override
	public int D1(int team_entry_uid, int uid) {
		return mapper.D1(team_entry_uid, uid);
	}

	@Override
	public int D2(int team_entry_uid, int uid) {
		return mapper.D2(team_entry_uid, uid);
	}

	@Override
	public int D3(int team_entry_uid, int uid) {
		return mapper.D3(team_entry_uid, uid);
	}

	@Override
	public int D4(int team_entry_uid, int uid) {
		return mapper.D4(team_entry_uid, uid);
	}

	@Override
	public int D5(int team_entry_uid, int uid) {
		return mapper.D5(team_entry_uid, uid);
	}

	@Override
	public int D6(int team_entry_uid, int uid) {
		return mapper.D6(team_entry_uid, uid);
	}

	@Override
	public int D7(int team_entry_uid, int uid) {
		return mapper.D7(team_entry_uid, uid);
	}

	@Override
	public int D8(int team_entry_uid, int uid) {
		return mapper.D8(team_entry_uid, uid);
	}

	@Override
	public int D9(int team_entry_uid, int uid) {
		return mapper.D9(team_entry_uid, uid);
	}

	@Override
	public int D10(int team_entry_uid, int uid) {
		return mapper.D10(team_entry_uid, uid);
	}

	@Override
	public int D11(int team_entry_uid, int uid) {
		return mapper.D11(team_entry_uid, uid);
	}

	@Override
	public int D12(int team_entry_uid, int uid) {
		return mapper.D12(team_entry_uid, uid);
	}
	
	@Override
	public int D13(int team_entry_uid, int uid) {
		return mapper.D13(team_entry_uid, uid);
	}

	@Override
	public EntryInfo entrylist(int team_entry_uid) {
		return mapper.entrylist(team_entry_uid);
	}

	@Override
	public int FWin(FMatchInfo dto) {
		return mapper.FWin(dto);
	}

	@Override
	public int FLose(FMatchInfo dto) {
		return mapper.FLose(dto);
	}

	@Override
	public int FDraw(FMatchInfo dto) {
		return mapper.FDraw(dto);
	}

	@Override
	public int Upuser(DataInfo d) {
		return mapper.Upuser(d);
	}

	@Override
	public int TWin(int h_uid, int a_uid) {
		return mapper.TWin(h_uid, a_uid);
	}

	@Override
	public int TLose(int h_uid, int a_uid) {
		return mapper.TLose(h_uid, a_uid);

	}

	@Override
	public int TDraw(int h_uid, int a_uid) {
		return mapper.TDraw(h_uid, a_uid);

	}

	@Override
	public int TD(int team_matching_uid, int away_team_uid) {
		return mapper.TD(team_matching_uid, away_team_uid);
	}

}
