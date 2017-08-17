package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.OfferItem;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Recurring;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.SubscriptionResponse;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyPlanFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    public static BuyPlanFragment newInstance() {
        return new BuyPlanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_buy_plan);

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl");
//        customerId = ((MainActivity) getActivity()).getSharedPreferences("customerId");
        customerId = "abc";
        connection = ApiUtils.getConnection(baseUrl);

        return view;
    }

    @OnClick(R.id.bt_plan_one)
    public void onClickBtPlanOne() {
        SubscriptionRequest subscriptionRequest = buildSubscription();

        subscriptionRequest.setPrice(new Money("BRL", 2990, 2));

        subscribe(subscriptionRequest);
    }

    @OnClick(R.id.bt_plan_two)
    public void onClickBtPlanTwo() {
        SubscriptionRequest subscriptionRequest = buildSubscription();

        subscriptionRequest.setPrice(new Money("BRL", 3990, 2));

        subscribe(subscriptionRequest);
    }

    private void subscribe(SubscriptionRequest subscriptionRequest) {
        if (baseUrl == null || customerId == null) {
            showMessage("Verifique a URL/Cliente nas configurações.");

            return;
        }

        doSubscribe(subscriptionRequest);
    }

    private void doSubscribe(SubscriptionRequest subscriptionRequest) {
        connection.subscriptionPlan(customerId, subscriptionRequest).enqueue(new Callback<SubscriptionResponse>() {
            @Override
            public void onResponse(Call<SubscriptionResponse> call, Response<SubscriptionResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("SubscriptionReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage("Plano assinado com sucesso!");
                } else {
                    showMessage("Falha na assinatura.");
                }
            }

            @Override
            public void onFailure(Call<SubscriptionResponse> call, Throwable t) {
                call.cancel();
                showMessage("Erro na requisição!");
            }
        });
    }

    private SubscriptionRequest buildSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();

        subscriptionRequest.setExternalId("PLAN-02_233dda31");
        subscriptionRequest.setOriginApp("MGM");
        subscriptionRequest.setDescription("Plano 2 - COMBO:  internet + dados moveis");
        subscriptionRequest.setType("STANDARD");

        Offer offer = new Offer();
        offer.setCatalogOfferId("41530168-12dd-4510-be2b-7add0e7d14e9");
        offer.setCatalogOfferType("PLAN");
        offer.setValidity(30);

        OfferItem item = new OfferItem();
        item.setProductId(1);
        item.setCatalogOfferItemId("1f0ef0f7-3a46-4b9f-8143-6490a046ff5b");
        item.setComponent("INTERNET_MOBILE");

        offer.setOfferItems(Arrays.asList(item));

        subscriptionRequest.setOffer(offer);

        subscriptionRequest.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));

        subscriptionRequest.setRecurring(new Recurring("2018-06-08", "2019-01-01", "DAY", 15));

        return subscriptionRequest;
    }
}
