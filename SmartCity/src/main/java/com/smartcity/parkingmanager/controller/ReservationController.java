package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.service.ReservationService;
import com.smartcity.parkingmanager.model.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request) {
        try {
            reservationService.createReservation(request.getUserId(), request.getParkingLotId());
            return ResponseEntity.ok("Reservation created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create reservation: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getALlReservations();
    }

    @Setter
    @Getter
    public static class ReservationRequest {
        private Long userId;
        private Long parkingLotId;

    }

}

