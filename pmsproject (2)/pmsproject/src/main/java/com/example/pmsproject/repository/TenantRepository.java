package com.example.pmsproject.repository;

import com.example.pmsproject.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant,Long> {
    Tenant findByTenantUsername(String tenantUsername);
}
