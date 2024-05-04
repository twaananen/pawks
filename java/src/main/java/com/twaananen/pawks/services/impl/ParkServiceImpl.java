package com.twaananen.pawks.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

  @Override
  public ParkDto getPark(UUID id) {
    Optional<Park> parkOptional = parkRepository.findById(id);
    if (parkOptional.isPresent())
      return parkMapper.mapTo(parkOptional.get());
    else
      return null;
  }

  @Override
  public ParkDto updatePark(UUID id, ParkDto park) {
    Optional<Park> parkOptional = parkRepository.findById(id);
    if (parkOptional.isPresent()) {
      Park updatedPark = parkOptional.get();
      updatedPark.setName(park.getName());
      updatedPark.setDescription(park.getDescription());
      updatedPark.setLocation(park.getLocation());
      return parkMapper.mapTo(parkRepository.save(updatedPark));
    } else
      return null;
  }

  @Override
  public void deletePark(UUID id) {
    parkRepository.deleteById(id);
  }
}
