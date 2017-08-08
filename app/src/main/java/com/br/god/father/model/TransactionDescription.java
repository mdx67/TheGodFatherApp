package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDescription {

    private String full;
    private String soft;

    public TransactionDescription(String full, String soft) {
        this.full = full;
        this.soft = soft;
    }
}
