package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Payment {

    private String method;
    private String methodId;

    public Payment(String method, String methodId) {
        this.method = method;
        this.methodId = methodId;
    }
}
