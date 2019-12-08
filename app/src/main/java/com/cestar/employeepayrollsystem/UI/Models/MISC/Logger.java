package com.cestar.employeepayrollsystem.UI.Models.MISC;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Created by Ramanpreet Singh
public class Logger {
    public static void log(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output.txt", true), true);
        out.write(message);
        out.close();
    }
}

