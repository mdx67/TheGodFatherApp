package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.br.god.father.R;
import com.br.god.father.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConnectionSettingsFragment extends BaseFragment {

//    @BindView(R.id.et_settings_login)
//    EditText etLogin;
//    @BindView(R.id.et_settings_password)
//    EditText etPassword;
    @BindView(R.id.et_settings__payments_url)
    EditText etPaymentUrl;
    @BindView(R.id.et_settings_wallet_url)
    EditText etWalletUrl;
    @BindView(R.id.et_settings_subscription_url)
    EditText etSubscriptionUrl;
    @BindView(R.id.et_settings_customer_url)
    EditText etCustomerUrl;

    @BindView(R.id.radio_dev)
    RadioButton rbDev;
    @BindView(R.id.radio_sandbox)
    RadioButton rbSand;
    @BindView(R.id.radio_prod)
    RadioButton rbProd;

    public static ConnectionSettingsFragment newInstance() {
        return new ConnectionSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection_settings, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_settings);

        setFields();

        return view;
    }

    @OnClick(R.id.bt_settings_save)
    public void onClickSaveButton() {
        saveParams();

        showMessage("Configuração atualizada!");
    }

    private String buildEnvironmentName() {
        if (rbDev.isChecked()) {
            return "dev";
        } else if (rbSand.isChecked()) {
            return "sand";
        } else if (rbProd.isChecked()) {
            return "prod";
        }

        return "dev";
    }

    private void saveParams() {
//        ((MainActivity) getActivity()).saveSharedPreferences("login", etLogin.getText().toString());
//        ((MainActivity) getActivity()).saveSharedPreferences("password", etPassword.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("paymentUrl", etPaymentUrl.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("walletUrl", etWalletUrl.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("subscriptionUrl", etSubscriptionUrl.getText().toString());
        ((MainActivity) getActivity()).saveSharedPreferences("customerUrl", etCustomerUrl.getText().toString());

        ((MainActivity) getActivity()).saveSharedPreferences("environment", buildEnvironmentName());
    }

    private void setFields() {
//        etLogin.setText(((MainActivity) getActivity()).getSharedPreferences("login"));
//        etPassword.setText(((MainActivity) getActivity()).getSharedPreferences("password"));
        etPaymentUrl.setText(((MainActivity) getActivity()).getSharedPreferences("paymentUrl"));
        etWalletUrl.setText(((MainActivity) getActivity()).getSharedPreferences("walletUrl"));
        etSubscriptionUrl.setText(((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl"));
        etCustomerUrl.setText(((MainActivity) getActivity()).getSharedPreferences("customerUrl"));

        String radioOption = ((MainActivity) getActivity()).getSharedPreferences("environment");

        if (radioOption != null) {
            switch (radioOption) {
                case "dev":
                    rbDev.setChecked(true);
                    break;
                case "sand":
                    rbSand.setChecked(true);
                    break;
                case "prod":
                    rbProd.setChecked(true);
                    break;
                default:
                    rbDev.setChecked(true);
                    break;
            }
        }
    }
}
