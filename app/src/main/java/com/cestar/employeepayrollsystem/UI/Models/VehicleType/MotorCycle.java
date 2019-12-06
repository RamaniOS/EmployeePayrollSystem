/*
Created by: Nitin Jaswal
 */
package com.cestar.employeepayrollsystem.UI.Models.VehicleType;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cestar.employeepayrollsystem.UI.Models.Other.CalculateInsuranceStat;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

public class MotorCycle extends Vehicle {

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

}
