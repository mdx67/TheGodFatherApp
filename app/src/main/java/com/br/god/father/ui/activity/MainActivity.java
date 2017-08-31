package com.br.god.father.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.br.god.father.R;
import com.br.god.father.model.CustomerApp;
import com.br.god.father.ui.fragment.AuthorizationFragment;
import com.br.god.father.ui.fragment.CancelFragment;
import com.br.god.father.ui.fragment.ConnectionSettingsFragment;
import com.br.god.father.ui.fragment.CustomerSettingsFragment;
import com.br.god.father.ui.fragment.DashboardFragment;
import com.br.god.father.ui.fragment.RegisterCreditCardFragment;
import com.br.god.father.ui.fragment.RegisterCustomerFragment;
import com.br.god.father.ui.fragment.SubscriptionFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(DashboardFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;

        if (menuItem.getItemId() == R.id.action_connection_settings) {
            fragmentClass = ConnectionSettingsFragment.class;
        }

        if (menuItem.getItemId() == R.id.action_customer_settings) {
            fragmentClass = CustomerSettingsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        return super.onOptionsItemSelected(menuItem);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_register_customer:
                fragmentClass = RegisterCustomerFragment.class;
                break;
            case R.id.nav_register_creditCard:
                fragmentClass = RegisterCreditCardFragment.class;
                break;
            case R.id.nav_plan:
                fragmentClass = SubscriptionFragment.class;
                break;
            case R.id.nav_authorize:
                fragmentClass = AuthorizationFragment.class;
                break;
            case R.id.nav_cancel:
                fragmentClass = CancelFragment.class;
                break;
            default:
                return true;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        replaceFragment(fragment);

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();

        return true;
    }
}
