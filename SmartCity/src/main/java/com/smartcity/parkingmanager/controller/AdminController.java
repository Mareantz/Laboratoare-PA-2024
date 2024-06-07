package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.model.User;
import com.smartcity.parkingmanager.service.UserService;
import com.smartcity.parkingmanager.service.ReservationService;
import com.smartcity.parkingmanager.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController
{
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ADMIN);
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin user created successfully");
    }

    @PostMapping("/clearReservations")
    public ResponseEntity<String> clearAllReservations()
    {
        reservationService.clearAllReservations();
        return ResponseEntity.ok("All reservations cleared and parking lots reset");
    }
}