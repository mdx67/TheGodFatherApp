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
import com.br.god.father.model.CreditCard;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RegisterCreditCardFragment extends BaseFragment {

    EditText etHolderName, etBin, etLastDigits, etExpirationDate, etBrand;

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

        return view;
    }

    @OnClick(R.id.bt_credit_card_register)
    public void onClickBtCrediCardRegister() {
        CreditCard creditCard = buildCreditCard();

        register(creditCard);
    }

    private void idenfityFields(View view) {
        etHolderName = view.findViewById(R.id.et_credit_card_holder);
        etBin = view.findViewById(R.id.et_credit_card_bin);
        etLastDigits = view.findViewById(R.id.et_credit_card_last_digits);
        etExpirationDate = view.findViewById(R.id.et_credit_card_expiration_date);
        etBrand = view.findViewById(R.id.et_credit_card_brand);
    }

    private CreditCard buildCreditCard() {
        return new CreditCard("EXTERNAL_CREDIT_CARD", etHolderName.getText().toString(), etBin.getText().toString(), etLastDigits.getText().toString(), etExpirationDate.getText().toString(), etBrand.getText().toString(), "ASJASJDASDASJDLIAJSLIJDIAJDIASJPDASPDJA-FULL");
    }

    public void register(CreditCard creditCard) {
        String baseUrl = ((MainActivity) getActivity()).getSharedPreferences("walletUrl");

        if (baseUrl == null) return;

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();

        Call call1 = retrofit.create(Connection.class).registerCreditCard(creditCard);

        call1.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    Log.i("CreditCardReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage("Cartão cadastrado com sucesso!");
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
