package com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType;


import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.PartTimeEmployee;

//Created by Ramanpreet Singh
public class FixedBasedPartTimeEmployee extends PartTimeEmployee {

    private float fixedAmount;

    private float getFixedAmount()
    {
        return fixedAmount;
    }

    private void setFixedAmount(float fixedAmount)
    {
        this.fixedAmount = fixedAmount;
    }

    public FixedBasedPartTimeEmployee()
    {
    }

    public FixedBasedPartTimeEmployee(String name, int age, float rate, int hoursWorked, float fixedAmount)
    {
        super(name, age, rate, hoursWorked);
        this.fixedAmount = fixedAmount;
    }

    @Override
    public float calculateEarning()
    {
        return (getRate() * getHoursWorked() + getFixedAmount());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void printMyData() {


        super.printMyData();

        // call method to get vehicle detail
        getVehicleDetails();

        System.out.println("Employee is PartTime / Fixed Amt");
        System.out.println("    - Rate:" + " $" + getRate());
        System.out.println("    - HoursWorked:" + " " + getHoursWorked());
        System.out.println("    - Fixed Amount:" + " $" + getFixedAmount());
        System.out.println("    - Earnings:" + " $" + calculateEarning());


    }
}
