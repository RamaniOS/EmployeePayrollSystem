package com.cestar.employeepayrollsystem.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cestar.employeepayrollsystem.R;

public class DetailSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(DetailSplashActivity.this, LoginActivity.class);
                DetailSplashActivity.this.startActivity(mainIntent);
                DetailSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
