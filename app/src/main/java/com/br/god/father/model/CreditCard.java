package com.br.god.father.model;

import lombok.Data;

@Data
public class CreditCard {

    private String number;
    private String validationDate;
    private String cvv;

    public CreditCard() {
    }

    public CreditCard(String number, String validationDate, String cvv) {
        this.number = number;
        this.validationDate = validationDate;
        this.cvv = cvv;
    }
}
