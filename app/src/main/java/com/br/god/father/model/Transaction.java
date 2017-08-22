package com.br.god.father.model;

import java.util.List;

public class Transaction {

    private String externalId;
    private TransactionDescription description;
    private String type;
    private Money price;
    private TransactionDetail details;
    private List<TransactionItem> items;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public TransactionDescription getDescription() {
        return description;
    }

    public void setDescription(TransactionDescription description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public TransactionDetail getDetails() {
        return details;
    }

    public void setDetails(TransactionDetail details) {
        this.details = details;
    }

    public List<TransactionItem> getItems() {
        return items;
    }

    public void setItems(List<TransactionItem> items) {
        this.items = items;
    }
}
