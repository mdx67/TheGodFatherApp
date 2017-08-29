package com.br.god.father.model;

public class Validity {

    private String period;
    private Integer duration;
    private Boolean unlimited;

    public Validity() {
    }

    public Validity(String period, Integer duration, Boolean unlimited) {
        this.period = period;
        this.duration = duration;
        this.unlimited = unlimited;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getUnlimited() {
        return unlimited;
    }

    public void setUnlimited(Boolean unlimited) {
        this.unlimited = unlimited;
    }
}
