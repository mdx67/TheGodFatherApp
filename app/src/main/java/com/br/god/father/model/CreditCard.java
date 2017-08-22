package com.br.god.father.model;

import java.util.Map;

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

    public CreditCard() {
    }

    public CreditCard(String type, String cardHolderName, String bin, String lastDigits, String expirationDate, String brand, String externalToken) {
        this.type = type;
        this.cardHolderName = cardHolderName;
        this.bin = bin;
        this.lastDigits = lastDigits;
        this.expirationDate = expirationDate;
        this.brand = brand;
        this.externalToken = externalToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLastDigits() {
        return lastDigits;
    }

    public void setLastDigits(String lastDigits) {
        this.lastDigits = lastDigits;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExternalToken() {
        return externalToken;
    }

    public void setExternalToken(String externalToken) {
        this.externalToken = externalToken;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Map getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map customFields) {
        this.customFields = customFields;
    }
}
