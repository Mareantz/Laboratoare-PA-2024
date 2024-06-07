package com.smartcity.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingSpaceDTO
{
    private Long id;
    private boolean reserved;
}