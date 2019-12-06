package com.cestar.employeepayrollsystem.UI.Models.Other;

public class DateException extends Exception {

    private String message;

    public DateException(String message)
    {
        super(message);
        this.message = message;
    }
}
