package com.smartcity.parkingmanager.dto;

import java.util.List;

public class ParkingLotDetailDTO {
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int availableSpaces;
    private List<ParkingSpaceDTO> parkingSpaces;
    private List<ReservationDTO> reservations;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public List<ParkingSpaceDTO> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpaceDTO> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public List<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDTO> reservations) {
        this.reservations = reservations;
    }
}
