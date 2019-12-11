package com.cestar.employeepayrollsystem.UI.Models.Employee;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.FullTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.InternEmployee;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.PartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.MISC.IPrintable;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.Car;
import com.cestar.employeepayrollsystem.UI.Models.VehicleType.MotorCycle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Created by Ramanpreet Singh
public abstract class EmployeeClass implements IPrintable, Parcelable {

    //Properties
    private String name;
    private int age;
    float EARNING = 1000.0f;
    public String type;

    protected EmployeeClass(Parcel in) {
        name = in.readString();
        age = in.readInt();
        EARNING = in.readFloat();
        car = in.readParcelable(Car.class.getClassLoader());
        motorcycle = in.readParcelable(MotorCycle.class.getClassLoader());
        fullTime = in.readParcelable(FullTimeEmployee.class.getClassLoader());
        intern = in.readParcelable(InternEmployee.class.getClassLoader());
        partTime = in.readParcelable(PartTimeEmployee.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeFloat(EARNING);
        dest.writeTypedList(vehicleList);
        dest.writeParcelable(car, flags);
        dest.writeParcelable(motorcycle, flags);
        dest.writeParcelable(fullTime, flags);
        dest.writeParcelable(intern, flags);
        dest.writeParcelable(partTime, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EmployeeClass> CREATOR = new Creator<EmployeeClass>() {
        @Override
        public EmployeeClass createFromParcel(Parcel in) {
            return new EmployeeClass(in) {
                @Override
                public void printMyData() {

                }
            };
        }

        @Override
        public EmployeeClass[] newArray(int size) {
            return new EmployeeClass[size];
        }
    };

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Vehicle> vehicleList = new ArrayList<>();
    public Car car;
    public MotorCycle motorcycle;
    public FullTimeEmployee fullTime;
    public InternEmployee intern;
    public PartTimeEmployee partTime;

    //Constructor
    public EmployeeClass() {
    }

    public EmployeeClass(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    //Getters & Setters
    public String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    private void setAge(int age)
    {
        this.age = age;
    }

    //Custom functions
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getBirthYear()
    {
        return calculateBirthYear();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private int calculateBirthYear()
    {
        return LocalDate.now().getYear() - getAge();
    }



    public abstract void printMyData();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getVehicleDetails() {

        if (car != null) {
            //
            if (car.getVehicleType().equalsIgnoreCase("car")) {
                // call method...
                car.printMyDisplay();
            }

        } else if(motorcycle != null){

            //
            if (motorcycle.getVehicleType().equalsIgnoreCase("motorcycle")) {

                // call method...
                motorcycle.printMyDisplay();
            }
        }else {

            System.out.println("Employee has no Vehicle registered");
        }
    }
}
