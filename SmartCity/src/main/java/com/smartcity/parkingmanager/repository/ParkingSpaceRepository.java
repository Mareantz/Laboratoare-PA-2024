package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    Optional<ParkingSpace> findFirstByParkingLotParkingLotIdAndIsReservedFalse(Long parkingLotId);
}