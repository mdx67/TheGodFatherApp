package com.br.god.father.model;

import lombok.Data;

@Data
public class RegisterCustomerRequest {

    private Customer customer;
    private CreditCard creditCard;
}
