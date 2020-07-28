package com.spots.service;

import com.spots.dto.PlaceInfo;
import com.spots.dto.SidoInfo;
import com.spots.dto.UserInfo;

public interface SidoInfoService {

	<List>SidoInfo cityList();

	<List>SidoInfo stateList();

	<List>SidoInfo dongList();


}
