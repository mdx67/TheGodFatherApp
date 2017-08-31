package com.br.god.father.model;

import java.util.Map;

public class Error {

    private String code;
    private String message;
    private Map fields;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getFields() {
        return fields;
    }

    public void setFields(Map fields) {
        this.fields = fields;
    }
}
