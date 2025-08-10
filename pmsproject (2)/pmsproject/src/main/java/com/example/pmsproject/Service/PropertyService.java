package com.example.pmsproject.Service;

import com.example.pmsproject.entity.Owner;
import com.example.pmsproject.entity.Property;
import com.example.pmsproject.repository.OwnerRepository;
import com.example.pmsproject.repository.PropertyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public PropertyService() {
    }

    public List<Property> getAllProperties() {
        return this.propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(Long propertyId) {
        return this.propertyRepository.findById(propertyId);
    }

    public Property addProperty(Property property, Long ownerId) {
        Owner owner = (Owner)this.ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));
        property.setOwner(owner);
        return (Property)this.propertyRepository.save(property);
    }

    public Property addProperty(Property property) {
        return (Property)this.propertyRepository.save(property);
    }

    public Property updateProperty(Long propertyId, Property propertyDetails) {
        if (propertyId == null) {
            throw new RuntimeException("Property ID cannot be null");
        } else {
            return (Property)this.propertyRepository.findById(propertyId).map((property) -> {
                property.setPropertyName(propertyDetails.getPropertyName());
                property.setType(propertyDetails.getType());
                property.setStatus(propertyDetails.getStatus());
                property.setAddress(propertyDetails.getAddress());
                property.setPrice(propertyDetails.getPrice());
                property.setImageUrl(propertyDetails.getImageUrl());
                return (Property)this.propertyRepository.save(property);
            }).orElseThrow(() -> new RuntimeException("Property not found"));
        }
    }

    public boolean deleteProperty(Long propertyId) {
        if (propertyId == null) {
            throw new RuntimeException("Property ID cannot be null");
        } else if (this.propertyRepository.existsById(propertyId)) {
            this.propertyRepository.deleteById(propertyId);
            return true;
        } else {
            return false;
        }
    }
    public List<Property> getAvailableProperties() {
        return propertyRepository.findByStatus("Available");
    }

    public List<Property> getPropertiesByLocation(String location) {
        return propertyRepository.findByLocationContainingIgnoreCase(location);
    }
}



