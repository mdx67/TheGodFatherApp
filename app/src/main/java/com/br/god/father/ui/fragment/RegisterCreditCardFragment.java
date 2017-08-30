package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.CreditCard;
import com.br.god.father.model.CreditCardResponse;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCreditCardFragment extends BaseFragment {

    @BindView(R.id.et_credit_card_holder)
    EditText etHolderName;
    @BindView(R.id.et_credit_card_bin)
    EditText etBin;
    @BindView(R.id.et_credit_card_last_digits)
    EditText etLastDigits;
    @BindView(R.id.et_credit_card_expiration_date)
    EditText etExpirationDate;
    @BindView(R.id.et_credit_card_brand)
    EditText etBrand;
    @BindView(R.id.et_credit_card_external_token)
    EditText etExternalToken;

    @BindView(R.id.spinner_loading_register_credit_card)
    ProgressBar spinnerLoading;

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    public static RegisterCreditCardFragment newInstance() {
        return new RegisterCreditCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_credit_card, container, false);

        ButterKnife.bind(this, view);
        MainActivity.toolbar.setTitle("Cad. Cartão");

        setConnectionParams();

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        return view;
    }

    @OnClick(R.id.bt_credit_card_register)
    public void onClickBtCrediCardRegister() {
        CreditCard creditCard = buildCreditCard();

        if (baseUrl == null || customerId == null) return;

        spinnerLoading.setVisibility(View.VISIBLE);

        register(creditCard);
    }

    private void setConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("walletUrl");

        CustomerApp mainCustomer = ((MainActivity) getActivity()).getMainCustomer();

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return;
        }

        customerId = mainCustomer.getId();
        connection = ApiUtils.getConnection(baseUrl);
    }

    private CreditCard buildCreditCard() {
        return new CreditCard("EXTERNAL_CREDIT_CARD", etHolderName.getText().toString(), etBin.getText().toString(), etLastDigits.getText().toString(), etExpirationDate.getText().toString(), etBrand.getText().toString(), etExternalToken.getText().toString());
    }

    public void register(CreditCard creditCard) {
        connection.registerCreditCard(ApiUtils.buildHeaders(customerId), creditCard).enqueue(new Callback<CreditCardResponse>() {
            @Override
            public void onResponse(Call<CreditCardResponse> call, Response<CreditCardResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("CreditCardReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();
                }

                showMessage(getString(R.string.msg_status_returned) + response.code());

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showMessage(getString(R.string.msg_request_error));
            }
        });
    }
}
