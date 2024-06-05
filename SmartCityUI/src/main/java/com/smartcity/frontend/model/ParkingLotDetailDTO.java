package com.smartcity.frontend.model;

import java.util.List;

public class ParkingLotDetailDTO extends ParkingLotDTO {
    private List<ParkingSpaceDTO> parkingSpaces;
    private List<ReservationDTO> reservations;

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
