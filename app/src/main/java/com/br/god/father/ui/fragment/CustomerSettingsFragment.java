package com.br.god.father.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.br.god.father.R;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerSettingsFragment extends BaseFragment {

    @BindView(R.id.et_customer_settings_name)
    EditText etName;
    @BindView(R.id.et_customer_settings_id)
    EditText etId;
    @BindView(R.id.tv_customer_settings_main_customer)
    TextView tvMainCustomer;

    private ArrayAdapter<String> adapter;
    private List<CustomerApp> customerAppList = new ArrayList<>();
    private List<String> listOfCustomers = new ArrayList<>();

    public static CustomerSettingsFragment newInstance() {
        return new CustomerSettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_settings, container, false);

        ButterKnife.bind(this, view);

        MainActivity.toolbar.setTitle(R.string.tittle_settings);

        customerAppList = getSavedCustomers();

        setParams();

        buildList(view);

        return view;
    }

    private void buildList(View view) {
        ListView lv = (ListView) view.findViewById(R.id.layout_list_customer);

        if (customerAppList != null) {
            customerAppList.stream().forEach(customerApp -> listOfCustomers.add(customerApp.getName()));
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfCustomers);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, view1, position, arg3) -> {
            view1.setSelected(true);

            String value = (String) ((TextView) view1).getText();

            showAlertDialog(value);
        });
    }

    private void showAlertDialog(final String customerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.msg_change_customer);
        builder.setPositiveButton(android.R.string.yes, (dialog, id) -> {
            updateMainCustomer(customerId);

            dialog.dismiss();
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @OnClick(R.id.bt_add_customer)
    public void onClickSaveButton() {
        saveParams();

        showMessage("Configuração atualizada!");
    }

    private void setParams() {
        CustomerApp mainCustomer = ((MainActivity) getActivity()).getMainCustomer();

        if (mainCustomer == null) {
            showMessage(getString(R.string.msg_add_customer));

            return;
        }

        tvMainCustomer.setText(mainCustomer.getName());
    }

    private void saveParams() {
        if (customerAppList == null) customerAppList = new ArrayList<>();

        customerAppList.add(new CustomerApp(etId.getText().toString(), etName.getText().toString()));

        ((MainActivity) getActivity()).saveSharedPreferences(customerAppList);
    }

    private List<CustomerApp> getSavedCustomers() {
        Set<String> list = ((MainActivity) getActivity()).getSharedPreferencesCustomerList();

        if (list == null || list.isEmpty()) return null;

        List<CustomerApp> savedCustomers = new ArrayList<>();

        for (String value : list) {
            savedCustomers.add(Utils.convertStringToCustomer(value));
        }

        return savedCustomers;
    }

    private void updateMainCustomer(String customerId) {
        for (CustomerApp customerApp : customerAppList) {
            if (customerApp.getName().equals(customerId)) {
                ((MainActivity) getActivity()).updateMainCustomer(customerApp);

                tvMainCustomer.setText(customerApp.getName());

                break;
            }
        }
    }
}
