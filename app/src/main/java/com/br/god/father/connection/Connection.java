package com.br.god.father.connection;

import com.br.god.father.model.AuthorizationRequest;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.CreateCustomerRequest;
import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.SubscriptionResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Connection {

    @POST("customers")
    Call<Customer> registerCustomer(@HeaderMap Map<String, String> headers, @Body CreateCustomerRequest customer);

    @POST("/mobile/register/creditCard")
    Call<CreditCard> registerCreditCard(@HeaderMap Map<String, String> headers, @Body CreditCard creditCard);

    @POST("v1/subscriptions")
    Call<SubscriptionResponse> subscriptionPlan(@HeaderMap Map<String, String> headers, @Body SubscriptionRequest subscriptionRequest);

    @POST("v1/payments")
    Call<AuthorizationResponse> authorize(@HeaderMap Map<String, String> headers, @Body AuthorizationRequest authorizationRequest);

    @DELETE("v1/payments/{paymentId}")
    Call<AuthorizationResponse> cancel(@HeaderMap Map<String, String> headers, @Path("paymentId") String paymentId);

}
