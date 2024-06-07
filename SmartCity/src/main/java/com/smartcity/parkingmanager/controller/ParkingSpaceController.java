package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.model.ParkingSpace;
import com.smartcity.parkingmanager.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-spaces")
public class ParkingSpaceController
{
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @GetMapping
    public List<ParkingSpace> getAllParkingSpaces()
    {
        return parkingSpaceService.getAllParkingSpaces();
    }

    @GetMapping("/{id}")
    public ParkingSpace getParkingSpaceById(@PathVariable Long id)
    {
        return parkingSpaceService.getParkingSpaceById(id);
    }

    @PostMapping
    public ParkingSpace createParkingSpace(@RequestBody ParkingSpace parkingSpace)
    {
        return parkingSpaceService.saveParkingSpace(parkingSpace);
    }

    @DeleteMapping("/{id}")
    public void deleteParkingSpace(@PathVariable Long id)
    {
        parkingSpaceService.deleteParkingSpace(id);
    }
}