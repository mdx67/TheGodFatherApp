package com.br.god.father.model;

public class Payment {

    private String method;
    private String methodId;

    public Payment() {
    }

    public Payment(String method, String methodId) {
        this.method = method;
        this.methodId = methodId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }
}
