/**
 * Created by: Nitin Jaswal
 */

package com.cestar.employeepayrollsystem.UI.Models.Vehicle;


import com.cestar.employeepayrollsystem.UI.Models.Other.IDispay;

import java.time.LocalDate;


/// This is the Vehicle class, containing the information of Employee's Vehicle.
public abstract class Vehicle implements IDispay {

    //-----------------------------------------------------------------------
//    public enum VehicleType {
//        car, motorcycle
//    }

    /** Properties*/
    protected String vehicleType;
    protected String manufacturer;
    protected String plateNo;
    protected String model;
    protected LocalDate insuranceDate;
    protected float milage;

    //-----------------------------------------------------------------------

    public Vehicle() {

    }

    /** Constructor*/

    public Vehicle(String vehicleType, String manufacturer, String plateNo, String model, LocalDate insuranceDate, float milage) {
        this.vehicleType = vehicleType;
        this.manufacturer = manufacturer;
        this.plateNo = plateNo;
        this.model = model;
        this.insuranceDate = insuranceDate;
        this.milage = milage;
    }

    //-----------------------------------------------------------------------
    /** Getter - Setter*/
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(LocalDate insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public float getMilage() {
        return milage;
    }

    public void setMilage(float milage) {
        this.milage = milage;
    }

    //-----------------------------------------------------------------------
    /** Helper methods*/
    // this is the method to detect if vehicle gives good/low/average
    public String statusOfMilage(Float milage){
        // check the type of vehicle
        if(this.vehicleType.equals("car")){
            if(milage > 15.0 && milage < 25.0){
                return "Average Milage";
            }else if(milage < 15.0){
                return "Low Milage";
            }else{
                return "Good Milage";
            }
        }else{
            if(milage > 40.0 && milage < 60.0){
                return "Average Milage";
            }else if(milage < 40.0){
                return "Low Milage";
            }else{
                return "Good Milage";
            }
        }

    }

     public abstract void printMyDisplay();

}
