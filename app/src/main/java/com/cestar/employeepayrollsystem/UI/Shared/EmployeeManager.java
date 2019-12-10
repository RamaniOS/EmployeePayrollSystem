package com.cestar.employeepayrollsystem.UI.Shared;

import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private static final EmployeeManager ourInstance = new EmployeeManager();

    public static EmployeeManager getInstance() {
        return ourInstance;
    }

    private static List<EmployeeClass> employees;

    private EmployeeManager() {
        employees = new ArrayList<>();
    }

    public static void addNewEmployee(EmployeeClass employee) {
        employees.add(employee);
    }

    public static int totalCount() {
        return employees.size();
    }

    public static List<EmployeeClass> fetchEmployees() {
        return employees;
    }
}
