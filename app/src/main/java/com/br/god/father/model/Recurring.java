package com.br.god.father.model;

import java.util.Date;

public class Recurring {

    private Date starDate;
    public Integer duration;
    private String period;
    private Integer cycles;

    public Recurring() {
    }

    public Recurring(Date starDate, Integer duration, String period, Integer cycles) {
        this.starDate = starDate;
        this.duration = duration;
        this.period = period;
        this.cycles = cycles;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getCycles() {
        return cycles;
    }

    public void setCycles(Integer cycles) {
        this.cycles = cycles;
    }
}
