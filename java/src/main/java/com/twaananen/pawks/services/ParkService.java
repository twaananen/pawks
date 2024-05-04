package com.twaananen.pawks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.twaananen.pawks.domain.dto.ParkDto;

public interface ParkService {
  Optional<ParkDto> createPark(ParkDto parkDto);

  List<ParkDto> getParks();

  Optional<ParkDto> getPark(UUID id);

  Optional<ParkDto> updatePark(UUID id, ParkDto park);

  void deletePark(UUID id);
}
