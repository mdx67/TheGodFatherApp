package com.br.god.father.model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Customer implements Serializable {

    @Nullable
    private String personType;
    private String fullName;
    private String country;
    private List<Document> documents;
    private List<Address> addresses;
    private Zns zns;

    public Customer() {
    }
}
