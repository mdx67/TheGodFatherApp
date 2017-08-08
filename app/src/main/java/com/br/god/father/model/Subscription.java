package com.br.god.father.model;

import lombok.Data;

@Data
public class Subscription {

    private Offer offer;
    private Money price;
    private Payment payment;
    private Recurring recurring;
//    private Map customFields;

    public Subscription() {
    }
}
