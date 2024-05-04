package com.twaananen.pawks.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.twaananen.pawks.domain.Park;
import com.twaananen.pawks.domain.dto.ParkDto;
import com.twaananen.pawks.mappers.Mapper;

@Component
public class ParkMapperImpl implements Mapper<Park, ParkDto> {

  private ModelMapper modelMapper;

  public ParkMapperImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public ParkDto mapTo(Park parkEntity) {
    return modelMapper.map(parkEntity, ParkDto.class);
  }

  @Override
  public Park mapFrom(ParkDto parkDto) {
    return modelMapper.map(parkDto, Park.class);
  }

}
