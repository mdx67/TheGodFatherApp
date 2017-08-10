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
import com.br.god.father.model.Subscription;
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

        MainActivity.toolbar.setTitle("Ass. plano");

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl");
        customerId = ((MainActivity) getActivity()).getSharedPreferences("customerId");
        connection = ApiUtils.getConnection(baseUrl);

        return view;
    }

    @OnClick(R.id.bt_plan_one)
    public void onClickBtPlanOne() {
        Subscription subscription = buildSubscription();

        subscription.setPrice(new Money("BRL", 2990, 2));

        subscribe(subscription);
    }

    @OnClick(R.id.bt_plan_two)
    public void onClickBtPlanTwo() {
        Subscription subscription = buildSubscription();

        subscription.setPrice(new Money("BRL", 3990, 2));

        subscribe(subscription);
    }

    private void subscribe(Subscription subscription) {
        if (baseUrl == null || customerId == null) return;

        doSubscribe(subscription);
    }

    private void doSubscribe(Subscription subscription) {
        connection.subscriptionPlan(customerId, subscription).enqueue(new Callback<Subscription>() {
            @Override
            public void onResponse(Call<Subscription> call, Response<Subscription> response) {
                if (response.isSuccessful()) {
                    Log.i("SubscriptionReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage("Plano assinado com sucesso!");
                } else {
                    showMessage("Falha na assinatura.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
                showMessage("Erro na requisição!");
            }
        });
    }

    private Subscription buildSubscription() {
        Subscription subscription = new Subscription();

        Offer offer = new Offer();
        offer.setId("41530168-12dd-4510-be2b-7add0e7d14e9");
        offer.setType("PLAN");
        offer.setValidity(30);

        OfferItem item = new OfferItem();
        item.setProductId(1);
        item.setId("1f0ef0f7-3a46-4b9f-8143-6490a046ff5b");
        item.setComponent("INTERNET_MOBILE");

        offer.setItems(Arrays.asList(item));

        subscription.setOffer(offer);

        subscription.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));

        subscription.setRecurring(new Recurring("2018-06-08", "2019-01-01", "DAY", 15));

        return subscription;
    }
}
