package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.repository.ParkingLotRepository;
import com.smartcity.parkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingLotService
{
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public List<ParkingLot> getAllParkingLots()
    {
        return parkingLotRepository.findAll();
    }

    public ParkingLot saveParkingLot(ParkingLot parkingLot)
    {
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot getParkingLotById(Long parkingLotId)
    {
        return parkingLotRepository.findById(parkingLotId).orElse(null);
    }

    public void deleteParkingLot(Long parkingLotId)
    {
        parkingLotRepository.deleteById(parkingLotId);
    }

    @Transactional
    public void updateAvailableSpaces(Long parkingLotId)
    {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(() -> new IllegalArgumentException("Invalid parking lot ID"));
        int reservedSpaces = parkingSpaceRepository.countByParkingLotParkingLotIdAndIsReserved(parkingLotId, true);
        int availableSpaces = parkingLot.getCapacity() - reservedSpaces;
        parkingLot.setAvailableSpaces(availableSpaces);
        parkingLotRepository.save(parkingLot);
    }
}