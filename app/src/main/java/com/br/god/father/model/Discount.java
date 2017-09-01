package com.br.god.father.model;

import java.util.List;

public class Discount {

    private String description;
    private Integer amountAsPercent;
    private Money amountAsCurrency;
    private List<Cycle> cycles;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmountAsPercent() {
        return amountAsPercent;
    }

    public void setAmountAsPercent(Integer amountAsPercent) {
        this.amountAsPercent = amountAsPercent;
    }

    public Money getAmountAsCurrency() {
        return amountAsCurrency;
    }

    public void setAmountAsCurrency(Money amountAsCurrency) {
        this.amountAsCurrency = amountAsCurrency;
    }

    public List<Cycle> getCycles() {
        return cycles;
    }

    public void setCycles(List<Cycle> cycles) {
        this.cycles = cycles;
    }
}
