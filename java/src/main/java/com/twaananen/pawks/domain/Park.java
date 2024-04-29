package com.twaananen.pawks.domain;

import java.util.UUID;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "parks")
public class Park {

  @Id
  @GeneratedValue
  private UUID id;

  private String name;

  private String description;

  private Point location;

}
