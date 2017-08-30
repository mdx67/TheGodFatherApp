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
import com.br.god.father.model.AuthorizationRequest;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.model.Money;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Transaction;
import com.br.god.father.model.TransactionDescription;
import com.br.god.father.model.TransactionDetail;
import com.br.god.father.model.TransactionItem;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
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

    @BindView(R.id.et_authorize_external_id)
    EditText etAuthorizeExternalId;
    @BindView(R.id.et_authorize_payment_method_id)
    EditText etPaymentMethodId;
    @BindView(R.id.et_authorize_price)
    EditText etAuthorizePrice;
    @BindView(R.id.et_authorize_item_code)
    EditText etAuthorizeItemCode;
    @BindView(R.id.et_cancel_payment_id)
    EditText etAuthorizeItemName;
    @BindView(R.id.et_authorize_item_price)
    EditText etAuthorizeItemPrice;
    @BindView(R.id.et_authorize_item_quantity)
    EditText etAuthorizeItemQuantity;

    @BindView(R.id.switch_auth_capt)
    Switch switchAuthorizeCapture;
    @BindView(R.id.bt_cancel_refund)
    Button btCancelRefund;
    @BindView(R.id.spinner_loading_authorize)
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

        setConnectionParams();

        setPaymentMethodId();

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        return view;
    }

    private void setConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("paymentUrl");

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

    private void setPaymentMethodId() {
        String creditCardId = ((MainActivity) getActivity()).getSharedPreferences("creditCardId");

        if (creditCardId != null) {
            etPaymentMethodId.setText(creditCardId);
        }
    }

    @OnClick(R.id.bt_authorize)
    public void onClickAuthorizationButton() {
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

    private void execute(AuthorizationRequest authorizationRequest) {
//        ObjectMapper mapper = new ObjectMapper();
//        AuthorizationRequest obj = authorizationRequest;
//        try {
//            String jsonInString = mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        connection.authorize(ApiUtils.buildHeaders(customerId), authorizationRequest).enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("AuthorizeReturn:", response.body().toString());

                    btCancelRefund.setVisibility(View.VISIBLE);
                    paymentIdCreated = response.body().getPaymentId();

                    showMessage(getString(R.string.msg_status_returned) + response.code());
                } else {
                    showErrorMessageByResponse(response);
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showMessage(getString(R.string.msg_request_error));
            }
        });
    }

    private AuthorizationRequest buildAuthorization() {
        AuthorizationRequest authorizationRequest = new AuthorizationRequest();

        authorizationRequest.setIntent(switchAuthorizeCapture.isChecked() ? "CAPTURE" : "AUTHORIZE");

        authorizationRequest.setPayment(new Payment("CREDIT_CARD", etPaymentMethodId.getText().toString()));

        Transaction transaction = new Transaction();

        transaction.setExternalId(etAuthorizeExternalId.getText().toString());
        transaction.setPrice(new Money("BRL", 199, Integer.parseInt(etAuthorizePrice.getText().toString())));
        transaction.setType("SINGLE");

        TransactionItem item = new TransactionItem();
        item.setPrice(new Money("BRL", 199, Integer.parseInt(etAuthorizeItemPrice.getText().toString())));
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

        authorizationRequest.setTransaction(transaction);
        authorizationRequest.setCustomFields(new HashMap()); //bugs se passar null

        return authorizationRequest;
    }

}
