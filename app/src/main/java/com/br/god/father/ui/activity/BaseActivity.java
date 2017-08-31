package com.br.god.father.ui.activity;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.br.god.father.R;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.ui.fragment.DashboardFragment;
import com.br.god.father.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseActivity extends AppCompatActivity {

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).addToBackStack(null).commit();
    }

    public void removeContent() {
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.fragment_content)).commit();

        MainActivity.toolbar.setTitle(R.string.app_name);

        replaceFragment(DashboardFragment.newInstance());
    }

    public String getSharedPreferences(String key) {
        SharedPreferences settings = getSharedPreferences("config_god_father_app", 0);

        return settings.getString(key, null);
    }

    public Set<String> getSharedPreferencesCustomerList() {
        SharedPreferences settings = getSharedPreferences("config_god_father_app", 0);

        return settings.getStringSet("customerList", null);
    }

    public void saveSharedPreferences(String key, String value) {
        if (key != null) {
            SharedPreferences settings = getSharedPreferences("config_god_father_app", 0);
            SharedPreferences.Editor editor = settings.edit();

            editor.putString(key, value);

            editor.commit();
        }
    }

    public void saveSharedPreferences(List<CustomerApp> customerList) {
        SharedPreferences settings = getSharedPreferences("config_god_father_app", 0);
        SharedPreferences.Editor editor = settings.edit();

        Set<String> tempSet = new HashSet<>();

        for (CustomerApp customer : customerList) {
            tempSet.add(customer.toString());
        }

        editor.putStringSet("customerList", tempSet);

        editor.commit();
    }

    public void updateMainCustomer(CustomerApp customerApp) {
        saveSharedPreferences("mainCustomer", customerApp.toString());
    }

    public CustomerApp getMainCustomer() {
        String mainCustomer = getSharedPreferences("mainCustomer");

        if (mainCustomer == null) {
            return null;
        }

        return Utils.convertStringToCustomer(getSharedPreferences("mainCustomer"));
    }
}
