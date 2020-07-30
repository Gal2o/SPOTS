package com.spots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
