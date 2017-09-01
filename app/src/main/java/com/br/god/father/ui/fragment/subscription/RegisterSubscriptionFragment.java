package com.br.god.father.ui.fragment.subscription;

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
import com.br.god.father.mock.SubscriptionMock;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.model.Error;
import com.br.god.father.model.Money;
import com.br.god.father.model.Payment;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.SubscriptionResponse;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.ui.fragment.BaseFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterSubscriptionFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    @BindView(R.id.spinner_loading_subscription)
    ProgressBar spinnerLoading;

    public static RegisterSubscriptionFragment newInstance() {
        return new RegisterSubscriptionFragment();
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

        if (baseUrl == null) {
            showMessage(getString(R.string.msg_add_payments_url));

            return;
        }

        CustomerApp mainCustomer = ((MainActivity) getActivity()).getMainCustomer();

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return;
        }

        customerId = mainCustomer.getId();
        connection = ApiUtils.getConnection(baseUrl);
    }

    @OnClick(R.id.bt_plan_one)
    public void onClickBtPlanOne() {
        SubscriptionRequest subscriptionRequest = SubscriptionMock.buildSubscription();

        subscriptionRequest.setPrice(new Money("BRL", 2990, 2));

        subscribe(subscriptionRequest);
    }

    @OnClick(R.id.bt_plan_two)
    public void onClickBtPlanTwo() {
        SubscriptionRequest subscriptionRequest = SubscriptionMock.buildSubscription();

        subscriptionRequest.setPrice(new Money("BRL", 3990, 2));

        subscriptionRequest.setPayment(new Payment("CREDIT_CARD", ((MainActivity) getActivity()).getSharedPreferences("creditCardId")));

        subscribe(subscriptionRequest);
    }

    private void subscribe(SubscriptionRequest subscriptionRequest) {
        spinnerLoading.setVisibility(View.VISIBLE);

        doSubscribe(subscriptionRequest);
    }

    private void doSubscribe(SubscriptionRequest subscriptionRequest) {
        connection.subscriptionPlan(ApiUtils.buildHeaders(customerId), subscriptionRequest).enqueue(new Callback<SubscriptionResponse>() {
            @Override
            public void onResponse(Call<SubscriptionResponse> call, Response<SubscriptionResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("SubscriptionReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage(getString(R.string.msg_status_returned) + response.code());
                } else {
                    try {
                        Error error = new ObjectMapper().readValue(response.errorBody().string().toString(), Error.class);

                        showErrorMessage(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<SubscriptionResponse> call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showAlertDialogWithOKButton("Erro", t.getMessage());
            }
        });
    }
}
