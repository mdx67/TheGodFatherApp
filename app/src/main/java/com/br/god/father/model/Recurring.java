package com.br.god.father.model;

import java.util.Date;

public class Recurring {

    private String startDate;
    public Integer duration;
    private String period;
    private Integer cycles;

    public Recurring() {
    }

    public Recurring(String startDate, Integer duration, String period, Integer cycles) {
        this.startDate = startDate;
        this.duration = duration;
        this.period = period;
        this.cycles = cycles;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
