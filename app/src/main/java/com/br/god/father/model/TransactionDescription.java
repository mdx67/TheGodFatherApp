package com.br.god.father.model;

public class TransactionDescription {

    private String full;
    private String soft;

    public TransactionDescription() {
    }

    public TransactionDescription(String full, String soft) {
        this.full = full;
        this.soft = soft;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getSoft() {
        return soft;
    }

    public void setSoft(String soft) {
        this.soft = soft;
    }
}
