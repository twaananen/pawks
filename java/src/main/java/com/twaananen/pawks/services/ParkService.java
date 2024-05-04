package com.twaananen.pawks.services;

import java.util.List;

import com.twaananen.pawks.domain.dto.ParkDto;

public interface ParkService {
  ParkDto createPark(ParkDto parkDto);

  List<ParkDto> getParks();
}
