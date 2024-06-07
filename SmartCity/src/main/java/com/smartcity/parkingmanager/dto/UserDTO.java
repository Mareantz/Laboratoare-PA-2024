package com.smartcity.parkingmanager.dto;

import com.smartcity.parkingmanager.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO
{
    private Long id;
    private String username;
    private String email;
    private UserRole role;
}
