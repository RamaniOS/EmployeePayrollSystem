package com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.PartTimeEmployee;

//Created by Ramanpreet Singh
public class FixedBasedPartTimeEmployee extends PartTimeEmployee implements Parcelable {

    private float fixedAmount;

    protected FixedBasedPartTimeEmployee(Parcel in) {
        super(in);
        fixedAmount = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(fixedAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FixedBasedPartTimeEmployee> CREATOR = new Creator<FixedBasedPartTimeEmployee>() {
        @Override
        public FixedBasedPartTimeEmployee createFromParcel(Parcel in) {
            return new FixedBasedPartTimeEmployee(in);
        }

        @Override
        public FixedBasedPartTimeEmployee[] newArray(int size) {
            return new FixedBasedPartTimeEmployee[size];
        }
    };

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
