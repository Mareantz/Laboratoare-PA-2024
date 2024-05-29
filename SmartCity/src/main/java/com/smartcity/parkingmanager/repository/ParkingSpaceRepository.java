package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
}