package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

    private String name;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
