package com.br.god.father.connection;

import java.util.HashMap;
import java.util.Map;

public class ApiUtils {

    public static Connection getConnection(String baseUrl) {
        return RetrofitClient.getClient(baseUrl).create(Connection.class);
    }

    public static Map<String, String> buildHeaders(String customerId) {
        Map<String, String> headers = buildHeaders();

        
        return headers;
    }

    public static Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");

        return headers;
    }
}
