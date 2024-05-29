package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
}