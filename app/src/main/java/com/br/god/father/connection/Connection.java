package com.br.god.father.connection;

import com.br.god.father.model.Customer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Connection {

    @GET("/customer")
    Call<Customer> registerCustomer();
//    Call<Customer> registerCustomer(Customer customer);

}
