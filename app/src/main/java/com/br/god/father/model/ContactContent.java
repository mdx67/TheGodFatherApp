package com.br.god.father.model;

public class ContactContent {

    private String name;
    private String email;

    public ContactContent() {
    }

    public ContactContent(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
