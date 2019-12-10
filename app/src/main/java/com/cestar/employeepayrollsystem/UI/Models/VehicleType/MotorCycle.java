/*
Created by: Nitin Jaswal
 */
package com.cestar.employeepayrollsystem.UI.Models.VehicleType;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.Other.CalculateInsuranceStat;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class MotorCycle extends Vehicle implements Parcelable {

    //-----------------------------------------------------------------------
//    public enum FuelType {
//        petrol, diesel, gas
//    }

    /** Properties*/
    protected float price;
    protected int seater;
    protected String fuelType;

    //-----------------------------------------------------------------------
    /** Constructor*/
    public MotorCycle(String vehicleType, String manufacturer, String plateNo, String model, LocalDate insuranceDate, float milage, float price, int seater, String fuelType) {
        super(vehicleType, manufacturer, plateNo, model, insuranceDate, milage);
        this.price = price;
        this.seater = seater;
        this.fuelType = fuelType;
    }

    //-----------------------------------------------------------------------

    protected MotorCycle(Parcel in) {
        super(in);
        price = in.readFloat();
        seater = in.readInt();
        fuelType = in.readString();
    }

    public static final Creator<MotorCycle> CREATOR = new Creator<MotorCycle>() {
        @Override
        public MotorCycle createFromParcel(Parcel in) {
            return new MotorCycle(in);
        }

        @Override
        public MotorCycle[] newArray(int size) {
            return new MotorCycle[size];
        }
    };

    /** Getter - Setter*/
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSeater() {
        return seater;
    }

    public void setSeater(int seater) {
        this.seater = seater;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    //-----------------------------------------------------------------------
    /** Helper */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String calculateInsuranceStatus() {
        // get the insurance date from Vehicle class getter method.
        LocalDate insurDate = getInsuranceDate();

        // use common public method to calculate the insurace status.
        CalculateInsuranceStat obj = new CalculateInsuranceStat();
        // call method.
        String strRemainingDuration = obj.calculateInsuranceStatusOfVehicle(insurDate);

        return strRemainingDuration;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void printMyDisplay() {

        String vehicleType = getVehicleType();

        if(vehicleType == "N/A"){
            System.out.println("Employee has no Vehicle");//
        }else{
            vehicleType = vehicleType.substring(0,1).toUpperCase() + vehicleType.substring(1).toLowerCase();
            System.out.println("Employee has a" + " " + vehicleType);//
            System.out.println("    - Manufacturer:" + " " + getManufacturer());//
            NumberFormat formatter = new DecimalFormat("###,###,###.##");
            System.out.println("    - Price:" + " $" + formatter.format(this.price));
            System.out.println("    - Seater:" + " " + this.seater);
            System.out.println("    - Fuel Type:" + " " + this.fuelType);
            System.out.println("    - Plate No.:" + " " + getPlateNo());//
            System.out.println("    - Model:" + " " + getModel());//
            System.out.println("    - Insurance Date:" + " " + getInsuranceDate());//
            System.out.println("    - Insurance Status:" + " " + calculateInsuranceStatus());
            float milage = getMilage();
            System.out.println("    - Milage:" + " " + milage + " km/hr");//
            System.out.println("    - Milage Status:" + " " + super.statusOfMilage(milage));
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(price);
        dest.writeInt(seater);
        dest.writeString(fuelType);
    }
}
