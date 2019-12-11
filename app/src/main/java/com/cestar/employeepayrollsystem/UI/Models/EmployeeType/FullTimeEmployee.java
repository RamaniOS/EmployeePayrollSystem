package com.cestar.employeepayrollsystem.UI.Models.EmployeeType;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.MISC.NameLengthException;

//Created by Ramanpreet Singh
public class FullTimeEmployee extends EmployeeClass {

    private float salary;
    private float bonus;

    protected FullTimeEmployee(Parcel in) {
        super(in);
        salary = in.readFloat();
        bonus = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(salary);
        dest.writeFloat(bonus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FullTimeEmployee> CREATOR = new Creator<FullTimeEmployee>() {
        @Override
        public FullTimeEmployee createFromParcel(Parcel in) {
            return new FullTimeEmployee(in);
        }

        @Override
        public FullTimeEmployee[] newArray(int size) {
            return new FullTimeEmployee[size];
        }
    };

    private float getSalary()
    {
        return salary;
    }

    private void setSalary(float salary)
    {
        this.salary = salary;
    }

    private float getBonus()
    {
        return bonus;
    }

    private void setBonus(float bonus)
    {
        this.bonus = bonus;
    }

    public FullTimeEmployee(String name, int age, float salary, float bonus)
    {
        super(name, age);
        this.salary = salary;
        this.bonus = bonus;
    }


    public float calculateEarning()
    {
        return getSalary() + getBonus();
    }

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

        // call method to get vehicle detail
        super.getVehicleDetails();

        System.out.println("Employee is FullTime");
        System.out.println("    - Salary:" + " $" + this.salary);
        System.out.println("    - Bonus:" + " $" + this.bonus);
        System.out.println("    - Earning:" + " $" + this.calculateEarning());

    }
}