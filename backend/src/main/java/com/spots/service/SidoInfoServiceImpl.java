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
	public List<SidoInfo> stateList() {
		return mapper.stateList();
	}

	@Override
	public List<SidoInfo> dongList() {
		return mapper.dongList();
	}





	 
}
