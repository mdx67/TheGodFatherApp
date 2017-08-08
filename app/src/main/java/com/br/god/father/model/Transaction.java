package com.br.god.father.model;

import java.util.List;

import lombok.Data;

@Data
public class Transaction {

    private String externalId;
    private TransactionDescription description;
    private String type;
    private Money price;
    private TransactionDetail details;
    private List<TransactionItem> items;

}
