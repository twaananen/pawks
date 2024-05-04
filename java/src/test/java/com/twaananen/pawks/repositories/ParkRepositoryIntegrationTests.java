package com.twaananen.pawks.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.twaananen.pawks.TestDataUtil;
import com.twaananen.pawks.domain.Park;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ParkRepositoryIntegrationTests {
  private ParkRepository parkRepository;

  @Autowired
  public ParkRepositoryIntegrationTests(ParkRepository parkRepository) {
    this.parkRepository = parkRepository;
  }

  @Test
  public void testThatParkCanBeCreatedAndRecalled() {
    Park park = TestDataUtil.createTestPark();
    assertEquals(park.getId(), null);
    Park savedPark = parkRepository.save(park);

    Optional<Park> result = parkRepository.findById(savedPark.getId());
    assertTrue(result.isPresent());
    assertEquals(result.get(), savedPark);
    assertTrue(result.get().getId().version() == 4);
  }

  @Test
  public void testThatParkCanBeUpdated() {
    Park park = TestDataUtil.createTestPark();
    Park savedPark = parkRepository.save(park);

    savedPark.setDescription("New description");
    parkRepository.save(savedPark);

    Optional<Park> result = parkRepository.findById(savedPark.getId());
    assertTrue(result.isPresent());
    assertEquals(savedPark, result.get());
  }

  @Test
  public void testThatParkCanBeDeleted() {
    Park park = TestDataUtil.createTestPark();
    Park savedPark = parkRepository.save(park);

    parkRepository.deleteById(savedPark.getId());
    Optional<Park> result = parkRepository.findById(savedPark.getId());
    assertTrue(result.isEmpty());
  }

}
