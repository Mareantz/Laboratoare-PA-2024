package com.smartcity.parkingmanager.dto;

public class ParkingSpaceDTO {
    private Long id;
    private boolean reserved;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
