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
public abstract class PartTimeEmployee extends EmployeeClass implements Parcelable {

    private float rate;
    private int hoursWorked;
    public CommissionBasedPartTimeEmployee commission;
    public FixedBasedPartTimeEmployee fixed;

    public PartTimeEmployee()
    {
    }

    protected PartTimeEmployee(Parcel in) {
        super(in);
        rate = in.readFloat();
        hoursWorked = in.readInt();
        commission = in.readParcelable(CommissionBasedPartTimeEmployee.class.getClassLoader());
        fixed = in.readParcelable(FixedBasedPartTimeEmployee.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(rate);
        dest.writeInt(hoursWorked);
        dest.writeParcelable(commission, flags);
        dest.writeParcelable(fixed, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PartTimeEmployee> CREATOR = new Creator<PartTimeEmployee>() {
        @Override
        public PartTimeEmployee createFromParcel(Parcel in) {
            return new PartTimeEmployee(in) {
                @Override
                public float calculateEarning() {
                    return 0;
                }
            };
        }

        @Override
        public PartTimeEmployee[] newArray(int size) {
            return new PartTimeEmployee[size];
        }
    };

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
