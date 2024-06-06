package com.smartcity.parkingmanager.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.smartcity.parkingmanager.model.Reservation;
import com.smartcity.parkingmanager.model.ParkingSpace;
import com.smartcity.parkingmanager.repository.ReservationRepository;
import com.smartcity.parkingmanager.repository.ParkingSpaceRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationCleanupService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Scheduled(fixedRate = 60000)
    public void cleanupExpiredReservations() {
        List<Reservation> expiredReservations = reservationRepository.findAllByEndTimeBefore(LocalDateTime.now());
        for (Reservation reservation : expiredReservations) {
            ParkingSpace parkingSpace = reservation.getParkingSpace();
            parkingSpace.setReserved(false);
            parkingSpaceRepository.save(parkingSpace);
            reservationRepository.delete(reservation);
            parkingLotService.updateAvailableSpaces(parkingSpace.getParkingLot().getParkingLotId());
        }
    }
}
