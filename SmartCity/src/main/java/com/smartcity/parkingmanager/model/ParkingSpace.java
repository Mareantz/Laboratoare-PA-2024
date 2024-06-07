package com.smartcity.parkingmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parking_spaces")
public class ParkingSpace
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    @JsonBackReference(value = "parkingLot-parkingSpaces")
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingSpace", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "parkingSpace-reservations")
    private Set<Reservation> reservations = new HashSet<>();

    private boolean isReserved;
}