package com.example.pmsproject.Controller;


import com.example.pmsproject.Service.VendorsService;
import com.example.pmsproject.entity.Tenant;
import com.example.pmsproject.entity.Vendors;
import com.example.pmsproject.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vendors")
public class VendorsController {
     @Autowired
    private VendorsService vendorsService;
    @Autowired
    private VendorsRepository vendorsRepository;

     @PostMapping
    public Vendors createVendors(@RequestBody Vendors vendors)
     {
         return  vendorsService.createVendors(vendors);
     }

     @GetMapping("/{id}")
    public Optional<Vendors> getVendorById(@PathVariable Long id)
     {
         return  vendorsService.getVendorsById(id);
     }
     @PutMapping("/{id}")
    public  Optional<Vendors> getVendorsById(@PathVariable Long id)
     {
         return  vendorsService.getVendorsById(id);
     }

     @DeleteMapping("/{id}")
    public  void deleteOwner(@PathVariable Long id)
     {
         vendorsService.deleteVendors(id);
     }

    @GetMapping({"/username/{vendorsUsername}"})
    public Vendors getVendorsByUsername(@PathVariable String vendorsUsername) {
        return this.vendorsService.getVendorsByUsername(vendorsUsername);
    }
    @PostMapping({"/register"})
    public ResponseEntity<String> registerVendors(@RequestBody Vendors vendors) {
        return this.vendorsService.registerVendors(vendors) ? ResponseEntity.ok("vendor registered successfully") : ResponseEntity.badRequest().body("vendor already exists");
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> loginVendors(@RequestBody Vendors loginRequest) {
        Vendors vendors = this.vendorsRepository.findByVendorsUsername(loginRequest.getVendorsUsername());
        return vendors != null && vendors.getVendorsPassword().equals(loginRequest.getVendorsPassword()) ? ResponseEntity.ok(vendors) : ResponseEntity.status(401).body("Invalid credentials");
    }
}
