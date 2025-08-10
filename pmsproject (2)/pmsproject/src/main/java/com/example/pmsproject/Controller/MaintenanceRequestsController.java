package com.example.pmsproject.Controller;


import com.example.pmsproject.Service.MaintenanceRequestsService;
import com.example.pmsproject.entity.MaintenanceRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class MaintenanceRequestsController {

  @Autowired
    private MaintenanceRequestsService maintenanceRequestsService;

  @PostMapping
    public MaintenanceRequests addRequest(@RequestBody MaintenanceRequests maintenanceRequests)
  {
      return  maintenanceRequestsService.addRequest(maintenanceRequests);
  }

  @GetMapping("{id}")
    public Optional<MaintenanceRequests> getRequestById(@PathVariable Long id)
  {
      return  maintenanceRequestsService.getRequestById(id);
  }

  @DeleteMapping("/{id}")
    public  void  deleteRequest(@PathVariable long id)
  {
      maintenanceRequestsService.deleteRequest(id);
  }
}
