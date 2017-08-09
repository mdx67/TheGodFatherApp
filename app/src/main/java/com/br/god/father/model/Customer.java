package com.br.god.father.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Customer implements Serializable {

    @JsonIgnore
    private String id;
    private String personType;
    private String fullName;
    private String country;
    private List<Document> documents;
    private List<Address> addresses;
    private Zns zns;

    public Customer() {
    }
}
