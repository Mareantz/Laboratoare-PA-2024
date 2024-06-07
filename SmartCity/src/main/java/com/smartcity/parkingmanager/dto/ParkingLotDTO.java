package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingLotDTO
{
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int availableSpaces;
}