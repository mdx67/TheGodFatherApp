package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.br.god.father.R;
import com.br.god.father.ui.activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends BaseFragment {

    EditText etLogin, etPassword, etPaymentUrl, etWalletUrl, etSubscriptionUrl;
    RadioGroup radioGroup;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle("Configurações");

        idenfityFields(view);
        setFields();

        return view;
    }

    @OnClick(R.id.bt_settings_save)
    public void onClickSaveButton() {
        ((MainActivity) getActivity()).saveSharedPreferences("login", etLogin.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("password", etPassword.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("paymentUrl", etPaymentUrl.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("walletUrl", etWalletUrl.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("subscriptionUrl", etSubscriptionUrl.getText().toString());

        Integer radioButtonID = radioGroup.getCheckedRadioButtonId();

        ((MainActivity) getActivity()).saveSharedPreferences("environment", radioButtonID.toString());

        showMessage("Configuração atualizada!");
    }

    private void idenfityFields(View view) {
        etLogin = view.findViewById(R.id.et_settings_login);
        etPassword = view.findViewById(R.id.et_settings_password);
        etPaymentUrl = view.findViewById(R.id.et_settings_payment_url);
        etWalletUrl = view.findViewById(R.id.et_settings_wallet_url);
        etSubscriptionUrl = view.findViewById(R.id.et_settings_subscription_url);

        radioGroup = view.findViewById(R.id.radio_group_settings);
    }

    private void setFields() {
        etLogin.setText(((MainActivity) getActivity()).getSharedPreferences("login"));
        etPassword.setText(((MainActivity) getActivity()).getSharedPreferences("password"));
        etPaymentUrl.setText(((MainActivity) getActivity()).getSharedPreferences("paymentUrl"));
        etWalletUrl.setText(((MainActivity) getActivity()).getSharedPreferences("walletUrl"));
        etSubscriptionUrl.setText(((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl"));

        String radioOption = ((MainActivity) getActivity()).getSharedPreferences("environment");

        if (radioOption != null) {
            radioGroup.check(Integer.parseInt(radioOption));
        }
    }
}
