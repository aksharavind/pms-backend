package com.example.pmsproject.repository;

import com.example.pmsproject.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByOwnerUsername(String ownerUsername);
}
