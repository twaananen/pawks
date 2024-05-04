package com.twaananen.pawks.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  public Optional<ParkDto> createPark(ParkDto parkDto) {
    Park park = parkMapper.mapFrom(parkDto);
    Park savedPark = parkRepository.save(park);
    ParkDto savedParkDto = parkMapper.mapTo(savedPark);
    return Optional.of(savedParkDto);
  }

  @Override
  public List<ParkDto> findAll() {
    List<ParkDto> parks = StreamSupport
        .stream(parkRepository.findAll().spliterator(), false)
        .map(parkMapper::mapTo)
        .collect(Collectors.toList());
    return parks;
  }

  @Override
  public Page<ParkDto> findAll(Pageable pageable) {
    return parkRepository.findAll(pageable).map(parkMapper::mapTo);
  }

  @Override
  public Optional<ParkDto> getPark(UUID id) {
    return parkRepository.findById(id).map(existingPark -> {
      return Optional.of(parkMapper.mapTo(existingPark));
    }).orElse(Optional.empty());
  }

  @Override
  public Optional<ParkDto> updatePark(UUID id, ParkDto park) {
    return parkRepository.findById(id).map(existingPark -> {
      Optional.ofNullable(park.getName()).ifPresent(existingPark::setName);
      Optional.ofNullable(park.getDescription()).ifPresent(existingPark::setDescription);
      Optional.ofNullable(park.getLocation()).ifPresent(existingPark::setLocation);
      return Optional.of(parkMapper.mapTo(parkRepository.save(existingPark)));
    }).orElse(Optional.empty());
  }

  @Override
  public void deletePark(UUID id) {
    parkRepository.deleteById(id);
  }

}
