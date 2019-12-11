package com.cestar.employeepayrollsystem.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Adapter.VehicleAdapter;
import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

public class VehicleDetailActivity extends AppCompatActivity {

    Spinner spinnerType;
    Spinner spinnerMaker;

    EditText txt_plate;
    EditText txt_price;
    EditText txt_seater;

    private RecyclerView recyclerViewDetail;
    private VehicleAdapter vehicleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Vehicle Detail");

        spinnerType = findViewById(R.id.spinnerType);
        spinnerMaker = findViewById(R.id.spinnerMaker);

        txt_plate = findViewById(R.id.txt_plate);
        txt_price = findViewById(R.id.txt_price);
        txt_seater = findViewById(R.id.txt_seater);

        spinnerType.setEnabled(false);
        spinnerMaker.setEnabled(false);
        spinnerType.setClickable(false);
        spinnerMaker.setEnabled(false);

        // set titles...
        Intent intent = getIntent();
        Vehicle vehObj = intent.getParcelableExtra("vehDetail");

//        if(vehObj.getVehicleType().equalsIgnoreCase("Car")){
//            spinnerType.setSelection(0);
//        }else{
//            spinnerType.setSelection(1);
//        }
//
//        txt_plate.setText(vehObj.getPlateNo());
       // txt_price.setText(vehObj.getVehicleType().getPrice());
       // txt_price.setText(vehObj.getVehicleType().getSeater());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
