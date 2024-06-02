package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.service.ReservationService;
import com.smartcity.parkingmanager.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
        try {
            reservationService.createReservation(request.getUserId(), request.getParkingLotId(), request.getParkingSpaceId());
            return ResponseEntity.ok("Reservation created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create reservation: " + e.getMessage());
        }
    }
}
