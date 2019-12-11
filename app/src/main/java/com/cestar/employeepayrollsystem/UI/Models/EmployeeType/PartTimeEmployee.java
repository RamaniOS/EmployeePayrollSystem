package com.cestar.employeepayrollsystem.UI.Models.EmployeeType;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.MISC.NameLengthException;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.CommissionBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.FixedBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.Car;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.MotorCycle;

//Created by Ramanpreet Singh
public abstract class PartTimeEmployee extends EmployeeClass {

    private float rate;
    private int hoursWorked;

    public PartTimeEmployee()
    {
    }

    protected PartTimeEmployee(Parcel in) {
        super(in);
        rate = in.readFloat();
        hoursWorked = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(rate);
        dest.writeInt(hoursWorked);
    }

    public float getRate()
    {
        return rate;
    }

    private void setRate(float rate)
    {
        this.rate = rate;
    }

    public int getHoursWorked()
    {
        return hoursWorked;
    }

    private void setHoursWorked(int hoursWorked)
    {
        this.hoursWorked = hoursWorked;
    }

    public PartTimeEmployee(String name, int age, float rate, int hoursWorked)
    {
        super(name, age);
        this.rate = rate;
        this.hoursWorked = hoursWorked;
    }

    public abstract float calculateEarning();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void printMyData() {
        try {
            if (getName().length() < 5) throw new NameLengthException("Name must have more than 5 chars");
            else System.out.println("Name:" + " " + getName());
        } catch (NameLengthException e)  {
            System.out.println("Something happens wrong" + " " + e);
        }
        System.out.println("Year of Birth:" + " " + getBirthYear());
    }
}
