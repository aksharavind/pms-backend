package com.example.pmsproject.Service;


import com.example.pmsproject.entity.MaintenanceRequests;
import com.example.pmsproject.repository.MaintenanceRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MaintenanceRequestsService {
    @Autowired
    private MaintenanceRequestsRepository maintenanceRequestsRepository;

    public MaintenanceRequests addRequest(MaintenanceRequests maintenanceRequests)
    {
        return  maintenanceRequestsRepository.save(maintenanceRequests);
    }
    public Optional<MaintenanceRequests> getRequestById(Long requestId)
    {
        return maintenanceRequestsRepository.findById(requestId);
    }
    public void deleteRequest(Long requestId)
    {
        maintenanceRequestsRepository.deleteById(requestId);
    }
}
