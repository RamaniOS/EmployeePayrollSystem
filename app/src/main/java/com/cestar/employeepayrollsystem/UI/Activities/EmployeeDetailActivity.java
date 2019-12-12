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
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.FullTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.InternEmployee;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.CommissionBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.FixedBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

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

        //Setup for different types of employees
        Object employee = intent.getParcelableExtra("empDetail");
        if (employee instanceof FullTimeEmployee) {
            setUpFull((FullTimeEmployee) employee);
        } else if (employee instanceof InternEmployee) {
            setUpIntern((InternEmployee) employee);
        } else if (employee instanceof CommissionBasedPartTimeEmployee) {
            setUpCommission((CommissionBasedPartTimeEmployee) employee);
        } else if (employee instanceof FixedBasedPartTimeEmployee) {
            setUpFixed((FixedBasedPartTimeEmployee) employee);
        }
    }

    void setUpFull(FullTimeEmployee employee) {
        userNET.setText(employee.getName());
        dobET.setText(String.valueOf(employee.getAge()));
        spinnerEmp.setSelection(0);
        salaryET.setText(String.valueOf(employee.getSalary()));
        bonusET.setText(String.valueOf(employee.getBonus()));
        partConst.setVisibility(View.GONE);
        internConst.setVisibility(View.GONE);
        fullConst.setVisibility(View.VISIBLE);
        showList(employee);
    }

    void setUpIntern(InternEmployee employee) {
        userNET.setText(employee.getName());
        dobET.setText(String.valueOf(employee.getAge()));
        spinnerEmp.setSelection(2);
        schoolET.setText(employee.getSchoolName());
        partConst.setVisibility(View.GONE);
        internConst.setVisibility(View.VISIBLE);
        fullConst.setVisibility(View.GONE);
        showList(employee);
    }

    void setUpCommission(CommissionBasedPartTimeEmployee employee) {
        userNET.setText(employee.getName());
        dobET.setText(String.valueOf(employee.getAge()));
        spinnerEmp.setSelection(1);
        partTimeSp.setSelection(0);
        rateET.setText(String.valueOf(employee.getRate()));
        hoursET.setText(String.valueOf(employee.getHoursWorked()));

        percOrFixET.setText(String.valueOf(employee.getCommissionPercentage()) + "%");
        partConst.setVisibility(View.VISIBLE);
        internConst.setVisibility(View.GONE);
        fullConst.setVisibility(View.GONE);
        showList(employee);
    }

    void setUpFixed(FixedBasedPartTimeEmployee employee) {
        userNET.setText(employee.getName());
        dobET.setText(String.valueOf(employee.getAge()));
        spinnerEmp.setSelection(1);
        partTimeSp.setSelection(1);
        rateET.setText(String.valueOf(employee.getRate()));
        hoursET.setText(String.valueOf(employee.getHoursWorked()));

        percOrFixET.setText("$" + String.valueOf(employee.getFixedAmount()));
        partConst.setVisibility(View.VISIBLE);
        internConst.setVisibility(View.GONE);
        fullConst.setVisibility(View.GONE);
        showList(employee);
    }

    void showList(EmployeeClass employee) {
        vehicles = employee.getVehicleList();
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = 270 * vehicles.size();
        recyclerView.setLayoutParams(params);
        if (vehicles.size() > 0) {
            vehicleView.setVisibility(View.VISIBLE);
            //
            initRecyclerView(employee);
        } else {
            vehicleView.setVisibility(View.GONE);
        }
    }

    void setUpViews(FullTimeEmployee employee) {

        userNET.setText(employee.getName());
        dobET.setText(String.valueOf(employee.getAge()));


        if (employee.type.equalsIgnoreCase("Full Time")) {
            spinnerEmp.setSelection(0);
            salaryET.setText(String.valueOf(employee.getSalary()));
            bonusET.setText(String.valueOf(employee.getBonus()));
            partConst.setVisibility(View.GONE);
            internConst.setVisibility(View.GONE);
            fullConst.setVisibility(View.VISIBLE);
        }
//        else if(employee.type.equalsIgnoreCase("Part Time")){
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
