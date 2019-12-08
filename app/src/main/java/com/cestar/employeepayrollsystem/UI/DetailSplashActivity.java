package com.cestar.employeepayrollsystem.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Shared.UserManager;

public class DetailSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_splash);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                // IF LOGIN USER

                Log.d("sff", "");

                if (UserManager.getLoggedStatus(getApplicationContext())) {
                    intent = new Intent(DetailSplashActivity.this, NavDrawerActivity.class);
                } else {
                    intent = new Intent(DetailSplashActivity.this, LoginActivity.class);
                }
                DetailSplashActivity.this.startActivity(intent);
                DetailSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
