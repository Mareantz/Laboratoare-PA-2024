package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDetailDTO
{
    private Long id;
    private String username;
    private String email;
    private String role;
    private List<ReservationDTO> reservations;
}