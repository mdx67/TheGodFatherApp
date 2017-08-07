package com.br.god.father.ui.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.br.god.father.R;
import com.br.god.father.model.Customer;
import com.br.god.father.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterCustomerFragment extends Fragment {

    @BindView(R.id.btNext)
    Button buttonNext;

    EditText etName, etEamil, etAddress, etPhone;

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

    @OnClick(R.id.btNext)
    public void onClickBtNext() {
        RegisterCreditCardFragment fragment = RegisterCreditCardFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putSerializable("customer", buildCustomer());
        fragment.setArguments(bundle);

        ((MainActivity) getActivity()).replaceFragment(fragment);
        ((MainActivity) getActivity()).getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
    }

    private Customer buildCustomer() {
        Customer customer = new Customer();

        customer.setName(etName.getText().toString());
        customer.setEmail(etEamil.getText().toString());
        customer.setPhone(etPhone.getText().toString());
        customer.setAddress(etAddress.getText().toString());

        return customer;
    }

    private void idenfityFields(View view) {
        etName = view.findViewById(R.id.etName);
        etEamil = view.findViewById(R.id.etEmail);
        etPhone = view.findViewById(R.id.etPhone);
        etAddress = view.findViewById(R.id.etAddress);
    }
}
