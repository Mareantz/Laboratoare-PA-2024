package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.service.ParkingLotService;
import com.smartcity.parkingmanager.mapper.ParkingLotMapper;
import com.smartcity.parkingmanager.dto.ParkingLotDetailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController
{
    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @GetMapping
    public List<ParkingLotDetailDTO> getAllParkingLots()
    {
        List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
        return parkingLots.stream()
                .map(parkingLotMapper::toParkingLotDetailDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParkingLotById(@PathVariable Long id)
    {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        if (parkingLot != null)
        {
            ParkingLotDetailDTO parkingLotDetailDTO = parkingLotMapper.toParkingLotDetailDTO(parkingLot);
            return ResponseEntity.ok(parkingLotDetailDTO);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking lot not found");
        }
    }

    @PostMapping
    public ParkingLot createParkingLot(@RequestBody ParkingLot parkingLot)
    {
        parkingLot.setAvailableSpaces(parkingLot.getCapacity());
        return parkingLotService.saveParkingLot(parkingLot);
    }
}