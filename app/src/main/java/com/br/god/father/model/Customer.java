package com.br.god.father.model;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {

    private String id;
    private String personType;
    private String fullName;
    private String country;
    private List<Document> documents;
    private List<Address> addresses;
    private Zns zns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Zns getZns() {
        return zns;
    }

    public void setZns(Zns zns) {
        this.zns = zns;
    }
}
