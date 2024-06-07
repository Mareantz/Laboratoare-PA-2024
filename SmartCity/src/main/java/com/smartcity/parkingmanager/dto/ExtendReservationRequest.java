package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExtendReservationRequest
{
    private Long reservationId;
    private int minutes;
}