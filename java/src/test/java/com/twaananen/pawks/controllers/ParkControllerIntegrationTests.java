package com.twaananen.pawks.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twaananen.pawks.TestDataUtil;
import com.twaananen.pawks.domain.Park;
import com.twaananen.pawks.domain.dto.ParkDto;
import com.twaananen.pawks.mappers.Mapper;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class ParkControllerIntegrationTests {

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  @Autowired
  public ParkControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  @Test
  public void testThatCreateParkSuccessfullyReturns201() throws Exception {
    Park park = TestDataUtil.createTestPark();

    mockMvc.perform(MockMvcRequestBuilders.post("/api/parks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(park)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testThatCreateParkSuccessfullyReturnsSavedAuthor() throws Exception {
    Park park = TestDataUtil.createTestPark();

    mockMvc.perform(MockMvcRequestBuilders.post("/api/parks")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(park))).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Länsilinkin koira-aitaus"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Sekapuisto"));
  }

}
