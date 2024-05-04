package com.twaananen.pawks.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.twaananen.pawks.domain.Park;

@Repository
public interface ParkRepository extends CrudRepository<Park, UUID>,
    PagingAndSortingRepository<Park, UUID> {

}
