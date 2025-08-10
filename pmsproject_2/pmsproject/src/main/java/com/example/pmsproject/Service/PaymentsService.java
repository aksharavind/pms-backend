package com.example.pmsproject.Service;


import com.example.pmsproject.entity.Payments;
import com.example.pmsproject.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentsService {
    @Autowired
    private PaymentsRepository paymentsRepository;

    public Payments generatePayments(Payments payments) {
        return paymentsRepository.save(payments);
    }
    public Optional<Payments> getPaymentById(Long paymentId) {
        return paymentsRepository.findById(paymentId);
    }

}
