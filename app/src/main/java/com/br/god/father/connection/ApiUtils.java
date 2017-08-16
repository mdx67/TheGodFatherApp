package com.br.god.father.connection;

public class ApiUtils {

    public static Connection getConnection(String baseUrl) {
            return RetrofitClient.getClient(baseUrl).create(Connection.class);
    }
}
