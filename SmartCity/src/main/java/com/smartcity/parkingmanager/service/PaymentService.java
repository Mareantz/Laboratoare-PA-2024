package com.smartcity.parkingmanager.service;

import com.smartcity.parkingmanager.model.Payment;
import com.smartcity.parkingmanager.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService
{
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId)
    {
        return paymentRepository.findById(paymentId).orElse(null);
    }

    public Payment savePayment(Payment payment)
    {
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId)
    {
        paymentRepository.deleteById(paymentId);
    }
}
