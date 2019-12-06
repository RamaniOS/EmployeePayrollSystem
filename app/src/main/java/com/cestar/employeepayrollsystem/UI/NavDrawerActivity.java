package com.cestar.employeepayrollsystem.UI;

import android.os.Bundle;

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

    private AppBarConfiguration mAppBarConfiguration;

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


//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_list,
//                R.id.nav_help, R.id.nav_logout, R.id.nav_send)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager mFragMang = getSupportFragmentManager();
        FragmentTransaction mFragTrans = mFragMang.beginTransaction();
        mFragTrans.add(R.id.container, new HomeFragment());
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
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawers();
        }
    }



    public void setUpNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager mFragMang = getSupportFragmentManager();
                FragmentTransaction mFragTrans = mFragMang.beginTransaction();

                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        mFragTrans.replace(R.id.container, new HomeFragment());
                        break;
                    case R.id.nav_add_emp:
                        mFragTrans.replace(R.id.container, new AddEmpPayrollFragment());
                        break;
                    case R.id.nav_list:
                        mFragTrans.replace(R.id.container, new ListPayrollFragment());
                        break;
                }
                mFragTrans.commit();
                drawer.closeDrawers();

                return  false;
            }
        });
    }
}
