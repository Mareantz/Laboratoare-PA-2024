package com.smartcity.parkingmanager.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spaceId;

    @ManyToOne
    @JoinColumn(name = "lot_id", nullable = false)
    private ParkingLot parkingLot;

    @Column(nullable = false)
    private String spaceNumber;

    private boolean isReserved;

    @OneToMany(mappedBy = "parkingSpace", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    public Long getSpaceId()
    {
        return spaceId;
    }

    public void setSpaceId(Long spaceId)
    {
        this.spaceId = spaceId;
    }

    public ParkingLot getParkingLot()
    {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot)
    {
        this.parkingLot = parkingLot;
    }

    public String getSpaceNumber()
    {
        return spaceNumber;
    }

    public void setSpaceNumber(String spaceNumber)
    {
        this.spaceNumber = spaceNumber;
    }

    public boolean isReserved()
    {
        return isReserved;
    }

    public void setReserved(boolean reserved)
    {
        isReserved = reserved;
    }

    public Set<Reservation> getReservations()
    {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations)
    {
        this.reservations = reservations;
    }
}
