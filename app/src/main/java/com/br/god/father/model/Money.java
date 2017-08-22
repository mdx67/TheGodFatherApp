package com.br.god.father.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class Money {

    private String currency;
    private Integer amount;
    private Integer scale;
    @JsonIgnore
    private BigDecimal amountAsBigDecimal;

    public Money() {
    }

    public Money(String currency, Integer amount, Integer scale) {
        this.currency = currency;
        this.amount = amount;
        this.scale = scale;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public BigDecimal getAmountAsBigDecimal() {
        return amountAsBigDecimal;
    }

    public void setAmountAsBigDecimal(BigDecimal amountAsBigDecimal) {
        this.amountAsBigDecimal = amountAsBigDecimal;
    }
}
