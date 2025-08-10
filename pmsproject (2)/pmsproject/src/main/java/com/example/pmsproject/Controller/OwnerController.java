package com.example.pmsproject.Controller;

import com.example.pmsproject.Service.OwnerService;
import com.example.pmsproject.entity.Owner;
import com.example.pmsproject.repository.OwnerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/owners"})
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerController() {
    }

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return this.ownerService.createOwner(owner);
    }

    @GetMapping({"/{id}"})
    public Optional<Owner> getOwnerById(@PathVariable Long id) {
        return this.ownerService.getOwnerById(id);
    }

    @PutMapping({"/{id}"})
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        owner.setOwnerId(id);
        return this.ownerService.updateOwner(owner);
    }

    @DeleteMapping({"/{id}"})
    public void deleteOwner(@PathVariable Long id) {
        this.ownerService.deleteOwner(id);
    }

    @GetMapping({"/username/{ownerUsername}"})
    public Owner getOwnerByUsername(@PathVariable String ownerUsername) {
        return this.ownerService.getOwnerByUsername(ownerUsername);
    }

    @PostMapping({"/register"})
    public ResponseEntity<String> registerOwner(@RequestBody Owner owner) {
        return this.ownerService.registerOwner(owner) ? ResponseEntity.ok("Owner registered successfully") : ResponseEntity.badRequest().body("Owner already exists");
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> loginOwner(@RequestBody Owner loginRequest) {
        Owner owner = this.ownerRepository.findByOwnerUsername(loginRequest.getOwnerUsername());
        return owner != null && owner.getOwnerPassword().equals(loginRequest.getOwnerPassword()) ? ResponseEntity.ok(owner) : ResponseEntity.status(401).body("Invalid credentials");
    }
}