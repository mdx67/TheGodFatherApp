package com.br.god.father.model;

import java.util.Map;

public class SubscriptionRequest {

    private String externalId;
    private String originApp;
    private String description;
    private String type;
    private String callback;
    private Offer offer;
    private Money price;
    private Payment payment;
    private Recurring recurring;
    private Map customFields;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getOriginApp() {
        return originApp;
    }

    public void setOriginApp(String originApp) {
        this.originApp = originApp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Recurring getRecurring() {
        return recurring;
    }

    public void setRecurring(Recurring recurring) {
        this.recurring = recurring;
    }

    public Map getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map customFields) {
        this.customFields = customFields;
    }
}
