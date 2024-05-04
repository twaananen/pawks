package com.twaananen.pawks.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping(path = "/{id}")
  public ParkDto getPark(@PathVariable("id") UUID id) {
    return parkService.getPark(id);
  }

  @PostMapping()
  public ParkDto createPark(@RequestBody ParkDto park) {
    return parkService.createPark(park);
  }

  @PutMapping(path = "/{id}")
  public ParkDto updatePark(@PathVariable("id") UUID id, @RequestBody ParkDto park) {
    return parkService.updatePark(id, park);
  }

  @DeleteMapping(path = "/{id}")
  public void deletePark(@PathVariable("id") UUID id) {
    parkService.deletePark(id);
  }
}
