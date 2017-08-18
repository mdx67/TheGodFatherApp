package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Recurring {

    private String starDate;
    public  Integer duration;
    private String period;
    private Integer cycles;

    public Recurring(String starDate, Integer duration, String period, Integer cycles) {
        this.starDate = starDate;
        this.duration = duration;
        this.period = period;
        this.cycles = cycles;
    }
}
