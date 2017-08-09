package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.br.god.father.R;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.Authorization;
import com.br.god.father.model.Money;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Transaction;
import com.br.god.father.model.TransactionDescription;
import com.br.god.father.model.TransactionItem;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Constants;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class AuthorizationFragment extends BaseFragment {

    EditText etAuthorizeIntent, etAuthorizeExternalId, etAuthorizePrice, etAuthorizeItemCode, etAuthorizeItemName, etAuthorizeItemQuantity, etAuthorizeItemPrice;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorize_payment, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle("Autorização");

        idenfityFields(view);

        return view;
    }

    @OnClick(R.id.bt_authorize)
    public void onClickAuthorizationButton() {
        register(buildAuthorization());
    }

    public void register(Authorization authorization) {
        String baseUrl = ((MainActivity) getActivity()).getSharedPreferences("walletUrl");

        if (baseUrl == null) return;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();

        Call call = retrofit.create(Connection.class).authorize(authorization);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    Log.i("AuthorizeReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage("Autorizado com sucesso!");
                } else {
                    showMessage("Falha na autorização.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
                showMessage("Erro ao realizar requisição.");
            }
        });
    }

    private void idenfityFields(View view) {
        etAuthorizeIntent = view.findViewById(R.id.et_authorize_intent);
        etAuthorizeExternalId = view.findViewById(R.id.et_authorize_external_id);
        etAuthorizePrice = view.findViewById(R.id.et_authorize_price);
        etAuthorizeItemCode = view.findViewById(R.id.et_authorize_item_code);
        etAuthorizeItemName = view.findViewById(R.id.et_authorize_item_name);
        etAuthorizeItemPrice = view.findViewById(R.id.et_authorize_item_price);
        etAuthorizeItemQuantity = view.findViewById(R.id.et_authorize_item_quantity);
    }

    private Authorization buildAuthorization() {
        Authorization authorization = new Authorization();

        authorization.setIntent(etAuthorizeIntent.getText().toString());
        authorization.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));

        Transaction transaction = new Transaction();

        transaction.setExternalId(etAuthorizeExternalId.getText().toString());
        transaction.setPrice(new Money("BRL", 199, 2));
        transaction.setType("SINGLE");

        TransactionItem item = new TransactionItem();
        item.setPrice(new Money("BRL", 199, 2));
        item.setCode(etAuthorizeItemCode.getText().toString());
        item.setName(etAuthorizeItemName.getText().toString());
        item.setQuantity(Integer.parseInt(etAuthorizeItemQuantity.getText().toString()));

        transaction.setItems(Arrays.asList(item));

        transaction.setDescription(new TransactionDescription("TELECOM ADDON PURCHASE", "ADDON"));

        authorization.setTransaction(transaction);

        return authorization;
    }

}
