package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.ParkingSpace;
import com.smartcity.parkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService
{
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public List<ParkingSpace> getAllParkingSpaces()
    {
        return parkingSpaceRepository.findAll();
    }

    public ParkingSpace getParkingSpaceById(Long parkingSpaceId)
    {
        return parkingSpaceRepository.findById(parkingSpaceId).orElse(null);
    }

    public ParkingSpace saveParkingSpace(ParkingSpace parkingSpace)
    {
        return parkingSpaceRepository.save(parkingSpace);
    }

    public void deleteParkingSpace(Long parkingSpaceId)
    {
        parkingSpaceRepository.deleteById(parkingSpaceId);
    }
}