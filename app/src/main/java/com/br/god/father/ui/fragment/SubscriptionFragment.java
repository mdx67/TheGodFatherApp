package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.ItemAttributes;
import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.OfferItem;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Recurring;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.SubscriptionResponse;
import com.br.god.father.model.UnitValue;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Utils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    @BindView(R.id.spinner_loading_subscription)
    ProgressBar spinnerLoading;

    public static SubscriptionFragment newInstance() {
        return new SubscriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_buy_plan);

        setConnectionParams();

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        return view;
    }

    private void setConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl");

        String mainCustomer = ((MainActivity) getActivity()).getSharedPreferences("mainCustomer");

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return;
        }

        customerId = Utils.convertStringToCustomer(mainCustomer).getId();
        connection = ApiUtils.getConnection(baseUrl);
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

        spinnerLoading.setVisibility(View.VISIBLE);

        doSubscribe(subscriptionRequest);
    }

    private void doSubscribe(SubscriptionRequest subscriptionRequest) {
        connection.subscriptionPlan(customerId, subscriptionRequest).enqueue(new Callback<SubscriptionResponse>() {
            @Override
            public void onResponse(Call<SubscriptionResponse> call, Response<SubscriptionResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("SubscriptionReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage(getString(R.string.msg_status_returned) + response.body().getStatus());
                } else {
                    showMessage("Falha na assinatura: " + response.code());
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<SubscriptionResponse> call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showMessage(getString(R.string.msg_request_error));
            }
        });
    }

    private SubscriptionRequest buildSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();

        subscriptionRequest.setExternalId("PLAN-02_233dda31");
        subscriptionRequest.setOriginApp("MGM");
        subscriptionRequest.setDescription("Plano 2 - COMBO:  internet + dados moveis");
        subscriptionRequest.setType("STANDARD");
        subscriptionRequest.setCallback("http://127.0.0.1:8834/notifications");

        subscriptionRequest.setRecurring(new Recurring("2017-10-01", 1, "MONTH", 6));

        subscriptionRequest.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));

        Offer offer = new Offer();
        offer.setCatalogOfferId("41530168-12dd-4510-be2b-7add0e7d14e9");
        offer.setCatalogOfferType("PLAN");
        offer.setValidity(30);

        OfferItem item = new OfferItem();
        item.setProductId(1);
        item.setCatalogOfferItemId("1f0ef0f7-3a46-4b9f-8143-6490a046ff5b");
        item.setComponent("INTERNET_MOBILE");
        item.setPrice(new Money("BRL", 3990, 2));

        ItemAttributes attributes = new ItemAttributes();
        attributes.setName("volume");
        attributes.setValue("4999");
        attributes.setUnitValue(new UnitValue(3, 0, "GB"));

        item.setAttributes(Arrays.asList(attributes));

        offer.setOfferItems(Arrays.asList(item));

        subscriptionRequest.setOffer(offer);

        return subscriptionRequest;
    }
}
