package com.example.erpnext.fragments.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.DateUtils;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OverviewFragment extends Fragment {
    TextView empId, phone, email, dob, blood, address, dateofjoining;
    UserSessionManager sessionManager;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        progressBar = view.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        empId = view.findViewById(R.id.employee_id);
        phone = view.findViewById(R.id.employee_phone);
        email = view.findViewById(R.id.employee_email);
        address = view.findViewById(R.id.employee_address);
        dob = view.findViewById(R.id.employee_dob);
        blood = view.findViewById(R.id.employee_blood);
        dateofjoining = view.findViewById(R.id.employee_dateofjoining);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //progressBar.setVisibility(View.VISIBLE);
        getEmployeeData();
        //progressBar.setVisibility(View.GONE);
    }


    public void getEmployeeData() {
        // progressDialog = new ProgressDialog(getContext());
        /*progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Fetching data");
        progressDialog.setCanceledOnTouchOutside(false);*/
        //
        sessionManager = new UserSessionManager(getContext());
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(),"sid="+ sessionManager.getUserId()).enqueue(new Callback<EmployeeDataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                if (response.isSuccessful()) {
                    EmployeeDataResponse responseModel = response.body();
                    if (responseModel != null && responseModel.getData() != null) {
                        EmployeeDataResponse.Data data = responseModel.getData();
                        if (data != null) {


                            String dateOfBirth = DateUtils.convertStringToDate(data.getDateOfBirth(), "yyyy-MM-dd", "dd MMM yyy");

                            String dateOfJoining = DateUtils.convertStringToDate(data.getDateOfJoining(), "yyyy-MM-dd", "dd MMM yyy");
                            String empIdtext = data.getEmployee();

                            empId.setText(empIdtext);
                            phone.setText(data.getEmployeeNumber());
                            blood.setText(data.getBloodGroup());
                            dateofjoining.setText(dateOfJoining);
                            address.setText(data.getCurrentAddress());
                            email.setText(data.getPreferedEmail());
                            dob.setText(dateOfBirth);
                            address.setText(data.getCurrentAddress());
                            progressBar.setVisibility(View.GONE);
                        } else {

                            Toast.makeText(requireContext(), "Employee data is empty", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                        // progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getContext(), "Null data", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Error Occurred");
                            builder.setMessage(errorResponseJson);

                            // Set a positive button and its click listener
                            builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            //progressBar.setVisibility(View.GONE);
                        } catch (IOException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                //System.out.println("Onfailure" + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}