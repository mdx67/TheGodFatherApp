package com.br.god.father.connection;

import com.br.god.father.model.AuthorizationRequest;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.CreateCustomerRequest;
import com.br.god.father.model.CreditCardListResponse;
import com.br.god.father.model.CreditCardRequest;
import com.br.god.father.model.CreditCardResponse;
import com.br.god.father.model.Customer;
import com.br.god.father.model.SubscriptionListResponse;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.SubscriptionResponse;
import com.br.god.father.model.payment.PaymentListResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Connection {

    @POST("v1/customers")
    Call<Customer> registerCustomer(@HeaderMap Map<String, String> headers, @Body CreateCustomerRequest customer);

    @GET("v1/wallet/credit-cards")
    Call<CreditCardListResponse> listCreditCard(@HeaderMap Map<String, String> headers);

    @POST("v1/wallet/credit-cards")
    Call<CreditCardResponse> registerCreditCard(@HeaderMap Map<String, String> headers, @Body CreditCardRequest creditCardRequest);

    @GET("v1/subscriptions")
    Call<SubscriptionListResponse> listSubscriptions(@HeaderMap Map<String, String> headers);

    @POST("v1/subscriptions")
    Call<SubscriptionResponse> subscriptionPlan(@HeaderMap Map<String, String> headers, @Body SubscriptionRequest subscriptionRequest);

    //TODO: remover fixo
    @GET("v1/payments?startDate=2017-07-01&endDate=2017-12-31")
    Call<List<PaymentListResponse>> listPayments(@HeaderMap Map<String, String> headers);

    @POST("v1/payments")
    Call<AuthorizationResponse> authorize(@HeaderMap Map<String, String> headers, @Body AuthorizationRequest authorizationRequest);

    @DELETE("v1/payments/{paymentId}")
    Call<AuthorizationResponse> cancel(@HeaderMap Map<String, String> headers, @Path("paymentId") String paymentId);

}
