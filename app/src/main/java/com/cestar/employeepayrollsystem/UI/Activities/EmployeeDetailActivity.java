package com.cestar.employeepayrollsystem.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Adapter.VehicleAdapter;
import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailActivity extends AppCompatActivity {

    private List<Vehicle> vehicles;

    ConstraintLayout partConst;
    ConstraintLayout internConst;
    ConstraintLayout fullConst;
    ConstraintLayout vehicleView;

    RecyclerView recyclerView;

    Spinner spinnerEmp;
    Spinner partTimeSp;

    EditText userNET;
    EditText dobET;
    EditText rateET;
    EditText hoursET;
    EditText percOrFixET;
    EditText schoolET;
    EditText salaryET;
    EditText bonusET;

    private RecyclerView recyclerViewDetail;
    private VehicleAdapter vehicleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initialize
        partConst = findViewById(R.id.partTimeView);
        internConst = findViewById(R.id.internView);
        fullConst = findViewById(R.id.fullTimeView);
        vehicleView = findViewById(R.id.vehicleView);

        recyclerView = findViewById(R.id.recycleVehicle);

        spinnerEmp = findViewById(R.id.spinnerType);
        partTimeSp = findViewById(R.id.part_time_sp);

        spinnerEmp.setEnabled(false);
        partTimeSp.setEnabled(false);
        spinnerEmp.setClickable(false);
        partTimeSp.setEnabled(false);

        userNET = findViewById(R.id.txt_name);
        dobET = findViewById(R.id.txt_dob);
        rateET = findViewById(R.id.txt_rate);
        hoursET = findViewById(R.id.txt_hours_worked);
        percOrFixET = findViewById(R.id.txt_PerOrFixed);
        schoolET = findViewById(R.id.txt_SchoolName);
        salaryET = findViewById(R.id.textInputSalary);
        bonusET = findViewById(R.id.textInputBonus);

        Intent intent = getIntent();
        EmployeeClass employee = intent.getParcelableExtra("empDetail");

        setUpViews(employee);

    }

    void setUpViews(EmployeeClass employee) {


        userNET.setText(employee.getName());
        dobET.setText("Age: " + String.valueOf(employee.getAge()));

//
//        if(employee.type.equalsIgnoreCase("Full Time")){
//              spinnerEmp.setSelection(0);
//              salaryET.setText(employee.fullTime.getSalary());
//              bonusET.setText(employee.fullTime.getBonus());
//              partConst.setVisibility(View.GONE);
//             internConst.setVisibility(View.GONE);
//             fullConst.setVisibility(View.VISIBLE);
//        }else if(employee.type.equalsIgnoreCase("Part Time")){
//              spinnerEmp.setSelection(1);
//              rateET.setText(employee.partTime.getRate());
//              hoursET.setText(employee.partTime.getHours());
//
//            // ?? How to get part time types...to set percOrFixET??
//              partConst.setVisibility(View.VISIBLE);
//              internConst.setVisibility(View.GONE);
//              fullConst.setVisibility(View.GONE);
//
//        }else if(employee.type.equalsIgnoreCase("Intern")){
//              spinnerEmp.setSelection(2);
////            schoolET.setText(employee.partTime.getSchoolName());
//              partConst.setVisibility(View.GONE);
//              internConst.setVisibility(View.VISIBLE);
//              fullConst.setVisibility(View.GONE);
//
//        }

        vehicles = employee.getVehicleList();
        ViewGroup.LayoutParams params=recyclerView.getLayoutParams();
        params.height=270*vehicles.size();
        recyclerView.setLayoutParams(params);
        if(vehicles.size() > 0){
            vehicleView.setVisibility(View.VISIBLE);
            //
            initRecyclerView(employee);
        }else{
            vehicleView.setVisibility(View.GONE);
        }

    }

    private void initRecyclerView(EmployeeClass employee) {
        recyclerViewDetail = findViewById(R.id.recycleVehicle);
        vehicleAdapter = new VehicleAdapter(employee.vehicleList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewDetail.setLayoutManager(layoutManager);
        recyclerViewDetail.setAdapter(vehicleAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
