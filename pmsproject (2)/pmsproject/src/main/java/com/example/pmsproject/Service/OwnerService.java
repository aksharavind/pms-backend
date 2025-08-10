package com.example.pmsproject.Service;

import com.example.pmsproject.entity.Owner;
import com.example.pmsproject.repository.OwnerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerService() {
    }

    public Owner createOwner(Owner owner) {
        return (Owner)this.ownerRepository.save(owner);
    }

    public Optional<Owner> getOwnerById(Long ownerId) {
        return this.ownerRepository.findById(ownerId);
    }

    public Owner updateOwner(Owner owner) {
        return (Owner)this.ownerRepository.save(owner);
    }

    public void deleteOwner(Long ownerId) {
        this.ownerRepository.deleteById(ownerId);
    }

    public Owner getOwnerByUsername(String ownerUsername) {
        return this.ownerRepository.findByOwnerUsername(ownerUsername);
    }

    public boolean registerOwner(Owner owner) {
        if (this.ownerRepository.findByOwnerUsername(owner.getOwnerUsername()) != null) {
            return false;
        } else {
            this.ownerRepository.save(owner);
            return true;
        }
    }

    public boolean authenticateOwner(String ownerUsername, String password) {
        Owner owner = this.ownerRepository.findByOwnerUsername(ownerUsername);
        return owner != null && owner.getOwnerPassword().equals(password);
    }
}
