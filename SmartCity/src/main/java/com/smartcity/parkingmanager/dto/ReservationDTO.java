package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ReservationDTO
{
    private Long reservationId;
    private Long userId;
    private Long parkingLotId;
    private Long parkingSpaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}