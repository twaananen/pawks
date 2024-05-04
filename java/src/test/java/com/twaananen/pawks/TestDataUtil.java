package com.twaananen.pawks;

import com.twaananen.pawks.domain.Park;

public class TestDataUtil {

  public static Park createTestPark() {
    return Park.builder()
        .name("Länsilinkin koira-aitaus")
        .description("Sekapuisto")
        // .location(new GeometryFactory().createPoint(new Coordinate(60.16211951241279,
        // 24.92175140927046)))
        .location(null)
        .build();
  }

}
