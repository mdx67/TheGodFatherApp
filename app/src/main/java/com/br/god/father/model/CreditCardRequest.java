package com.br.god.father.model;

import java.util.Map;

public class CreditCardRequest {

    private String type;
    private String holder;
    private String bin;
    private String lastDigits;
    private String expirationDate;
    private String brand;
    private String externalToken;
    private Address address;
    private Map customFields;

    public CreditCardRequest() {
    }

    public CreditCardRequest(String type, String holder, String bin, String lastDigits, String expirationDate, String brand, String externalToken) {
        this.type = type;
        this.holder = holder;
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

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map customFields) {
        this.customFields = customFields;
    }
}
