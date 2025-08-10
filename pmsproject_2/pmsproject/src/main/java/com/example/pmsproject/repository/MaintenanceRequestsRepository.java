package com.example.pmsproject.repository;

import com.example.pmsproject.entity.MaintenanceRequests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRequestsRepository  extends JpaRepository<MaintenanceRequests,Long > {
    
}
