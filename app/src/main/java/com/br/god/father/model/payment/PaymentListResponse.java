package com.br.god.father.model.payment;

import com.br.god.father.model.CreditCardResponse;
import com.br.god.father.model.Event;
import com.br.god.father.model.Transaction;

import java.util.List;

public class PaymentListResponse {

    private String createdAt;
    private String paymentId;
    private String status;
    private String customerId;
    private String paymentMethod;
    private CreditCardResponse creditCard;
    private Transaction transaction;
    private List<Event> events;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CreditCardResponse getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardResponse creditCard) {
        this.creditCard = creditCard;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
