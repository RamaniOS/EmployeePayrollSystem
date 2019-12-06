package com.cestar.employeepayrollsystem.UI.Models.Other;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CalculateInsuranceStat {

    /**
     * this method done in 4 steps
     * 1. check difference of years.
     * 2. check difference of months.
     * 3. check difference of days.
     * 4. calculation for remaining duration.
     * @return remaining duration
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String calculateInsuranceStatusOfVehicle(LocalDate insurDate){
        String insuranceDate = String.format("%s", insurDate);

        LocalDate from = LocalDate.now();
        LocalDate to;

        // check if date exceed the currentdate.
            try {
                if (insurDate.isAfter(from)) throw new com.cestar.employeepayrollsystem.UI.Models.Other.DateException("Date must not exceed the currentdate");

            } catch (com.cestar.employeepayrollsystem.UI.Models.Other.DateException e)  {
                System.out.println("Wrong date:" + " " + e);
        }

        int lastInsuranceYear = insurDate.getYear();
        int currentYear = LocalDate.now().getYear();

        // 1. check difference of years.
        if((currentYear - lastInsuranceYear) == 1){
            int currentMonth = LocalDate.now().getMonthValue();
            int currentDay = LocalDate.now().getDayOfMonth();

            int insuranceMonth = insurDate.getMonthValue();
            int insuranceDay = insurDate.getDayOfMonth();

            insuranceDate = insuranceDate.substring(5,10);
            insuranceDate = String.format("%d-%s", currentYear, insuranceDate);
            //System.out.println("Nitin 1:" + insuranceDate + ":");

            // 2. check difference of months.
            if((currentMonth - insuranceMonth) > 0){
                //to = LocalDate.parse(insuranceDate);
                return "Your insurance has crossed the expiry date, please contact to the insurance agency. :(";

            }else if((currentMonth - insuranceMonth) < 0){
                // set to date
                to =  LocalDate.parse(insuranceDate);

            }else{

                // 3. check difference of days.
                if((currentDay - insuranceDay) > 0){
                    //to = LocalDate.parse(insuranceDate);
                    return "Your insurance has crossed the expiry date, please contact to the insurance agency. :(";
                }else if((currentDay - insuranceDay) < 0){
                    to = LocalDate.parse(insuranceDate);
                }else{
                    //to = LocalDate.parse(insuranceDate);
                    return "Your insurance has crossed the expiry date, please contact to the insurance agency. :(";
                }

            }

        }else if((currentYear - lastInsuranceYear) > 1){
            //to = LocalDate.parse(insuranceDate);
            return "Your insurance has crossed the expiry date, please contact to the insurance agency. :(";

        }else{
            //to = LocalDate.parse(insuranceDate);
            return "Your insurance is covered for this year. :)";

        }

        // 4. calculation for remaining duration.
        LocalDate fromTemp = LocalDate.from(from);
        // remaining years
        long years = fromTemp.until(to, ChronoUnit.YEARS);
        fromTemp = fromTemp.plusYears(years);
        // remaining months
        long months = fromTemp.until(to, ChronoUnit.MONTHS);
        fromTemp = fromTemp.plusMonths(months);
        // remaining days
        long days = fromTemp.until(to, ChronoUnit.DAYS);
        fromTemp = fromTemp.plusDays(days);

        String strRemainingDuration;
        if(months > 0){
            strRemainingDuration = String.format("Your insurance date is in : %s months, %s days", months, days);
        }else{
            strRemainingDuration = String.format("Your insurance date is in : %s days", days);
        }

        return strRemainingDuration;
    }
}
