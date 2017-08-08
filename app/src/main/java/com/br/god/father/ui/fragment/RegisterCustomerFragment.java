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
import com.br.god.father.model.Address;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Document;
import com.br.god.father.model.Zns;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RegisterCustomerFragment extends Fragment {

    @BindView(R.id.bt_next)
    Button buttonNext;

    EditText etName, etUserId, etDocumentNumber, etAddressStreet, etAddressNumber, etAddressDistinct, etAddressPostalCode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_customer, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle("Cad. Cliente");

        idenfityFields(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        MainActivity.toolbar.setTitle("God Father App");
    }

    @OnClick(R.id.bt_next)
    public void onClickBtNext() {
//        buildCustomer();

        registerCustomer();

//        RegisterCreditCardFragment fragment = RegisterCreditCardFragment.newInstance();
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("customer", buildCustomer());
//        fragment.setArguments(bundle);
//
//        ((MainActivity) getActivity()).replaceFragment(fragment);
//        ((MainActivity) getActivity()).getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();

        customer.setFullName(etName.getText().toString());
        customer.setZns(new Zns(etUserId.getText().toString()));
        customer.setPersonType("INDIVIDUAL");
        customer.setCountry("Brasil");

        Address address = new Address();
        address.setName("Principal");
        address.setStreet(etAddressStreet.getText().toString());
        address.setNumber(etAddressNumber.getText().toString());
        address.setDistrict(etAddressDistinct.getText().toString());
        address.setCity("Joinville");
        address.setState("Santa Catarina");
        address.setZipCode(etAddressPostalCode.getText().toString());
        address.setCountry("Brasil");

        customer.setAddresses(Arrays.asList(address));

        Document document = new Document();
        document.setNumber(etDocumentNumber.getText().toString());
        document.setDocType("CPF");

        customer.setDocuments(Arrays.asList(document));

        return customer;
    }

    private void idenfityFields(View view) {
        etName = view.findViewById(R.id.et_name);
        etUserId = view.findViewById(R.id.et_user_id);
        etAddressStreet = view.findViewById(R.id.et_address_street);
        etAddressNumber = view.findViewById(R.id.et_address_number);
        etAddressDistinct = view.findViewById(R.id.et_address_complement);
        etAddressPostalCode = view.findViewById(R.id.et_address_postal_code);

        etDocumentNumber = view.findViewById(R.id.et_document_number);
    }

    public void registerCustomer() {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.5.195:8882").addConverterFactory(JacksonConverterFactory.create()).build();

            Call call1 = retrofit.create(Connection.class).registerCustomer(buildCustomer());

            call1.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Customer respCustomer = (Customer) response.body();

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
}
