package com.br.god.father.model;

import lombok.Data;

@Data
public class Zns {

    private String userId;

    public Zns() {
    }

    public Zns(String userId) {
        this.userId = userId;
    }
}
