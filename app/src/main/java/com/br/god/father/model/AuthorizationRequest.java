package com.br.god.father.model;

import java.util.Map;

import lombok.Data;

@Data
public class AuthorizationRequest {

    private String intent;
    private Payment payment;
    private Transaction transaction;
    private Map customFields;

}
