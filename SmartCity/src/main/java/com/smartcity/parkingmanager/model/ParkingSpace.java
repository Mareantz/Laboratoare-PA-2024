package com.smartcity.parkingmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    @JsonBackReference(value="parkingLot-parkingSpaces")
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingSpace", cascade = CascadeType.ALL)
    @JsonManagedReference(value="parkingSpace-reservations")
    private Set<Reservation> reservations = new HashSet<>();

    private boolean isReserved;

    // Getters and setters
    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setAvailable(boolean available) {
        isReserved = available;
    }
}
