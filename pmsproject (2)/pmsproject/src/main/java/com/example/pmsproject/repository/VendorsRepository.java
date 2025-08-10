package com.example.pmsproject.repository;

import com.example.pmsproject.entity.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorsRepository  extends JpaRepository<Vendors,Long> {
    Vendors findByVendorsUsername(String vendorsUsername);
}
