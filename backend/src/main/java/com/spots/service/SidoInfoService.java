package com.spots.service;

import java.util.List;

import com.spots.dto.SidoInfo;

public interface SidoInfoService {

	List<SidoInfo> stateList();
	
	List<SidoInfo> cityList(String state_code);

	List<SidoInfo> dongList(String city_code);


}
