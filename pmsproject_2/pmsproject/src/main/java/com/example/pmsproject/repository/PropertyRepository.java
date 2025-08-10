package com.example.pmsproject.repository;

import com.example.pmsproject.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {


    List<Property> findByStatus(String status);

    List<Property> findByLocationContainingIgnoreCase(String location);



}
