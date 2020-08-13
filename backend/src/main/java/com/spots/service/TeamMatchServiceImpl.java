package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.TMatchInfo;
import com.spots.mapper.MatchMapper;

@Service
public class TeamMatchServiceImpl implements TeamMatchService{

	@Autowired
	MatchMapper mapper;
	
	@Override
	public List<TMatchInfo> TListMain() {
		return mapper.TListMain();
	}

	@Override
	public List<TMatchInfo> TListAll(String doe, String si, String dong, String word) {
		return mapper.TListAll(doe, si, dong, word);
	}

	@Override
	public List<TMatchInfo> TListRoom(int uid) {
		return mapper.TListRoom(uid);
	}

	@Override
	public void TRoomCreate(TMatchInfo info) {
		mapper.TRoomCreate(info);
	}

	@Override
	public void TRoomUpdate(TMatchInfo info) {
		mapper.TRoomModify(info);
	}

	@Override
	public List<TMatchInfo> TRoomCheck(int head_uid) {
		return mapper.TRoomCheck(head_uid);
	}

	@Override
	public int TRoomJoin(int uid, int room_uid) {
		return mapper.TRoomJoin(uid, room_uid);
	}


}