package com.spots.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.SidoInfo;


@Mapper
public interface SidoMapper {

	List<SidoInfo> stateList();

	List<SidoInfo> cityList(String state_code);

	List<SidoInfo> dongList(String city_code);


}
