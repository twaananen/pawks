package com.twaananen.pawks.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.twaananen.pawks.domain.dto.ParkDto;

public interface ParkService {
  Optional<ParkDto> createPark(ParkDto parkDto);

  List<ParkDto> findAll();

  Page<ParkDto> findAll(Pageable pageable);

  Optional<ParkDto> getPark(UUID id);

  Optional<ParkDto> updatePark(UUID id, ParkDto park);

  void deletePark(UUID id);
}
