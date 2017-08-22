package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelFragment extends BaseFragment {

    private static String baseUrl;
    private static String customerId;
    private static String action;
    private static String paymentCreatedId;
    private static Connection connection;

    @BindView(R.id.et_cancel_payment_id)
    EditText etPaymentId;
    @BindView(R.id.spinner_loading_cancel)
    ProgressBar spinnerLoading;

    public static CancelFragment newInstance() {
        return new CancelFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cancel_payment, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_cancel_refund);

        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("paymentUrl");
//        customerId = ((MainActivity) getActivity()).getSharedPreferences("customerId");
        connection = ApiUtils.getConnection("https://dev-service.apirealwave.io/paymentsmanager/");

        getReceivedParams();
        setFieldsIfNecessary();

        spinnerLoading.setVisibility(View.GONE);

        return view;
    }

    @OnClick(R.id.bt_cancel)
    public void onClickCancellationButton() {
        if (baseUrl == null || customerId == null) return;

        spinnerLoading.setVisibility(View.VISIBLE);

        execute();
    }

    private void execute() {
        connection.cancel(customerId, paymentCreatedId).enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("CancelReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage("Status retornado: " + response.body().getStatus());
                } else {
                    showMessage("Falha no cancelamento.");
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                call.cancel();

                showMessage("Erro ao realizar requisição.");

                spinnerLoading.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void setFieldsIfNecessary() {
        etPaymentId.setText(paymentCreatedId != null ? paymentCreatedId : "");
    }

    private void getReceivedParams() {
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            paymentCreatedId = bundle.getString("paymentIdCreated");
            customerId = bundle.getString("customerId");
            action = bundle.getString("action");
        }
    }
}
