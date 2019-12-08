package com.cestar.employeepayrollsystem.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Shared.UserManager;

public class LoginActivity extends AppCompatActivity {


    private EditText userNameTextView;
    private EditText passwordTextView;
    private Switch rememberMeSwitch;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        userNameTextView = findViewById(R.id.txt_name);
        passwordTextView = findViewById(R.id.txt_dob);
        rememberMeSwitch = findViewById(R.id.btn_switch);
        loginButton = findViewById(R.id.btn_save);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonClicked();
            }
        });
        checkIsRememberMe();
    }

    private void checkIsRememberMe() {
        if(UserManager.getUserName(this).equals("null")) {
            rememberMeSwitch.setChecked(false);
            userNameTextView.setText("");
            passwordTextView.setText("");
        } else {
            rememberMeSwitch.setChecked(true);
            userNameTextView.setText(UserManager.getUserName(this));
            passwordTextView.setText(UserManager.getPassword(this));
        }
    }

    private Context context() {
        return LoginActivity.this;
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
                UserManager.setUserName(getApplicationContext(), user);
                UserManager.setPassword(getApplicationContext(), pass);
            }
            Intent intent = new Intent(context(), NavDrawerActivity.class);
            startActivity(intent);
        }
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
