package com.br.god.father.model;

import java.util.Map;

import lombok.Data;

@Data
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

}
