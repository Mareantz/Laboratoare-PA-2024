package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.dto.ReservationDTO;
import com.smartcity.parkingmanager.mapper.ReservationMapper;
import com.smartcity.parkingmanager.service.ReservationService;
import com.smartcity.parkingmanager.dto.ExtendReservationRequest;
import com.smartcity.parkingmanager.model.Reservation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController
{
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @PostMapping
    public ResponseEntity<String> createReservation(@RequestBody ReservationRequest request)
    {
        try
        {
            reservationService.createReservation(request.getUserId(), request.getParkingLotId());
            return ResponseEntity.ok("Reservation created successfully");
        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Failed to create reservation: " + e.getMessage());
        }
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations()
    {
        List<Reservation> reservations = reservationService.getALlReservations();
        return reservations.stream()
                .map(reservationMapper::toReservationDTO)
                .collect(Collectors.toList());
    }

    @Setter
    @Getter
    public static class ReservationRequest
    {
        private Long userId;
        private Long parkingLotId;

    }

    @PostMapping("/extend")
    public ResponseEntity<String> extendReservation(@RequestBody ExtendReservationRequest extendRequest)
    {
        reservationService.extendReservation(extendRequest.getReservationId(), extendRequest.getMinutes());
        return ResponseEntity.ok("Reservation extended successfully");
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId)
    {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok("Reservation canceled successfully");
    }
}