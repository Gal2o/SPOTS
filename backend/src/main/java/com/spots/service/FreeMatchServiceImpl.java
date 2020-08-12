package com.spots.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.FMatchInfo;
import com.spots.mapper.MatchMapper;

@Service
public class FreeMatchServiceImpl implements FreeMatchService {

	@Autowired
	MatchMapper mapper;

	@Override
	public List<FMatchInfo> FListMain() {
		return mapper.FListMain();
	}
	
	@Override
	public List<FMatchInfo> FListAll(String doe, String si, String dong, String word) {
		return mapper.FListAll(doe, si, dong, word);
	}

	@Override
	public List<FMatchInfo> FListRoom(int uid) {
		return mapper.FListRoom(uid);
	}

	@Override
	public void FRoomCreate(FMatchInfo info) {
		mapper.FRoomCreate(info);
	}

	@Override
	public void FRoomUpdate(FMatchInfo info) {
		mapper.FRoomModify(info);
	}

	@Override
	public List<FMatchInfo> FRoomCheck(int head_uid) {
		return mapper.FRoomCheck(head_uid);
	}

	@Override
	public int matchEnd(int uid, int mvp) {
		return mapper.matchEnd(uid, mvp);
	}


	
	
}