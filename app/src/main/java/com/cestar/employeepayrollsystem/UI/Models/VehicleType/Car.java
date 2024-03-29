/*
Created by: Nitin Jaswal
 */
package com.cestar.employeepayrollsystem.UI.Models.VehicleType;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/// This is the Car class inherited from parent class 'Vehicle', containing the information of vehicle type car.
public class Car extends Vehicle implements Parcelable {

    //-----------------------------------------------------------------------
//    public enum FuelType {
//        petrol, diesel, gas, Tesla
//    }
    /** Properties*/
    protected float price;
    protected int seater;
    protected String fuelType;

    //-----------------------------------------------------------------------
    /** Constructor*/
    public Car(String vehicleType, String manufacturer, String plateNo, String model, String insuranceDate, float milage, float price, int seater, String fuelType) {
        super(vehicleType, manufacturer, plateNo, model, insuranceDate, milage);
        this.price = price;
        this.seater = seater;
        this.fuelType = fuelType;
    }

    //-----------------------------------------------------------------------

    protected Car(Parcel in) {
        super(in);
        price = in.readFloat();
        seater = in.readInt();
        fuelType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeFloat(price);
        dest.writeInt(seater);
        dest.writeString(fuelType);
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
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



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void printMyDisplay() {

        String vehicleType = getVehicleType();
        if(vehicleType == "N/A"){
            System.out.println("Employee has no Vehicle");//
        }else {
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
            float milage = getMilage();
            System.out.println("    - Milage:" + " " + milage + " km/hr");//
            System.out.println("    - Milage Status:" + " " + super.statusOfMilage(milage));
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }


}
