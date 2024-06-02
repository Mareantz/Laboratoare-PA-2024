package com.smartcity.parkingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.smartcity.parkingmanager")
public class ParkingManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingManagerApplication.class, args);
    }
}
