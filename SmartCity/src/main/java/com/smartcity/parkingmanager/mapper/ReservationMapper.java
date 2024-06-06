package com.smartcity.parkingmanager.mapper;

import org.springframework.stereotype.Service;
import com.smartcity.parkingmanager.dto.ReservationDTO;
import com.smartcity.parkingmanager.model.Reservation;



@Service
public class ReservationMapper {

    public ReservationDTO toReservationDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setReservationId(reservation.getReservationId());
        dto.setUserId(reservation.getUser().getUserId());
        dto.setParkingLotId(reservation.getParkingLot().getParkingLotId());
        dto.setParkingSpaceId(reservation.getParkingSpace().getParkingSpaceId());
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        return dto;
    }

}
