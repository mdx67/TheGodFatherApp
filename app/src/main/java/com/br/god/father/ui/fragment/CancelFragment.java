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
import com.br.god.father.model.AuthorizationResponse;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Utils;

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

        setConnectionParams();

        getReceivedParams();
        setFieldsIfNecessary();

        spinnerLoading.setVisibility(View.GONE);

        return view;
    }

    private void setConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("paymentUrl");

        if (baseUrl == null) {
            showMessage(getString(R.string.msg_add_payments_url));

            return;
        }

        CustomerApp mainCustomer = ((MainActivity) getActivity()).getMainCustomer();

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return;
        }

        customerId = mainCustomer.getId();
        connection = ApiUtils.getConnection(baseUrl);
    }


    @OnClick(R.id.bt_cancel)
    public void onClickCancellationButton() {
        spinnerLoading.setVisibility(View.VISIBLE);

        execute();
    }

    private void execute() {
        connection.cancel(ApiUtils.buildHeaders(customerId), etPaymentId.getText().toString()).enqueue(new Callback<AuthorizationResponse>() {
            @Override
            public void onResponse(Call<AuthorizationResponse> call, Response<AuthorizationResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("CancelReturn:", response.body().toString());

                    ((MainActivity) getActivity()).removeContent();

                    showMessage(getString(R.string.msg_status_returned) + response.body().getStatus());
                } else {
                    showMessage(getString(R.string.msg_cancellation_fail));
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<AuthorizationResponse> call, Throwable t) {
                call.cancel();

                showMessage(getString(R.string.msg_request_error));

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
            action = bundle.getString("action");
        }
    }
}
