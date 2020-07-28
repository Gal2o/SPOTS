package com.spots.service;

import java.util.List;

import com.spots.dto.SidoInfo;

public interface SidoInfoService {

	List<SidoInfo> cityList();

	List<SidoInfo> stateList(String city_code);

	List<SidoInfo> dongList(String state_code);


}
