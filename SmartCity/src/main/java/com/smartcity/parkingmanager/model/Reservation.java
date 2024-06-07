package com.smartcity.parkingmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "reservations")
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-reservations")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    @JsonBackReference(value = "parkingLot-reservations")
    private ParkingLot parkingLot;

    @ManyToOne
    @JoinColumn(name = "parking_space_id", nullable = false)
    @JsonBackReference(value = "parkingSpace-reservations")

    private ParkingSpace parkingSpace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}