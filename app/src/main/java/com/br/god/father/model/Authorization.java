package com.br.god.father.model;

import java.util.Map;

import lombok.Data;

@Data
public class Authorization {

    private String intent;
    private Payment payment;
    private Transaction transaction;
    private Map customFields;

}
