package com.cestar.employeepayrollsystem.UI;

import android.content.Intent;
import android.os.Bundle;

import com.cestar.employeepayrollsystem.UI.Helper.Helper;
import com.cestar.employeepayrollsystem.UI.Shared.UserManager;
import com.cestar.employeepayrollsystem.UI.ui.home.AddEmpPayrollFragment;
import com.cestar.employeepayrollsystem.UI.ui.home.HomeFragment;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.cestar.employeepayrollsystem.UI.ui.home.ListPayrollFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import com.cestar.employeepayrollsystem.R;

public class NavDrawerActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // Added first fragment as Home
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction mFragTrans = manager.beginTransaction();
        mFragTrans.add(R.id.container, new HomeFragment());
        getSupportActionBar().setTitle("Home");
        mFragTrans.commit();

        setUpNavigationDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            finish();
        }
    }

    public void setUpNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction mFragTrans = manager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        mFragTrans.replace(R.id.container, new HomeFragment());
                        getSupportActionBar().setTitle("Home");
                        mFragTrans.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_add_emp:
                        mFragTrans.replace(R.id.container, new AddEmpPayrollFragment());
                        getSupportActionBar().setTitle("Add Employee Payroll");
                        mFragTrans.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_list:
                        mFragTrans.replace(R.id.container, new ListPayrollFragment());
                        getSupportActionBar().setTitle("Payroll List");
                        mFragTrans.commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_help:
                        Helper.showAlert(NavDrawerActivity.this, "Email: " + UserManager.getUserName(getApplicationContext()));
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        performLogout();
                        break;
                }
                return false;
            }
        });
    }

    public void performLogout() {
        UserManager.setLoggedIn(getApplicationContext(), false);
        Intent intent = new Intent(NavDrawerActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
