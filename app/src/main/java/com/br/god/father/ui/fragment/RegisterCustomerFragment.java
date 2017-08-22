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
import com.br.god.father.model.Address;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Document;
import com.br.god.father.model.Zns;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCustomerFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    EditText etName, etUserId, etDocumentNumber, etAddressStreet, etAddressNumber, etAddressDistinct, etAddressPostalCode;

    public static RegisterCustomerFragment newInstance() {
        return new RegisterCustomerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_customer, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle("Cad. Cliente");

        idenfityFields(view);

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("paymentUrl");
        customerId = ((MainActivity) getActivity()).getSharedPreferences("customerId");
        connection = ApiUtils.getConnection(baseUrl);

        return view;
    }

    @OnClick(R.id.bt_register_customer)
    public void onClickBtRegisterCustomer() {
        if (baseUrl == null || customerId == null) return;

        registerCustomer(buildCustomer());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void idenfityFields(View view) {
//        etName = view.findViewById(R.id.et_name);
//        etUserId = view.findViewById(R.id.et_user_id);
//        etAddressStreet = view.findViewById(R.id.et_address_street);
//        etAddressNumber = view.findViewById(R.id.et_address_number);
//        etAddressDistinct = view.findViewById(R.id.et_address_complement);
//        etAddressPostalCode = view.findViewById(R.id.et_address_postal_code);
//        etDocumentNumber = view.findViewById(R.id.et_document_number);
    }

    public void registerCustomer(Customer customer) {
        connection.registerCustomer(customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    Log.i("RegisterCustomerReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    ((MainActivity) getActivity()).saveSharedPreferences("customerId", response.body().getId());

                    showMessage("Cliente cadastrado com sucesso!");
                } else {
                    showMessage("Falha no cadastro.");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
                showMessage(getString(R.string.msg_request_error));
            }
        });
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
}
