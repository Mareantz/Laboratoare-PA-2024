package com.smartcity.parkingmanager.mapper;

import org.springframework.stereotype.Service;
import com.smartcity.parkingmanager.dto.ParkingLotDetailDTO;
import com.smartcity.parkingmanager.dto.ParkingSpaceDTO;
import com.smartcity.parkingmanager.dto.ReservationDTO;
import com.smartcity.parkingmanager.model.ParkingLot;
import com.smartcity.parkingmanager.model.ParkingSpace;
import com.smartcity.parkingmanager.model.Reservation;

import java.util.stream.Collectors;

@Service
public class ParkingLotMapper
{
    public ParkingLotDetailDTO toParkingLotDetailDTO(ParkingLot parkingLot)
    {
        ParkingLotDetailDTO dto = new ParkingLotDetailDTO();
        dto.setId(parkingLot.getParkingLotId());
        dto.setName(parkingLot.getName());
        dto.setAddress(parkingLot.getAddress());
        dto.setCapacity(parkingLot.getCapacity());
        dto.setAvailableSpaces(parkingLot.getAvailableSpaces());
        dto.setParkingSpaces(parkingLot.getParkingSpaces().stream()
                .map(this::toParkingSpaceDTO)
                .collect(Collectors.toList()));
        dto.setReservations(parkingLot.getReservations().stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public ParkingSpaceDTO toParkingSpaceDTO(ParkingSpace parkingSpace)
    {
        ParkingSpaceDTO dto = new ParkingSpaceDTO();
        dto.setId(parkingSpace.getParkingSpaceId());
        dto.setReserved(parkingSpace.isReserved());
        return dto;
    }

    public ReservationDTO toReservationDTO(Reservation reservation)
    {
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