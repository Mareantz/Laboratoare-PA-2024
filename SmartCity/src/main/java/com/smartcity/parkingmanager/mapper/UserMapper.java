package com.smartcity.parkingmanager.mapper;

import com.smartcity.parkingmanager.dto.UserDTO;
import com.smartcity.parkingmanager.dto.UserDetailDTO;
import com.smartcity.parkingmanager.dto.ReservationDTO;
import com.smartcity.parkingmanager.dto.UserRegistrationDTO;
import com.smartcity.parkingmanager.model.Reservation;
import com.smartcity.parkingmanager.model.User;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());  // Assuming role is an enum
        return dto;
    }

    public UserDetailDTO toUserDetailDTO(User user) {
        UserDetailDTO dto = new UserDetailDTO();
        dto.setId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());  // Assuming role is an enum
        dto.setReservations(user.getReservations().stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList()));
        return dto;
    }

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

    public User toUser(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        return user;
    }
}
