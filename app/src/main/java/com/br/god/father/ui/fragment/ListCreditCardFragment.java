package com.br.god.father.ui.fragment;

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
import com.br.god.father.model.CreditCardListResponse;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.model.Error;
import com.br.god.father.ui.activity.MainActivity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCreditCardFragment extends BaseFragment {

    @BindView(R.id.spinner_loading_list_credit_card)
    ProgressBar spinnerLoading;

    private static String baseUrl;
    private static String customerId;
    private static Connection connection;

    private ArrayAdapter<String> adapter;
    private CreditCardListResponse creditCardListResponse = new CreditCardListResponse();
    private List<String> listOfCreditCards = new ArrayList<>();

    public static ListCreditCardFragment newInstance() {
        return new ListCreditCardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_credit_card, container, false);

        ButterKnife.bind(this, view);
        MainActivity.toolbar.setTitle(R.string.tittle_register_credit_card);

        spinnerLoading.setVisibility(View.GONE);
        spinnerLoading.setClickable(false);

        validateAndSetConnectionParams();

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfCreditCards);
        adapter.notifyDataSetChanged();

        list();

        return view;
    }

    private void validateAndSetConnectionParams() {
        baseUrl = ((MainActivity) getActivity()).getSharedPreferences("walletUrl");

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

    private void buildCreditCardList() {
        ListView lv = (ListView) this.getView().findViewById(R.id.layout_list_credit_card);

        if (creditCardListResponse != null) {
            creditCardListResponse.getCreditCardResults().stream().forEach(creditCard -> listOfCreditCards.add(creditCard.getCreditCardId()));
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfCreditCards);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view1, position, arg3) -> {
            view1.setSelected(true);

            showAlertDialogWithOKButton("Credit Card Id", (String) ((TextView) view1).getText());
        });
    }

    public void list() {
        spinnerLoading.setVisibility(View.VISIBLE);

        connection.listCreditCard(ApiUtils.buildHeaders(customerId)).enqueue(new Callback<CreditCardListResponse>() {
            @Override
            public void onResponse(Call<CreditCardListResponse> call, Response<CreditCardListResponse> response) {
                if (response.isSuccessful()) {
                    creditCardListResponse = response.body();

                    buildCreditCardList();

                    showMessage(getString(R.string.msg_status_returned) + response.code());
                } else {
                    try {
                        Error error = new ObjectMapper().readValue(response.errorBody().string().toString(), Error.class);

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

//    In the future
//    try {
//        Class clazz = CancelFragment.class;
//
//        Fragment fragment = (Fragment) clazz.newInstance();
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("creditCard", (Serializable) creditCardListResponse.getCreditCardResults().stream().filter(c -> c.getCreditCardId().equals(value)));
//
//        fragment.setArguments(bundle);
//
//        ((MainActivity) getActivity()).replaceFragment(fragment);
//    } catch (java.lang.InstantiationException e) {
//        e.printStackTrace();
//    } catch (IllegalAccessException e) {
//        e.printStackTrace();
//    }

}
