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
        name = "tenant"
)
public class Tenant {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "tenant_id"
    )
    private long tenantId;
    @Column(
            name = "tenant_username",
            nullable = false
    )
    private String tenantUsername;
    @Column(
            name = "tenant_email",
            nullable = false

    )
    private String tenantEmail;
    @Column(
            name = "tenant_phoneno",
            nullable = false

    )
    private String tenantPhoneno;
    @Column(
            name = "tenant_address",
            nullable = false
    )
    private String tenantAddress;
    @Column(
            name = "tenant_password",
            nullable = false
    )
    private String tenantPassword;
    @OneToMany(
            mappedBy = "tenant",
            cascade = {CascadeType.ALL}
    )
    @JsonIgnore
    private List<Property> properties;

    public Tenant() {
    }

    public long getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantUsername() {
        return this.tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public String getTenantPassword() {
        return this.tenantPassword;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword;
    }

    public String getTenantEmail() {
        return this.tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public String getTenantPhoneno() {
        return this.tenantPhoneno;
    }

    public void setTenantPhoneno(String tenantPhoneno) {
        this.tenantPhoneno =tenantPhoneno;
    }

    public String getTenantAddress() {
        return this.tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String toString() {
        return "Tenant{tenantId=" + this.tenantId + ", tenantUsername='" + this.tenantUsername + "', tenantEmail='" + this.tenantEmail + "', tenantPhoneno='" + this.tenantPhoneno + "', tenantAddress='" + this.tenantAddress + "', tenantPassword='" + this.tenantPassword + "'}";
    }
}

