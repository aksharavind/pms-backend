package com.example.pmsproject.repository;

import com.example.pmsproject.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository  extends JpaRepository<Payments, Long> {
}
