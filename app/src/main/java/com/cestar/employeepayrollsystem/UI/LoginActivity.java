package com.cestar.employeepayrollsystem.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Shared.UserManager;

public class LoginActivity extends AppCompatActivity {


    private EditText userNameTextView;
    private EditText passwordTextView;
    private Switch rememberMeSwitch;
    private Button loginButton;

    private static final String PREFS_NAME = "LoginActivity";
    private static final String REMEMBER_ME = "isRememberMe";
    private static final String USER_NAME = "user_name";
    private static final String PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        userNameTextView = findViewById(R.id.txt_user);
        passwordTextView = findViewById(R.id.txt_pass);
        rememberMeSwitch = findViewById(R.id.btn_switch);
        loginButton = findViewById(R.id.btn_login);
        rememberMeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchButtonChanged(buttonView, isChecked);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonClicked();
            }
        });
        checkIsRememberMe();
    }

    private void checkIsRememberMe() {
        boolean isRememberME = getPreference(context());
        rememberMeSwitch.setChecked(isRememberME);
        if(isRememberME) {
            userNameTextView.setText(getUserName(context()));
            passwordTextView.setText(getPassword(context()));
        } else {
            userNameTextView.setText("");
            passwordTextView.setText("");
        }
    }

    private Context context() {
        return LoginActivity.this;
    }

    private void switchButtonChanged(CompoundButton buttonView, boolean isChecked) {
        setPreference(context(), isChecked);
    }

    private void loginButtonClicked() {
        String user = userNameTextView.getText().toString();
        String pass = passwordTextView.getText().toString();
        if (!user.equals("user@employee.com") && user.trim().contains("")) {
            showAlert("User name should be 'user@employee.com'");
        } else if (!pass.equals("s3cr3t") && pass.trim().contains("")) {
            showAlert("Password should be 's3cr3t' ");
        } else {
            UserManager.setLoggedIn(getApplicationContext(),true);
            if(rememberMeSwitch.isChecked()) {
                setPreference(context(), true);
                setUserName(context(), user);
                setPassword(context(), pass);
            } else {
                setPreference(context(), false);
            }
            Intent intent = new Intent(context(), NavDrawerActivity.class);
            startActivity(intent);
        }
    }

    private static void setPreference(Context context, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(REMEMBER_ME, value);
        editor.commit();
    }

    private static boolean getPreference(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(REMEMBER_ME, false);
    }

    private static void setUserName(Context context, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER_NAME, value);
        editor.commit();
    }

    private static String getUserName(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(USER_NAME, "");
    }

    private static void setPassword(Context context, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PASSWORD, value);
        editor.commit();
    }

    private static String getPassword(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(PASSWORD, "");
    }

    private void showAlert(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Alert!");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
