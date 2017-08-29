package com.br.god.father.model;

import java.io.Serializable;

public class CustomerApp implements Serializable {

    private String id;
    private String name;

    public CustomerApp() {
    }

    public CustomerApp(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerApp{id=" + this.getId() + ", name=" + this.getName() + "}";
    }
}
