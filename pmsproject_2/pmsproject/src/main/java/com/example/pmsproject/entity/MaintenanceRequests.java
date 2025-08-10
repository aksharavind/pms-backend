package com.example.pmsproject.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "maintenance_req")
public class MaintenanceRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private long requestId;

    @Column(name = "issue_desc", nullable = false)
    private String issueDesc;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "request_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "response_date")
    @Temporal(TemporalType.DATE)
    private Date responseDate;

    @ManyToOne
    @JoinColumn(name = "propertyId")
    private  Property property;

    // Getters and Setters
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
    public  Property getProperty()
    {
        return  property;
    }
    public  void  setProperty(Property property)
    {
        this.property=property;
    }
}
