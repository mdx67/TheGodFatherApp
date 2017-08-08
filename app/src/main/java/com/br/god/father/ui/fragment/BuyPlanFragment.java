package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.br.god.father.R;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.OfferItem;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Subscription;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class BuyPlanFragment extends Fragment {

    @BindView(R.id.btPlanOne)
    Button planOneButton;
    @BindView(R.id.btPlanTwo)
    Button planTwoButton;

    private Subscription subscription;

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

    @OnClick(R.id.btPlanOne)
    public void onClickBtPlanOne() {
        buildSubscription();

        subscription.setPrice(new Money("BRL", 2990, 2));

        subscriptionPlan();
    }

    @OnClick(R.id.btPlanTwo)
    public void onClickBtPlanTwo() {
        buildSubscription();

        subscription.setPrice(new Money("BRL", 3990, 2));

        subscriptionPlan();
    }

    public void subscriptionPlan() {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.20.48.1:3000").addConverterFactory(JacksonConverterFactory.create()).build();

            Connection connection = retrofit.create(Connection.class);

            Call call1 = connection.subscriptionPlan(subscription);

            call1.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Subscription resp = (Subscription) response.body();

                    if (response.code() == 200) {
                        ((MainActivity) getActivity()).removeContent(RegisterCreditCardFragment.newInstance());

                        Toast.makeText(getActivity(), "Cliente cadastrados!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Falha no cadastro!", Toast.LENGTH_LONG).show();
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

    private void buildSubscription() {
        Offer offer = new Offer();
        offer.setValidity(30);
        offer.setCatalogOfferType("PLAN");

        OfferItem item = new OfferItem();
        item.setProductId(1);

        offer.setOfferItems((Set<OfferItem>) Arrays.asList(item));

        subscription.setOffer(offer);

        subscription.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));
    }

}
