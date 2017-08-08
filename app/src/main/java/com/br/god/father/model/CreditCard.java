package com.br.god.father.model;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCard {

    private String type;
    private String cardHolderName;
    private String bin;
    private String lastDigits;
    private String expirationDate;
    private String brand;
    private String externalToken;
    private Address billingAddress;
    private Map customFields;

    public CreditCard(String type, String cardHolderName, String bin, String lastDigits, String expirationDate, String brand, String externalToken) {
        this.type = type;
        this.cardHolderName = cardHolderName;
        this.bin = bin;
        this.lastDigits = lastDigits;
        this.expirationDate = expirationDate;
        this.brand = brand;
        this.externalToken = externalToken;
    }
}
