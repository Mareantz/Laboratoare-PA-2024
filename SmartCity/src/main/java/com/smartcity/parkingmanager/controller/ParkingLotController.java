package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public List<ParkingLot> getAllParkingLots() {
        return parkingLotService.getAllParkingLots();
    }

    @GetMapping("/{id}")
    public ParkingLot getParkingLotById(@PathVariable Long id) {
        return parkingLotService.getParkingLotById(id);
    }

    @PostMapping
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLot.setAvailableSpaces(parkingLot.getCapacity());
        return parkingLotService.saveParkingLot(parkingLot);
    }

    @DeleteMapping("/{id}")
    public void deleteParkingLot(@PathVariable Long id) {
        parkingLotService.deleteParkingLot(id);
    }
}

