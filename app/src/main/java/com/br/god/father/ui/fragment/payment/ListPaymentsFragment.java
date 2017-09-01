package com.br.god.father.ui.fragment.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.god.father.R;
import com.br.god.father.connection.ApiUtils;
import com.br.god.father.connection.Connection;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.model.Error;
import com.br.god.father.model.payment.PaymentListResponse;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.ui.fragment.BaseFragment;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPaymentsFragment extends BaseFragment {

    @BindView(R.id.spinner_loading_list_payment)
    ProgressBar spinnerLoading;

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    private ArrayAdapter<String> adapter;
    private List<PaymentListResponse> paymentListResponse = new ArrayList<>();
    private List<String> listOfPayments = new ArrayList<>();

    public static ListPaymentsFragment newInstance() {
        return new ListPaymentsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_payment, container, false);

        ButterKnife.bind(this, view);
        MainActivity.toolbar.setTitle(R.string.tittle_list_payments);

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfPayments);
        adapter.notifyDataSetChanged();

        validateAndSetConnectionParams();

        list();

        return view;
    }

    private void validateAndSetConnectionParams() {
        baseUrl = "";

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

    private void buildSubscriptionList() {
        ListView lv = (ListView) this.getView().findViewById(R.id.layout_list_payment);

        if (paymentListResponse != null) {
            paymentListResponse.stream().forEach(creditCard -> listOfPayments.add(creditCard.getStatus()));
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfPayments);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view1, position, arg3) -> {
            view1.setSelected(true);

            showAlertDialogWithOKButton("Description", (String) ((TextView) view1).getText());
        });
    }

    public void list() {
        spinnerLoading.setVisibility(View.VISIBLE);

        connection.listPayments(ApiUtils.buildHeaders(customerId)).enqueue(new Callback<List<PaymentListResponse>>() {
            @Override
            public void onResponse(Call<List<PaymentListResponse>> call, Response<List<PaymentListResponse>> response) {
                if (response.isSuccessful()) {
                    paymentListResponse = response.body();

                    buildSubscriptionList();

                    showMessage(getString(R.string.msg_status_returned) + response.code());
                } else {
                    try {
                        Error error = new ObjectMapper().readValue(response.errorBody().string(), Error.class);

                        showErrorMessage(error);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                spinnerLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();

                spinnerLoading.setVisibility(View.INVISIBLE);

                showAlertDialogWithOKButton("Erro", t.getMessage());
            }
        });
    }
}
