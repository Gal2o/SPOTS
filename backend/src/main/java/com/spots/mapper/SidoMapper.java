package com.spots.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.spots.dto.PlaceInfo;
import com.spots.dto.SidoInfo;


@Mapper
public interface SidoMapper {

	<List>SidoInfo cityList();

	<List>SidoInfo stateList();

	<List>SidoInfo dongList();


}
