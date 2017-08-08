package com.br.god.father.model;

import java.util.Date;

import lombok.Data;

@Data
public class Recurring {

    private Date starDate;
    private Integer duration;
    private String period;
    private Integer cycles;

}
