package com.cestar.employeepayrollsystem.UI.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cestar.employeepayrollsystem.UI.Activities.EmployeeDetailActivity;
import com.cestar.employeepayrollsystem.UI.Activities.VehicleDetailActivity;
import com.cestar.employeepayrollsystem.UI.Models.Vehicle.Vehicle;

import com.cestar.employeepayrollsystem.R;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder> {

    private List<Vehicle> vehicles;

    public VehicleAdapter(List<Vehicle> Vehicles) {
        this.vehicles = Vehicles;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        final Vehicle vehicle = vehicles.get(position);
        holder.txtType.setText(vehicle.getVehicleType());
        holder.txtMaker.setText(vehicle.getManufacturer());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent vAct = new Intent(v.getContext(), VehicleDetailActivity.class);

                vAct.putExtra("vehDetail", vehicle);

                v.getContext().startActivity(vAct);

            }

        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public static class VehicleViewHolder extends RecyclerView.ViewHolder {
        public TextView txtType;
        public TextView txtMaker;
        public VehicleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txtVType);
            txtMaker = itemView.findViewById(R.id.txtVMaker);

        }
    }
}
