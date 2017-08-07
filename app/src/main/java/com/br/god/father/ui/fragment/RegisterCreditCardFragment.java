package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.god.father.R;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
import com.br.god.father.ui.activity.MainActivity;

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

    private Customer customer;

    public static RegisterCreditCardFragment newInstance() {
        return new RegisterCreditCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_credit_card, container, false);

        ButterKnife.bind(this, view);
        MainActivity.toolbar.setTitle("Cad. Cartão");

        Bundle args = getArguments();
        customer = (Customer) args.getSerializable("customer");

        idenfityFields(view);

        return view;
    }

    @OnClick(R.id.btCrediCardRegister)
    public void onClickBtCrediCardRegister() {
        buildCreditCard();

        registerCustomer();
    }

    private void idenfityFields(View view) {
        etNumber = view.findViewById(R.id.etName);
        etValidate = view.findViewById(R.id.etEmail);
        etCvv = view.findViewById(R.id.etPhone);
    }

    private void buildCreditCard() {
        customer.setCreditCard(new CreditCard(etNumber.getText().toString(), etValidate.getText().toString(), etCvv.getText().toString()));
    }

    public void registerCustomer() {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.20.48.1:3000").addConverterFactory(JacksonConverterFactory.create()).build();

            Connection connection = retrofit.create(Connection.class);

            Call call1 = connection.registerCustomer();

            call1.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Customer respCustomer = (Customer) response.body();

                    ((MainActivity) getActivity()).removeContent(RegisterCreditCardFragment.newInstance());

                    Toast.makeText(getActivity(), "Cliente e cartão cadastrados!", Toast.LENGTH_LONG).show();
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
}
