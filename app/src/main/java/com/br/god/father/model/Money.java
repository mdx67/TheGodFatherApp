package com.br.god.father.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Money {

    private String currency;
    private Integer amount;
    private Integer scale;
    private BigDecimal amountAsBigDecimal;

    public Money(String currency, Integer amount, Integer scale) {
        this.currency = currency;
        this.amount = amount;
        this.scale = scale;
    }
}
