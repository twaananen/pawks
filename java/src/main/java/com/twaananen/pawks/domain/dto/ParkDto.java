package com.twaananen.pawks.domain.dto;

import java.util.UUID;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkDto {
  private UUID id;

  private String name;

  private String description;

  private Point location;
}
