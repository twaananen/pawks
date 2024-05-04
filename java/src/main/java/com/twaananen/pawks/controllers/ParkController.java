package com.twaananen.pawks.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twaananen.pawks.domain.dto.ParkDto;
import com.twaananen.pawks.services.ParkService;

@RestController
@RequestMapping("/api/parks")
public class ParkController {

  private ParkService parkService;

  public ParkController(ParkService parkService) {
    this.parkService = parkService;
  }

  @GetMapping()
  public List<ParkDto> getParks() {
    return parkService.getParks();
  }

  @PostMapping()
  public ParkDto createPark(@RequestBody ParkDto park) {
    return parkService.createPark(park);
  }
}
