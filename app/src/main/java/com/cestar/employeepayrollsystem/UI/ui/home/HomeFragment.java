package com.cestar.employeepayrollsystem.UI.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Helper.Helper;
import com.cestar.employeepayrollsystem.UI.Shared.EmployeeManager;

public class HomeFragment extends Fragment {

    TextView dateTV;
    TextView timeTV;
    TextView empCountTV;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //
        initSetup(view);
    }

    private void initSetup(View view){

        dateTV = view.findViewById(R.id.dateId);
        timeTV = view.findViewById(R.id.timeId);
        empCountTV = view.findViewById(R.id.empCount);

        Helper objH = new Helper();
        dateTV.setText(objH.currentDate());
        timeTV.setText(objH.currentTime());
        String count = String.valueOf(EmployeeManager.totalCount());
        empCountTV.setText(count);
    }
}