package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ParkingLotDetailDTO
{
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int availableSpaces;
    private List<ParkingSpaceDTO> parkingSpaces;
    private List<ReservationDTO> reservations;
}