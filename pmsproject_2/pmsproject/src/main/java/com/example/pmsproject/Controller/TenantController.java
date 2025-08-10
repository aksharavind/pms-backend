package com.example.pmsproject.Controller;


import com.example.pmsproject.Service.TenantService;
import com.example.pmsproject.entity.Owner;
import com.example.pmsproject.entity.Tenant;
import com.example.pmsproject.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;
    @Autowired
    private TenantRepository tenantRepository;

    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantService.createTenant(tenant);
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenantById(@PathVariable Long id) {
        return tenantService.getTenantById(id);
    }

    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant tenant) {
        tenant.setTenantId(id);
        return tenantService.updateTenant(tenant);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }

    @GetMapping({"/username/{tenantUsername}"})
    public Tenant getTenantByUsername(@PathVariable String tenantUsername) {
        return this.tenantService.getTenantByUsername(tenantUsername);
    }

    @PostMapping({"/register"})
    public ResponseEntity<String> registerTenant(@RequestBody Tenant tenant) {
        return this.tenantService.registerTenant(tenant) ? ResponseEntity.ok("tenant registered successfully") : ResponseEntity.badRequest().body("tenant already exists");
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> loginTenant(@RequestBody Tenant loginRequest) {
        Tenant tenant = this.tenantRepository.findByTenantUsername(loginRequest.getTenantUsername());
        return tenant != null && tenant.getTenantPassword().equals(loginRequest.getTenantPassword()) ? ResponseEntity.ok(tenant) : ResponseEntity.status(401).body("Invalid credentials");

    }
}
