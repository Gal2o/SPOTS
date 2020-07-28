package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.PlaceInfo;
import com.spots.mapper.PlaceMapper;

@Service
public class PlaceInfoServiceImpl implements PlaceInfoService {
	
	@Autowired
	PlaceMapper mapper;

	@Override
	public List<PlaceInfo> placeList() {
		return mapper.placeList();
	}

	@Override
	public PlaceInfo placeDetail(int uid) {
		return mapper.placeDetail(uid);
	}

	@Override
	public int placeRegist(PlaceInfo dto) {
		return mapper.placeRegist(dto);
	}



	 
}
