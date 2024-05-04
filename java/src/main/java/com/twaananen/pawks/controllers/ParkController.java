package com.twaananen.pawks.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public Page<ParkDto> listParks(Pageable pageable) {
    return parkService.findAll(pageable);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ParkDto> getPark(@PathVariable("id") UUID id) {
    Optional<ParkDto> parkOptional = parkService.getPark(id);
    return parkOptional
        .map(park -> new ResponseEntity<>(park, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping()
  public ResponseEntity<ParkDto> createPark(@RequestBody ParkDto park) {
    Optional<ParkDto> parkOptional = parkService.createPark(park);
    return parkOptional
        .map(savedPark -> new ResponseEntity<>(savedPark, HttpStatus.CREATED))
        .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<ParkDto> updatePark(@PathVariable("id") UUID id, @RequestBody ParkDto park) {
    Optional<ParkDto> parkOptional = parkService.updatePark(id, park);
    return parkOptional
        .map(savedPark -> new ResponseEntity<>(savedPark, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deletePark(@PathVariable("id") UUID id) {
    parkService.deletePark(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
