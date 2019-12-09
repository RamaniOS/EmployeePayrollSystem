package com.cestar.employeepayrollsystem.UI.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cestar.employeepayrollsystem.R;
import com.cestar.employeepayrollsystem.UI.Helper.Helper;
import com.cestar.employeepayrollsystem.UI.Models.Employee.EmployeeClass;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.FullTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.InternEmployee;
import com.cestar.employeepayrollsystem.UI.Models.EmployeeType.PartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.CommissionBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Models.PartTimeSalaryType.FixedBasedPartTimeEmployee;
import com.cestar.employeepayrollsystem.UI.Shared.EmployeeManager;
import com.cestar.employeepayrollsystem.UI.Shared.UserDataManager;

import static com.cestar.employeepayrollsystem.R.array.array_emp_type;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddEmpPayrollFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddEmpPayrollFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEmpPayrollFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    ConstraintLayout partConst;
    ConstraintLayout internConst;
    ConstraintLayout fullConst;
    EditText userNET;
    EditText dobET;
    Spinner spinnerEmp;
    EditText rateET;
    EditText hoursET;
    Spinner partTimeSp;
    EditText percOrFixET;
    EditText schoolET;
    EditText salaryET;
    EditText bonusET;
    Button saveBtn;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddEmpPayrollFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEmpPayrollFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEmpPayrollFragment newInstance(String param1, String param2) {
        AddEmpPayrollFragment fragment = new AddEmpPayrollFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_emp_payroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        partConst = view.findViewById(R.id.partTimeView);
        internConst = view.findViewById(R.id.internView);
        fullConst = view.findViewById(R.id.fullTimeView);

        spinnerEmp = view.findViewById(R.id.spinnerEmp);
        partTimeSp = view.findViewById(R.id.part_time_sp);

        userNET = view.findViewById(R.id.txt_name);
        dobET = view.findViewById(R.id.txt_dob);
        rateET = view.findViewById(R.id.txt_rate);
        hoursET = view.findViewById(R.id.txt_hours_worked);
        percOrFixET = view.findViewById(R.id.txt_PerOrFixed);
        schoolET = view.findViewById(R.id.txt_SchoolName);
        salaryET = view.findViewById(R.id.textInputSalary);
        bonusET = view.findViewById(R.id.textInputBonus);
        saveBtn = view.findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currItem = spinnerEmp.getSelectedItem().toString();
                if(currItem.equalsIgnoreCase("Full Time")){
                    saveClicked(v, 0);
                }else if(currItem.equalsIgnoreCase("Part Time")){
                    saveClicked(v, 1);
                }else if(currItem.equalsIgnoreCase("Intern")){
                    saveClicked(v, 2);
                }

            }
        });

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(array_emp_type));
//        spinnerEmp.setAdapter(arrayAdapter);

        spinnerEmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // by default
                        iniTypeViews(view, 0);
                        break;
                    case 1:
                        // by default
                        iniTypeViews(view, 1);
                        break;
                    case 2:
                        // by default
                        iniTypeViews(view, 2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // by default
        iniTypeViews(view, 0);
    }

    void saveClicked(View v,Integer type){

        if(checkValidation()){
            EmployeeClass employeeClass;

            switch (type) {
                case 0:
                    employeeClass = new FullTimeEmployee("abc", 12, 2, 3);
                    break;
                case 1:
                    String partTimeType = partTimeSp.getSelectedItem().toString();
                    if(partTimeType.equalsIgnoreCase("Commision")){
                        employeeClass = new CommissionBasedPartTimeEmployee("",  23, 1, 5, 20);
                    }else{
                        employeeClass = new FixedBasedPartTimeEmployee("",  23, 1, 23, 34);
                    }

                    break;
                default:
                    employeeClass = new InternEmployee("",  12, "");
                    break;

            }

            Helper.showAlertCommon(getContext(), "Employee Added Succesfully.");

            EmployeeManager.addNewEmployee(employeeClass);
        }else{

        }



    }

    void iniTypeViews(View v,Integer type){

        switch (type) {
            case 0:
                partConst.setVisibility(v.GONE);
                internConst.setVisibility(v.GONE);
                fullConst.setVisibility(v.VISIBLE);
                break;
            case 1:
                partConst.setVisibility(v.VISIBLE);
                internConst.setVisibility(v.GONE);
                fullConst.setVisibility(v.GONE);
                break;
            case 2:
                partConst.setVisibility(v.GONE);
                internConst.setVisibility(v.VISIBLE);
                fullConst.setVisibility(v.GONE);
                break;
        }

    }

    void iniPartTimeViews(View v,Integer type){

        switch (type) {
            case 0:
                percOrFixET.setHint("Commision Percentage");
                break;
            case 1:
                percOrFixET.setHint("Fixed Amount");
                break;

        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //
    public Boolean checkValidation(){

        if(userNET.getText().toString().isEmpty()){
            Helper.showAlertCommon(getContext(), "User Name is required!");
            return false;
        }
        else if(dobET.getText().toString().isEmpty()){
            Helper.showAlertCommon(getContext(), "Age is required!");
            return false;
        }

        String currItem = spinnerEmp.getSelectedItem().toString();
        if(currItem.equalsIgnoreCase("Full Time")){
            if(salaryET.getText().toString().isEmpty()){
                Helper.showAlertCommon(getContext(), "Salary is required!");
                return false;
            }
            else if(bonusET.getText().toString().isEmpty()){
                Helper.showAlertCommon(getContext(), "Bonus is required!");
                return false;
            }else{
                return true;
            }
        }else if(currItem.equalsIgnoreCase("Part Time")){
            if(rateET.getText().toString().isEmpty()){
                Helper.showAlertCommon(getContext(), "Rate is required!");
                return false;
            }
            else if(hoursET.getText().toString().isEmpty()){
                Helper.showAlertCommon(getContext(), "Hours is required!");
                return false;
            }
            else if(percOrFixET.getText().toString().isEmpty()){
                String strPartType = partTimeSp.getSelectedItem().toString();
                if(strPartType.equalsIgnoreCase("Commision")){
                    Helper.showAlertCommon(getContext(), "Commision Percentage is required!");
                }else{
                    Helper.showAlertCommon(getContext(), "Fixed Amount is required!");
                }

                return false;
            }else{
                return true;
            }
        }else if(currItem.equalsIgnoreCase("Intern")){
            if(schoolET.getText().toString().isEmpty()){
                Helper.showAlertCommon(getContext(), "School Name is required!");
                return false;

            }else{
                return true;
            }
        }

        return true;

    }
}
