package com.br.god.father.connection;

import com.br.god.father.model.Authorization;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Subscription;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Connection {

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/register/customer")
    Call<Customer> registerCustomer(@Body Customer customer);

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/register/creditCard")
    Call<CreditCard> registerCreditCard(@Header("customerId") String customerId, @Body CreditCard creditCard);

    @Headers({"Content-Type: application/json"})
    @POST("mobile/register/plan")
    Call<Subscription> subscriptionPlan(@Header("customerId") String customerId, @Body Subscription subscription);

    @Headers({"Content-Type: application/json"})
    @POST("v1/payments")
    Call<AuthorizationResponse> authorize(@Header("X-Customer-Id") String customerId, @Body Authorization authorization);

    @Headers({"Content-Type: application/json"})
    @DELETE("v1/payments/{paymentId}")
    Call<AuthorizationResponse> cancel(@Header("X-Customer-Id") String customerId, @Path("paymentId") String paymentId);

}
