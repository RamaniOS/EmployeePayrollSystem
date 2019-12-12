package com.cestar.employeepayrollsystem.UI.Models.EmployeeType;

import android.os.Build;
import android.os.Parcel;
import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.MISC.NameLengthException;

//Created by Ramanpreet Singh
public class InternEmployee extends EmployeeClass {

    private String schoolName;

    protected InternEmployee(Parcel in) {
        super(in);
        schoolName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(schoolName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InternEmployee> CREATOR = new Creator<InternEmployee>() {
        @Override
        public InternEmployee createFromParcel(Parcel in) {
            return new InternEmployee(in);
        }

        @Override
        public InternEmployee[] newArray(int size) {
            return new InternEmployee[size];
        }
    };

    public String getSchoolName()
    {
        return schoolName;
    }

    private void setSchoolName(String schoolName)
    {
        this.schoolName = schoolName;
    }

    public InternEmployee()
    {
    }

    public InternEmployee(String name, int age, String schoolName)
    {
        super(name, age);
        this.schoolName = schoolName;
    }

    public float calculateEarning()
    {
        return 1000.0f;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void printMyData()
    {
        try {
            if (getName().length() < 5) throw new NameLengthException("Name must have more than 5 chars");
            else System.out.println("Name:" + " " + getName());
        } catch (NameLengthException e)  {
            System.out.println("Something happens wrong" + " " + e);
        }
        System.out.println("Year of Birth:" + " " + getBirthYear());

        // call method to get vehicle detail
        super.getVehicleDetails();

        System.out.println("Employee is Intern");
        System.out.println("    - SchoolName:" + " " + getSchoolName());
        System.out.println("    - Earnings:" + " $" + calculateEarning());
    }
}
