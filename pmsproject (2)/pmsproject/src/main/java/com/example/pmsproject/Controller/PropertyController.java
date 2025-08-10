package com.example.pmsproject.Controller;

import com.example.pmsproject.Service.PropertyService;
import com.example.pmsproject.entity.Property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import com.example.pmsproject.entity.Tenant;
import com.example.pmsproject.repository.PropertyRepository;
import com.example.pmsproject.repository.TenantRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api/properties"})
@CrossOrigin(
        origins = {"http://localhost:5173"}
)
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRepository propertyRepository;
    private TenantRepository tenantRepository;
    public PropertyController() {
    }

    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = this.propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = this.propertyService.getPropertyById(id);
        return (ResponseEntity)property.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping({"/owner"})
    public ResponseEntity<Property> addPropertyWithOwner(@RequestBody Property property, @RequestParam Long ownerId) {
        try {
            Property newProperty = this.propertyService.addProperty(property, ownerId);
            return new ResponseEntity(newProperty, HttpStatus.CREATED);
        } catch (RuntimeException var4) {
            return new ResponseEntity((MultiValueMap)null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property newProperty = this.propertyService.addProperty(property);
        return ResponseEntity.ok(newProperty);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        } else {
            Property updatedProperty = this.propertyService.updateProperty(id, propertyDetails);
            return updatedProperty != null ? ResponseEntity.ok(updatedProperty) : ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        } else {
            boolean isDeleted = this.propertyService.deleteProperty(id);
            return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search")
    public ResponseEntity<List<Property>> searchPropertiesByLocation(@RequestParam String location) {
        if (location == null || location.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Property> properties = propertyService.getPropertiesByLocation(location);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}/owner-phone")
    public ResponseEntity<String> getOwnerPhoneNumber(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property.isPresent() && property.get().getOwner() != null) {
            return ResponseEntity.ok(property.get().getOwner().getOwnerPhoneno());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}






