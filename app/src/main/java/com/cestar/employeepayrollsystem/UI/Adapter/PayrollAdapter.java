package com.cestar.employeepayrollsystem.UI.Adapter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.ui.home.AddEmpPayrollFragment;

import java.util.List;

public class PayrollAdapter extends RecyclerView.Adapter<PayrollAdapter.PayrollViewHolder> {

    private List<EmployeeClass> employees;

    public PayrollAdapter(List<EmployeeClass> employees) {
        this.employees = employees;
    }


    @NonNull
    @Override
    public PayrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payroll, parent, false);
        return new PayrollAdapter.PayrollViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final PayrollViewHolder holder, int position) {
        final EmployeeClass employee = employees.get(position);
        holder.txtName.setText("Full Name: " + employee.getName());
        holder.txtEType.setText("Employee Type: " + "Full Time");
        holder.txtTotalEarning.setText("Birth Year: " + String.valueOf(employee.getBirthYear()));
        holder.txtHasVehicle.setText(employee.getVehicleList().size() > 0 ? "Employee has Vehicle." : "Employee has no Vehicle.");
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public static class PayrollViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtEType;
        public TextView txtTotalEarning;
        public TextView txtHasVehicle;
        public PayrollViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEType = itemView.findViewById(R.id.txtEType);
            txtTotalEarning = itemView.findViewById(R.id.txtTotalEarning);
            txtHasVehicle = itemView.findViewById(R.id.txtHasVehicle);
        }
    }
}
