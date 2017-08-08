package com.br.god.father.model;

import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Recurring;

import java.util.Map;

import lombok.Data;

@Data
public class Subscription {

    private Offer offer;
    private Money price;
    private Payment payment;
    private Recurring recurring;
    private Map customFields;
}
