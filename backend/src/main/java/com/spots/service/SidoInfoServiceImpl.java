package com.spots.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spots.dto.SidoInfo;
import com.spots.mapper.SidoMapper;

@Service
public class SidoInfoServiceImpl implements SidoInfoService {
	
	@Autowired
	SidoMapper mapper;

	@Override
	public List<SidoInfo> cityList() {
		return mapper.cityList();
	}

	@Override
	public List<SidoInfo> stateList(String city_code) {
		city_code = city_code.substring(0,2);
		return mapper.stateList(city_code);
	}

	@Override
	public List<SidoInfo> dongList(String state_code) {
		state_code = state_code.substring(0,4);
		return mapper.dongList(state_code);
	}





	 
}
