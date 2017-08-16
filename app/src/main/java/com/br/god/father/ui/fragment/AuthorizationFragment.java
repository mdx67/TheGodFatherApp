package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.Authorization;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.Money;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Transaction;
import com.br.god.father.model.TransactionDescription;
import com.br.god.father.model.TransactionDetail;
import com.br.god.father.model.TransactionItem;
import com.br.god.father.ui.activity.MainActivity;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static String paymentIdCreated;
    private static Connection connection;

    EditText etAuthorizeExternalId, etAuthorizePrice, etAuthorizeItemCode, etAuthorizeItemName, etAuthorizeItemQuantity, etAuthorizeItemPrice;
    Switch switchAuthorizeCapture;
    Button btCancelRefund;
    ProgressBar spinnerLoading;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_authorize_payment, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_authorize_capture);

        idenfityFields(view);

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("paymentUrl");
        customerId = ((MainActivity) getActivity()).getSharedPreferences("customerId");
        connection = ApiUtils.getConnection("https://dev-service.apirealwave.io/paymentsmanager/");

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        return view;
    }

    @OnClick(R.id.bt_authorize)
    public void onClickAuthorizationButton() {
        if (baseUrl == null || customerId == null) return;

        spinnerLoading.setVisibility(View.VISIBLE);

        execute(buildAuthorization());
    }

    @OnClick(R.id.bt_cancel_refund)
    public void onClickCancelRefundButton() {
        try {
            Class clazz = CancelFragment.class;

            Fragment fragment = (Fragment) clazz.newInstance();

            Bundle bundle = new Bundle();
            bundle.putString("paymentIdCreated", paymentIdCreated);
            bundle.putString("customerId", customerId);
            bundle.putString("action", switchAuthorizeCapture.isChecked() ? "CAPTURE" : "AUTHORIZE");

            fragment.setArguments(bundle);

            ((MainActivity) getActivity()).replaceFragment(fragment);

            ((MainActivity) getActivity()).removeContent();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void execute(Authorization authorization) {
//        ObjectMapper mapper = new ObjectMapper();
//        Authorization obj = authorization;
//        try {
//            String jsonInString = mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        if (authorization.getIntent().equals("AUTHORIZE")) {
            customerId = "43237f28-8853-41a3-83d0-03457db6d014";
        } else {
            customerId = "83237f28-8853-41a3-83d0-03457db6d014";
        }

        connection.authorize(customerId, authorization).enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("AuthorizeReturn:", response.body().toString());

                    btCancelRefund.setVisibility(View.VISIBLE);
                    paymentIdCreated = response.body().getPaymentId();

                    showMessage("Status retornado: " + response.body().getStatus());
                } else {
                    showMessage("Falha na autorização.");
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showMessage("Erro ao realizar requisição.");
            }
        });
    }

    private void idenfityFields(View view) {
        etAuthorizeExternalId = view.findViewById(R.id.et_authorize_external_id);
        etAuthorizePrice = view.findViewById(R.id.et_authorize_price);
        etAuthorizeItemCode = view.findViewById(R.id.et_authorize_item_code);
        etAuthorizeItemName = view.findViewById(R.id.et_cancel_payment_id);
        etAuthorizeItemPrice = view.findViewById(R.id.et_authorize_item_price);
        etAuthorizeItemQuantity = view.findViewById(R.id.et_authorize_item_quantity);

        switchAuthorizeCapture = view.findViewById(R.id.switch_auth_capt);

        btCancelRefund = view.findViewById(R.id.bt_cancel_refund);

        spinnerLoading = view.findViewById(R.id.spinner_loading_authorize);

    }

    private Authorization buildAuthorization() {
        Authorization authorization = new Authorization();

        authorization.setIntent(switchAuthorizeCapture.isChecked() ? "CAPTURE" : "AUTHORIZE");
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

        Money defaultMoney = new Money("BRL", 0, 0);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setDiscount(defaultMoney);
        transactionDetail.setHandlingFee(defaultMoney);
        transactionDetail.setInsurance(defaultMoney);
        transactionDetail.setShippingDiscount(defaultMoney);
        transactionDetail.setShippingFee(defaultMoney);
        transactionDetail.setTax(defaultMoney);

        transaction.setDetails(transactionDetail);

        authorization.setTransaction(transaction);

        return authorization;
    }

}
