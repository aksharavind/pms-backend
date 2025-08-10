package com.example.pmsproject.Service;


import com.example.pmsproject.entity.Tenant;
import com.example.pmsproject.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    public Tenant createTenant(Tenant tenant)
    {
        return  tenantRepository.save(tenant);
    }
    public Optional<Tenant> getTenantById(Long tenantId)
    {
        return  tenantRepository.findById(tenantId);
    }
    public  void  deleteTenant(Long tenantId)
    {
        tenantRepository.deleteById(tenantId);
    }
    public  Tenant updateTenant(Tenant tenant)
    {
        return tenantRepository.save(tenant);
    }

    public Tenant getTenantByUsername(String tenantUsername) {
        return this.tenantRepository.findByTenantUsername(tenantUsername);
    }

    public boolean registerTenant(Tenant tenant) {
        if (this.tenantRepository.findByTenantUsername(tenant.getTenantUsername()) != null) {
            return false;
        } else {
            this.tenantRepository.save(tenant);
            return true;
        }
    }

    public boolean authenticateTenant(String tenantUsername, String password) {
        Tenant tenant = this.tenantRepository.findByTenantUsername(tenantUsername);
        return tenant != null && tenant.getTenantPassword().equals(password);
    }
}
