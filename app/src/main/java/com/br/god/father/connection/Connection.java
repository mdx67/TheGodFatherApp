package com.br.god.father.connection;

import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Subscription;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Connection {

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/register/customer")
    Call<Customer> registerCustomer(@Body Customer customer);

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/register/creditCard")
    Call<CreditCard> registerCreditCard(@Body CreditCard creditCard);

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/register/plan")
    Call<Subscription> subscriptionPlan(@Body Subscription subscription);

}
