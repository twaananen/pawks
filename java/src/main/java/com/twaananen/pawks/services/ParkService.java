package com.twaananen.pawks.services;

import java.util.List;
import java.util.UUID;

import com.twaananen.pawks.domain.dto.ParkDto;

public interface ParkService {
  ParkDto createPark(ParkDto parkDto);

  List<ParkDto> getParks();

  ParkDto getPark(UUID id);

  ParkDto updatePark(UUID id, ParkDto park);

  void deletePark(UUID id);
}
