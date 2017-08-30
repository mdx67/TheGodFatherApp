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
import com.br.god.father.model.Contact;
import com.br.god.father.model.ContactContent;
import com.br.god.father.model.ContactType;
import com.br.god.father.model.CreateCustomerRequest;
import com.br.god.father.model.Customer;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.model.Document;
import com.br.god.father.ui.activity.MainActivity;

import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCustomerFragment extends BaseFragment {

    private static Connection connection;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_register_customer_email)
    EditText etEmail;
    @BindView(R.id.et_document_number)
    EditText etDocumentNumber;
    @BindView(R.id.et_address_street)
    EditText etAddressStreet;
    @BindView(R.id.et_address_number)
    EditText etAddressNumber;
    @BindView(R.id.et_address_district)
    EditText etAddressDistinct;
    @BindView(R.id.et_address_postal_code)
    EditText etAddressPostalCode;

    @BindView(R.id.spinner_loading_register_customer)
    ProgressBar spinnerLoading;

    public static RegisterCustomerFragment newInstance() {
        return new RegisterCustomerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_customer, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_register_customer);

        String baseUrl = ((MainActivity) getActivity()).getSharedPreferences("customerUrl");

        if (baseUrl == null) {
            showMessage(getString(R.string.url_not_configured));

            ((MainActivity) getActivity()).removeContent();

            return null;
        }

        connection = ApiUtils.getConnection(baseUrl);

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        return view;
    }

    @OnClick(R.id.bt_register_customer)
    public void onClickBtRegisterCustomer() {
        spinnerLoading.setVisibility(View.VISIBLE);

        registerCustomer(buildCustomer());
    }

    public void registerCustomer(CreateCustomerRequest customer) {
        connection.registerCustomer(ApiUtils.buildHeaders(), customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    Log.i("RegisterCustomerReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    ((MainActivity) getActivity()).updateMainCustomer(new CustomerApp(response.body().getId(), response.body().getNickname()));

                    showMessage(getString(R.string.msg_status_returned) + response.code());
                } else {
                    showErrorMessageByResponse(response);
                }

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

    private CreateCustomerRequest buildCustomer() {

        CreateCustomerRequest customer = new CreateCustomerRequest();

        customer.setFullName(etName.getText().toString());
        customer.setNickname(etName.getText().toString());

        customer.setPersonType("F");
        customer.setBirthDate("1980-01-20");
        customer.setCountry("Brasil");
        customer.setMotherName("Julia Maria");
        customer.setGender("M");

//        Address address = new Address();
//        address.setName("Principal");
//        address.setStreet(etAddressStreet.getText().toString());
//        address.setNumber(etAddressNumber.getText().toString());
//        address.setDistrict(etAddressDistinct.getText().toString());
//        address.setCity("Joinville");
//        address.setState("Santa Catarina");
//        address.setZipCode(etAddressPostalCode.getText().toString());
//        address.setCountry("Brasil");
//
//        customer.setAddresses(Arrays.asList(address));

        Document document = new Document();
        document.setNumber(etDocumentNumber.getText().toString());
        document.setDocType("CPF");

        customer.setDocuments(Arrays.asList(document));

        Contact contact = new Contact(ContactType.EMAIL, new Date(), new ContactContent("MAIN", etEmail.getText().toString()));

        customer.setContacts(Arrays.asList(contact));

        return customer;
    }
}
