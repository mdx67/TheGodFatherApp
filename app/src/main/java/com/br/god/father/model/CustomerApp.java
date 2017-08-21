package com.br.god.father.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerApp implements Serializable{

    private String id;
    private String name;

    public CustomerApp(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
