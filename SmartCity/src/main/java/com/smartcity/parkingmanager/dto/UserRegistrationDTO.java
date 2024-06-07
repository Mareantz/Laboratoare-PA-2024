package com.smartcity.parkingmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDTO
{
    private String username;
    private String password;
    private String email;
}