package com.spots.service;

import com.spots.dto.PlaceInfo;
import com.spots.dto.UserInfo;

public interface PlaceInfoService {
	

	public <List>PlaceInfo placeList();

	public PlaceInfo placeDetail(int uid);

	public int placeRegist(PlaceInfo dto);

}
