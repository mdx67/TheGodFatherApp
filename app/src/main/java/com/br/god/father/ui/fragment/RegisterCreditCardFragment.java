package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.CreditCard;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Utils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCreditCardFragment extends BaseFragment {

    EditText etHolderName, etBin, etLastDigits, etExpirationDate, etBrand;

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

        idenfityFields(view);

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("walletUrl");

        String mainCustomer = ((MainActivity) getActivity()).getSharedPreferences("mainCustomer");

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return null;
        }

        customerId = Utils.convertStringToCustomer(mainCustomer).getId();

        connection = ApiUtils.getConnection(baseUrl);

        return view;
    }

    @OnClick(R.id.bt_credit_card_register)
    public void onClickBtCrediCardRegister() {
        CreditCard creditCard = buildCreditCard();

        if (baseUrl == null || customerId == null) return;

        register(creditCard);
    }

    private void idenfityFields(View view) {
//        etHolderName = view.findViewById(R.id.et_credit_card_holder);
//        etBin = view.findViewById(R.id.et_credit_card_bin);
//        etLastDigits = view.findViewById(R.id.et_credit_card_last_digits);
//        etExpirationDate = view.findViewById(R.id.et_credit_card_expiration_date);
//        etBrand = view.findViewById(R.id.et_credit_card_brand);
    }

    private CreditCard buildCreditCard() {
        return new CreditCard("EXTERNAL_CREDIT_CARD", etHolderName.getText().toString(), etBin.getText().toString(), etLastDigits.getText().toString(), etExpirationDate.getText().toString(), etBrand.getText().toString(), "ASJASJDASDASJDLIAJSLIJDIAJDIASJPDASPDJA-FULL");
    }

    public void register(CreditCard creditCard) {
        connection.registerCreditCard(customerId, creditCard).enqueue(new Callback<CreditCard>() {
            @Override
            public void onResponse(Call<CreditCard> call, Response<CreditCard> response) {
                if (response.isSuccessful()) {
                    Log.i("CreditCardReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage(getString(R.string.msg_status_returned));
                } else {
                    showMessage("Falha no cadastro.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
                showMessage("Erro ao realizar requisição.");
            }
        });
    }
}
