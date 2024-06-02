package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.Reservation;
import com.smartcity.parkingmanager.model.User;
import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.repository.ReservationRepository;
import com.smartcity.parkingmanager.repository.UserRepository;
import com.smartcity.parkingmanager.repository.ParkingLotRepository;
import com.smartcity.parkingmanager.model.ParkingSpace;
import com.smartcity.parkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;  // Add this

    public void createReservation(Long userId, Long parkingLotId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(() -> new IllegalArgumentException("Invalid parking lot ID"));
        ParkingSpace parkingSpace = parkingSpaceRepository.findFirstByParkingLotParkingLotIdAndIsReservedFalse(parkingLotId).orElseThrow(() -> new IllegalArgumentException("Invalid parking space ID"));  // Add this

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setParkingLot(parkingLot);
        reservation.setParkingSpace(parkingSpace);
        reservation.setReservedAt(LocalDateTime.now());// Add this

        reservationRepository.save(reservation);
    }

    public List<Reservation> getALlReservations() {
        return reservationRepository.findAll();
    }
}