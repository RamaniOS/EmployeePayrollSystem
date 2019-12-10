package com.cestar.employeepayrollsystem.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Helper.Helper;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.Car;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.MotorCycle;
import com.cestar.employeepayrollsystem.UI.ui.home.AddEmpPayrollFragment;

import java.time.LocalDate;

public class AddVehicleActivity extends AppCompatActivity {

    Spinner spinnerType;
    Spinner spinnerMaker;

    EditText txt_plate;
    EditText txt_price;
    EditText txt_seater;

    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupIni();
    }

    public void setupIni() {

        spinnerType = findViewById(R.id.spinnerType);
        spinnerMaker = findViewById(R.id.spinnerMaker);

        txt_plate = findViewById(R.id.txt_plate);
        txt_price = findViewById(R.id.txt_price);
        txt_seater = findViewById(R.id.txt_seater);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(checkValidation()){
                    LocalDate insuranceDate = LocalDate.parse("2019-08-09");

                    Vehicle vehObj;
                    String strType = spinnerType.getSelectedItem().toString();
                    if(strType.equalsIgnoreCase("Car")){
                        vehObj = new Car(strType, spinnerMaker.getSelectedItem().toString(), txt_plate.getText().toString(), "MMM", insuranceDate, 10, Float.valueOf(txt_price.getText().toString()), 1, "");
                    }else{
                        vehObj = new MotorCycle(strType, spinnerMaker.getSelectedItem().toString(), txt_plate.getText().toString(), "MMM", insuranceDate, 10, Float.valueOf(txt_price.getText().toString()), 1, "");

                    }

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",vehObj);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }

            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        addVehTypeSpinner(0);
                        break;
                    case 1:
                        addVehTypeSpinner(1);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

    void addVehTypeSpinner(Integer type){
        if(type == 0){
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_car_maker));
            spinnerMaker.setAdapter(arrayAdapter);

        }else{
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.array_bike_maker));
            spinnerMaker.setAdapter(arrayAdapter);

        }

    }


    //
    public Boolean checkValidation(){

        if(txt_plate.getText().toString().isEmpty()){
            Helper.showAlertCommon(getApplicationContext(), "Plate Number is required!");
            return false;
        }
        else if(txt_price.getText().toString().isEmpty()){
            Helper.showAlertCommon(getApplicationContext(), "Price is required!");
            return false;
        }
        else if(txt_seater.getText().toString().isEmpty()){
            Helper.showAlertCommon(getApplicationContext(), "No. of Seats is required!");
            return false;
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
