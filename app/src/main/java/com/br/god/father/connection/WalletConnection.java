package com.br.god.father.connection;

import com.br.god.father.model.Customer;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WalletConnection {

    @GET("/customer")
    Call<Customer> registerCustomer();

}
