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
import android.widget.Toast;

import com.br.god.father.R;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.CreditCard;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RegisterCreditCardFragment extends Fragment {

    @BindView(R.id.btCrediCardRegister)
    Button buttonCreditCardRegister;

    EditText etNumber, etValidate, etCvv;

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

    @OnClick(R.id.btCrediCardRegister)
    public void onClickBtCrediCardRegister() {
        CreditCard creditCard = buildCreditCard();

        registerCustomer(creditCard);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.toolbar.setTitle("God Father App");
    }

    private void idenfityFields(View view) {
        etNumber = view.findViewById(R.id.et_credit_card_number);
        etValidate = view.findViewById(R.id.et_credit_card_validation);
        etCvv = view.findViewById(R.id.et_credit_card_cvv);
    }

    private CreditCard buildCreditCard() {
        return new CreditCard(etNumber.getText().toString(), etValidate.getText().toString(), etCvv.getText().toString());
    }

    public void registerCustomer(CreditCard creditCard) {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.URL_BASE).addConverterFactory(JacksonConverterFactory.create()).build();

            Call call1 = retrofit.create(Connection.class).registerCreditCard(creditCard);

            call1.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    if (response.code() == 200) {
                        Log.i("CreditCardReturn:", response.body().toString());

                        ((MainActivity) getActivity()).removeContent(RegisterCreditCardFragment.newInstance());

                        Toast.makeText(getActivity(), "Cartão cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Falha no cadastro.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    call.cancel();
                    Toast.makeText(getActivity(), "Erro na requisição.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erro ao tentar fazer a chamada.", Toast.LENGTH_LONG).show();
        }
    }
}
