package com.spots.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spots.dto.PlaceInfo;


@Mapper
public interface PlaceMapper {

   public List<PlaceInfo> placeList();

   public PlaceInfo placeDetail(@Param ("uid") int uid);

   public int placeRegist(PlaceInfo dto);

}