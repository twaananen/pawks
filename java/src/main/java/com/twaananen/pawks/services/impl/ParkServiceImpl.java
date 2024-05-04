package com.twaananen.pawks.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.twaananen.pawks.domain.Park;
import com.twaananen.pawks.domain.dto.ParkDto;
import com.twaananen.pawks.mappers.Mapper;
import com.twaananen.pawks.repositories.ParkRepository;
import com.twaananen.pawks.services.ParkService;

@Service
public class ParkServiceImpl implements ParkService {

  private ParkRepository parkRepository;
  private Mapper<Park, ParkDto> parkMapper;

  public ParkServiceImpl(Mapper<Park, ParkDto> parkMapper, ParkRepository parkRepository) {
    this.parkRepository = parkRepository;
    this.parkMapper = parkMapper;
  }

  @Override
  public ParkDto createPark(ParkDto parkDto) {
    Park park = parkMapper.mapFrom(parkDto);
    Park savedPark = parkRepository.save(park);
    ParkDto savedParkDto = parkMapper.mapTo(savedPark);
    return savedParkDto;
  }

  @Override
  public List<ParkDto> getParks() {
    List<ParkDto> parks = new ArrayList<>();
    parkRepository.findAll().forEach(park -> parks.add(parkMapper.mapTo(park)));
    return parks;
  }
}
