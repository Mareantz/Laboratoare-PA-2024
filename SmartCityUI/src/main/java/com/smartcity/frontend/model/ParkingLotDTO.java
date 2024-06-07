package com.smartcity.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingLotDTO
{
    private Long id;
    private String name;
    private String address;
    private int capacity;
    private int availableSpaces;
}