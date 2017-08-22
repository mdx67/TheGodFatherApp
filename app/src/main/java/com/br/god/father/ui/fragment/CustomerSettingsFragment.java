package com.br.god.father.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        ListView lv = view.findViewById(R.id.layout_list_customer);

        if (customerAppList != null) {
            for (CustomerApp customerApp : customerAppList) {
                listOfCustomers.add(customerApp.getName());
            }
        }

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listOfCustomers);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                view.setSelected(true);

                String value = (String) ((TextView) view).getText();

                showAlertDialog(value);
            }
        });
    }

    private void showAlertDialog(final String customerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.msg_change_customer);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                updateMainCustomer(customerId);

                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @OnClick(R.id.bt_add_customer)
    public void onClickSaveButton() {
        saveParams();

        showMessage("Configuração atualizada!");
    }

    private void setParams() {
        String mainCustomer = ((MainActivity) getActivity()).getSharedPreferences("mainCustomer");

        if (mainCustomer == null) return;

        tvMainCustomer.setText(Utils.convertStringToCustomer(mainCustomer).getName());
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
                ((MainActivity) getActivity()).saveSharedPreferences("mainCustomer", customerApp.toString());

                tvMainCustomer.setText(customerApp.getName());

                break;
            }
        }
    }
}
