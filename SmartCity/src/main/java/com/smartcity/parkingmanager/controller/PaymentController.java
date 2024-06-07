package com.smartcity.parkingmanager.controller;

import com.smartcity.parkingmanager.model.Payment;
import com.smartcity.parkingmanager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController
{
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAllPayments()
    {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id)
    {
        return paymentService.getPaymentById(id);
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment)
    {
        return paymentService.savePayment(payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id)
    {
        paymentService.deletePayment(id);
    }
}