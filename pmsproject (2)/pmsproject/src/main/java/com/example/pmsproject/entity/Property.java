package com.example.pmsproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "property"
)
public class Property {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "propertyId"
    )
    private Long propertyId;
    @Column(
            name = "property_name",
            nullable = false
    )
    private String propertyName;
    @Column(
            name = "type",
            nullable = false
    )
    private String type;
    @Column(
            name = "status",
            nullable = false
    )
    private String status;
    @Column(
            name = "address",
            nullable = false
    )
    private String address;
    @Column(
            name = "location",
            nullable = false
    )
    private String location;
    @Column(
            name = "price",
            nullable = false
    )
    private double price;
    @Column(
            name = "image_url"
    )
    private String imageUrl;
    @ManyToOne
    @JoinColumn(
            name = "owner_id"
    )
    private Owner owner;
    @ManyToOne
    @JoinColumn(
            name = "tenant_id"
    )
    private Tenant tenant;

    public Property() {
    }

    public Long getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Tenant getTenant() {
        return this.tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}