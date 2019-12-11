package com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.PartTimeEmployee;

//Created by Ramanpreet Singh
public class CommissionBasedPartTimeEmployee extends PartTimeEmployee {

    private float CommissionPercentage;

    public CommissionBasedPartTimeEmployee()
    {
    }

    public CommissionBasedPartTimeEmployee(String name, int age, float rate, int hoursWorked, float CommissionPercentage)
    {
        super(name, age, rate, hoursWorked);
        this.CommissionPercentage = CommissionPercentage;
    }

    protected CommissionBasedPartTimeEmployee(Parcel in) {
        super(in);
        CommissionPercentage = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(CommissionPercentage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommissionBasedPartTimeEmployee> CREATOR = new Creator<CommissionBasedPartTimeEmployee>() {
        @Override
        public CommissionBasedPartTimeEmployee createFromParcel(Parcel in) {
            return new CommissionBasedPartTimeEmployee(in);
        }

        @Override
        public CommissionBasedPartTimeEmployee[] newArray(int size) {
            return new CommissionBasedPartTimeEmployee[size];
        }
    };

    private float getCommissionPercentage()
    {
        return CommissionPercentage;
    }

    private void setCommission(float CommissionPercentage)
    {
        CommissionPercentage = CommissionPercentage;
    }

    private float calculateHours()
    {
        return getRate() * getHoursWorked();
    }

    private float calculateCommission()
    {
        float calculateHours = calculateHours();
        return (calculateHours + ((calculateHours * getCommissionPercentage()) / 100));
    }

    @Override
    public float calculateEarning()
    {
        return calculateCommission();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void printMyData() {
        super.printMyData();

        // call method to get vehicle detail
        super.getVehicleDetails();

        System.out.println("Employee is PartTime / Commissioned");
        System.out.println("    - Rate:" + " $" + getRate());
        System.out.println("    - HoursWorked:" + " " + getHoursWorked());
        System.out.println("    - Commission:" + " " + getCommissionPercentage()+"%");
        System.out.println("    - Earnings:" + " $" + calculateEarning());

    }
}