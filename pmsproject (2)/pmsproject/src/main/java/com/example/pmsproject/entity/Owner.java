package com.example.pmsproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(
        name = "owner"
)
public class Owner {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "owner_id"
    )
    private long ownerId;
    @Column(
            name = "owner_username",
            nullable = false
    )
    private String ownerUsername;
    @Column(
            name = "owner_email",
            nullable = false,
            unique = true
    )
    private String ownerEmail;
    @Column(
            name = "owner_phoneno",
            nullable = false,
            unique = true
    )
    private String ownerPhoneno;
    @Column(
            name = "owner_address",
            nullable = false
    )
    private String ownerAddress;
    @Column(
            name = "owner_password",
            nullable = false
    )
    private String ownerPassword;
    @OneToMany(
            mappedBy = "owner",
            cascade = {CascadeType.ALL}
    )
    @JsonIgnore
    private List<Property> properties;

    public Owner() {
    }

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerUsername() {
        return this.ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getOwnerPassword() {
        return this.ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    public String getOwnerEmail() {
        return this.ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhoneno() {
        return this.ownerPhoneno;
    }

    public void setOwnerPhoneno(String ownerPhoneno) {
        this.ownerPhoneno = ownerPhoneno;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String toString() {
        return "Owner{ownerId=" + this.ownerId + ", ownerUsername='" + this.ownerUsername + "', ownerEmail='" + this.ownerEmail + "', ownerPhoneno='" + this.ownerPhoneno + "', ownerAddress='" + this.ownerAddress + "', ownerPassword='" + this.ownerPassword + "'}";
    }
}
