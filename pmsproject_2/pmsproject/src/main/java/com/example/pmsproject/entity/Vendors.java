
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
        name = "vendors"
)
public class Vendors {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "vendor_id"
    )
    private long vendorsId;
    @Column(
            name = "name",
            nullable = false
    )
    private String vendorsUsername;
    @Column(
            name = "vendors_email",
            nullable = false,
            unique = true
    )
    private String vendorsEmail;
    @Column(
            name = "vendors_phoneno",
            nullable = false,
            unique = true
    )
    private String vendorsPhoneno;
    @Column(
            name = "vendors_address",
            nullable = false
    )
    private String vendorsAddress;
    @Column(
            name = "vendors_password",
            nullable = false
    )
    private String vendorsPassword;


    public Vendors() {
    }

    public long getVendorsId() {
        return this.vendorsId;
    }

    public void setVendorsId(long ownerId) {
        this.vendorsId = ownerId;
    }

    public String getVendorsUsername() {
        return this.vendorsUsername;
    }

    public void setVendorsUsername(String ownerUsername) {
        this.vendorsUsername = ownerUsername;
    }

    public String getVendorsPassword() {
        return this.vendorsPassword;
    }

    public void setVendorsPassword(String ownerPassword) {
        this.vendorsPassword = ownerPassword;
    }

    public String getVendorsEmail() {
        return this.vendorsEmail;
    }

    public void setVendorsEmail(String ownerEmail) {
        this.vendorsEmail = ownerEmail;
    }

    public String getVendorsPhoneno() {
        return this.vendorsPhoneno;
    }

    public void setOwnerPhoneno(String ownerPhoneno) {
        this.vendorsPhoneno = ownerPhoneno;
    }

    public String getOwnerAddress() {
        return this.vendorsAddress;
    }

    public void setVendorsAddress(String vendorsAddress) {
        this.vendorsAddress = vendorsAddress;
    }





    public String toString() {
        return "Vendors{vendorsId=" + this.vendorsId + ", vendorsUsername='" + this.vendorsUsername + "', ownerEmail='" + this.vendorsEmail + "', ownerPhoneno='" + this.vendorsPhoneno + "', ownerAddress='" + this.vendorsAddress + "', ownerPassword='" + this.vendorsPassword + "'}";
    }
}