package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    List<Reservation> findAllByEndTimeBefore(LocalDateTime now);
    boolean existsByUserUserIdAndEndTimeAfter(Long userId, LocalDateTime now);
}