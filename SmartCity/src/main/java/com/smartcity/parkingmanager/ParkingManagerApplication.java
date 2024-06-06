package com.smartcity.parkingmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.smartcity.parkingmanager")
@EnableScheduling
public class ParkingManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingManagerApplication.class, args);
    }
}
