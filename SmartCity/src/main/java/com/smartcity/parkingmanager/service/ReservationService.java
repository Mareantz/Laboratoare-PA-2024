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
import org.springframework.transaction.annotation.Transactional;

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
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Transactional
    public void createReservation(Long userId, Long parkingLotId) {
        if (reservationRepository.existsByUserUserIdAndEndTimeAfter(userId, LocalDateTime.now())) {
            throw new IllegalArgumentException("User already has an active reservation");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking lot ID"));
        ParkingSpace parkingSpace = parkingSpaceRepository.findFirstByParkingLotParkingLotIdAndIsReservedFalse(parkingLotId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking space ID"));
        parkingSpace.setReserved(true);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setParkingLot(parkingLot);
        reservation.setParkingSpace(parkingSpace);
        reservation.setStartTime(LocalDateTime.now());
        reservation.setEndTime(LocalDateTime.now().plusHours(1));

        reservationRepository.save(reservation);
        parkingLotService.updateAvailableSpaces(parkingLotId);
    }


    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID"));
        ParkingSpace parkingSpace = reservation.getParkingSpace();
        parkingSpace.setReserved(false);
        parkingSpaceRepository.save(parkingSpace);
        reservationRepository.delete(reservation);
        parkingLotService.updateAvailableSpaces(parkingSpace.getParkingLot().getParkingLotId());
    }

    @Transactional
    public void clearAllReservations() {
        reservationRepository.deleteAll();
        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        for (ParkingSpace space : parkingSpaces) {
            space.setReserved(false);
            parkingSpaceRepository.save(space);
        }
        List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
        for (ParkingLot lot : parkingLots) {
            parkingLotService.updateAvailableSpaces(lot.getParkingLotId());
        }
    }

    @Transactional
    public void extendReservation(Long reservationId, int minutes) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID"));
        LocalDateTime newEndTime = reservation.getEndTime().plusMinutes(minutes);
        reservation.setEndTime(newEndTime);
        reservationRepository.save(reservation);
    }


    public List<Reservation> getALlReservations() {
        return reservationRepository.findAll();
    }
}