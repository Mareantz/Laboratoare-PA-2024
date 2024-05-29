package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot saveParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot getParkingLotById(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId).orElse(null);
    }

    public void deleteParkingLot(Long parkingLotId) {
        parkingLotRepository.deleteById(parkingLotId);
    }
}
