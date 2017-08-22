package com.br.god.father.model;

import java.util.Map;

public class AuthorizationRequest {

    private String intent;
    private Payment payment;
    private Transaction transaction;
    private Map customFields;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map customFields) {
        this.customFields = customFields;
    }
}
