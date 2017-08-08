package com.br.god.father.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Money {

    private String currency;
    private Integer amount;
    private Integer scale;
    @JsonIgnore
    private BigDecimal amountAsBigDecimal;

    public Money(String currency, Integer amount, Integer scale) {
        this.currency = currency;
        this.amount = amount;
        this.scale = scale;
    }
}
