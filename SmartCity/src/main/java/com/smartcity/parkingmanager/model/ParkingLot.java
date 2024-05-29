package com.smartcity.parkingmanager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parking_lots")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lotId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private int availableSpaces;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ParkingSpace> parkingSpaces;

    public Long getLotId()
    {
        return lotId;
    }

    public void setLotId(Long lotId)
    {
        this.lotId = lotId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getAvailableSpaces()
    {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces)
    {
        this.availableSpaces = availableSpaces;
    }

    public Set<ParkingSpace> getParkingSpaces()
    {
        return parkingSpaces;
    }

    public void setParkingSpaces(Set<ParkingSpace> parkingSpaces)
    {
        this.parkingSpaces = parkingSpaces;
    }
}
