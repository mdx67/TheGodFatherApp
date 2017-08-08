package com.br.god.father.model;

import lombok.Data;

@Data
public class Payment {

    private String method;
    private String methodId;

    public Payment(String method, String methodId) {
        this.method = method;
        this.methodId = methodId;
    }
}
