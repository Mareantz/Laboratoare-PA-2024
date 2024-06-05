package com.smartcity.parkingmanager.dto;

import com.smartcity.parkingmanager.model.UserRole;

public class UserDTO {
    private Long id;
    private String username;
    private String email;  // Assuming users have an email address
    private UserRole role;   // Assuming users have a role like USER or ADMIN

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
