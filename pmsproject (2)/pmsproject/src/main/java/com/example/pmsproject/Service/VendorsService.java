package com.example.pmsproject.Service;

import com.example.pmsproject.entity.Vendors;
import com.example.pmsproject.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class VendorsService {

    @Autowired
    private VendorsRepository vendorsRepository;

    public Vendors createVendors(Vendors vendors)
    {
        return  vendorsRepository.save(vendors);
    }
    public Optional<Vendors> getVendorsById(Long vendorsId)
    {
        return  vendorsRepository.findById(vendorsId);
    }
    public void deleteVendors(Long vendorsId)
    {
        vendorsRepository.deleteById(vendorsId);
    }
    public  Vendors  updateVendors(Vendors vendors)
    {
        return  vendorsRepository.save(vendors);
    }
    public Vendors getVendorsByUsername(String vendorsUsername) {
        return this.vendorsRepository.findByVendorsUsername(vendorsUsername);
    }
    public boolean registerVendors(Vendors vendors) {
        if (this.vendorsRepository.findByVendorsUsername(vendors.getVendorsUsername()) != null) {
            return false;
        } else {
            this.vendorsRepository.save(vendors);
            return true;
        }
    }

    public boolean authenticateVendors(String vendorsUsername, String password) {
        Vendors vendors = this.vendorsRepository.findByVendorsUsername(vendorsUsername);
        return vendors != null && vendors.getVendorsPassword().equals(password);
    }
}
