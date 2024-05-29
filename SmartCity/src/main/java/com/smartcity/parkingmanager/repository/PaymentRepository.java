package com.smartcity.parkingmanager.repository;

import com.smartcity.parkingmanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}