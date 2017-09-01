package com.br.god.father.ui.fragment.subscription;

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
import com.br.god.father.model.SubscriptionListResponse;
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

public class ListSubscriptionFragment extends BaseFragment {

    @BindView(R.id.spinner_loading_list_subscription)
    ProgressBar spinnerLoading;

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    private ArrayAdapter<String> adapter;
    private SubscriptionListResponse subscriptionListResponse = new SubscriptionListResponse();
    private List<String> listOfSubscriptions = new ArrayList<>();

    public static ListSubscriptionFragment newInstance() {
        return new ListSubscriptionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_subscription, container, false);

        ButterKnife.bind(this, view);
        MainActivity.toolbar.setTitle(R.string.tittle_register_credit_card);

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfSubscriptions);
        adapter.notifyDataSetChanged();

        validateAndSetConnectionParams();

        list();

        return view;
    }

    private void validateAndSetConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("subscriptionUrl");

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
        ListView lv = (ListView) this.getView().findViewById(R.id.layout_list_subscription);

        if (subscriptionListResponse != null) {
            subscriptionListResponse.getSubscriptions().stream().forEach(creditCard -> listOfSubscriptions.add(creditCard.getDescription()));
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfSubscriptions);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view1, position, arg3) -> {
            view1.setSelected(true);

            showAlertDialogWithOKButton("Description", (String) ((TextView) view1).getText());
        });
    }

    public void list() {
        spinnerLoading.setVisibility(View.VISIBLE);

        connection.listSubscriptions(ApiUtils.buildHeaders(customerId)).enqueue(new Callback<SubscriptionListResponse>() {
            @Override
            public void onResponse(Call<SubscriptionListResponse> call, Response<SubscriptionListResponse> response) {
                if (response.isSuccessful()) {
                    subscriptionListResponse = response.body();

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
