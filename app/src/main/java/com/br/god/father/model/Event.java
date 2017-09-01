package com.br.god.father.model;

public class Event {

    private String id;
    private String createdAt;
    private String type;
    private Money transactionAmount;
    private Provider provider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Money getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Money transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
