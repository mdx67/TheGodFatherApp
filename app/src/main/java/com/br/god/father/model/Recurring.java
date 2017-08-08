package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Recurring {

    private String starDate;
    private String endDate;
    private String period;
    private Integer cycles;

    public Recurring(String starDate, String endDate, String period, Integer cycles) {
        this.starDate = starDate;
        this.endDate = endDate;
        this.period = period;
        this.cycles = cycles;
    }
}
