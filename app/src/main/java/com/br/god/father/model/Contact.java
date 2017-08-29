package com.br.god.father.model;

import java.util.Date;

public class Contact {

    private Long id;
    private ContactType contactType;
    private Date createdAt;
    private String customerId;
    private ContactContent content;
    private String status;

    public Contact() {
    }

    public Contact(ContactType contactType, Date createdAt, ContactContent content) {
        this.contactType = contactType;
        this.createdAt = createdAt;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ContactContent getContent() {
        return content;
    }

    public void setContent(ContactContent content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
