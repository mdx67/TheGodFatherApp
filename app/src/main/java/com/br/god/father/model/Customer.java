package com.br.god.father.model;

import android.support.annotation.Nullable;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {

    @Nullable
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private CreditCard creditCard;

    public Customer() {
    }
}
