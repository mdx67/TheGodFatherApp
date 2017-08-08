package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.br.god.father.R;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.OfferItem;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Recurring;
import com.br.god.father.model.Subscription;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Constants;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class BuyPlanFragment extends Fragment {

    @BindView(R.id.bt_plan_one)
    Button planOneButton;
    @BindView(R.id.bt_plan_two)
    Button planTwoButton;

    public static BuyPlanFragment newInstance() {
        return new BuyPlanFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle("Ass. plano");

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.toolbar.setTitle("God Father App");
    }

    @OnClick(R.id.bt_plan_one)
    public void onClickBtPlanOne() {
        Subscription subscription = buildSubscription();

        subscription.setPrice(new Money("BRL", 2990, 2));

        subscriptionPlan(subscription);
    }

    @OnClick(R.id.bt_plan_two)
    public void onClickBtPlanTwo() {
        Subscription subscription = buildSubscription();

        subscription.setPrice(new Money("BRL", 3990, 2));

        subscriptionPlan(subscription);
    }

    public void subscriptionPlan(Subscription subscription) {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL_BASE).addConverterFactory(JacksonConverterFactory.create()).build();

            Call call1 = retrofit.create(Connection.class).subscriptionPlan(subscription);

            call1.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.code() == 200) {
                        Log.i("SubscriptionReturn:", response.body().toString());

                        ((MainActivity) getActivity()).removeContent(BuyPlanFragment.newInstance());

                        Toast.makeText(getActivity(), "Plano assinado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Falha na assinatura.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    call.cancel();
                    Toast.makeText(getActivity(), "Erro na requisição!", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erro ao tentar fazer a chamada!", Toast.LENGTH_LONG).show();
        }
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
