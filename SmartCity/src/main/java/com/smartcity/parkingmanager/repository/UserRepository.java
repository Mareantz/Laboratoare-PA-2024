package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
}