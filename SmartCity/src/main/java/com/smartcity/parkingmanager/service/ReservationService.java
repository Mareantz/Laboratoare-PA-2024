package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.Reservation;
import com.smartcity.parkingmanager.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService
{
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long reservationId)
    {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    public void deleteReservation(Long reservationId)
    {
        reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> getReservationsByUserId(Long userId)
    {
        return reservationRepository.findByUser_UserId(userId);
    }
}
