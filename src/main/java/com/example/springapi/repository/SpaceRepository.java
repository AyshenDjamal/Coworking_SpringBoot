package com.example.springapi.repository;


import com.example.springapi.entity.CoworkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<CoworkingSpace, Integer> {


    List<CoworkingSpace> findByIsAvailable(boolean isAvailable);


    List<CoworkingSpace> findBySpaceType(String spaceType);


    List<CoworkingSpace> findBySpaceTypeAndIsAvailable(String spaceType, boolean isAvailable);


    boolean existsBySpaceID(int spaceID);

    List<CoworkingSpace> findByIsAvailableTrue();
}
