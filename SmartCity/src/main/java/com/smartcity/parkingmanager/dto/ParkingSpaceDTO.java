package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingSpaceDTO
{
    private Long id;
    private boolean reserved;
}