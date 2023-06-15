package com.example.erpnext.fragments.profile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.activities.drawerActivities.ProfileActivity;
import com.example.erpnext.models.EmployeesData;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //progressBar.setVisibility(View.VISIBLE);
        getEmployeeData();
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        empId = view.findViewById(R.id.employee_id);
        phone = view.findViewById(R.id.employee_phone);
        email = view.findViewById(R.id.employee_email);
        address = view.findViewById(R.id.employee_address);
        dob = view.findViewById(R.id.employee_dob);
        blood = view.findViewById(R.id.employee_blood);
        dateofjoining = view.findViewById(R.id.employee_dateofjoining);
        progressBar = view.findViewById(R.id.progressbar);
        return view;
    }

    public void getEmployeeData() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait...");
        progressDialog.setMessage("Fetching data");
        progressDialog.setCanceledOnTouchOutside(false);
        //progressBar.setVisibility(View.VISIBLE);
        sessionManager = new UserSessionManager(getContext());
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), sessionManager.getUserId()).enqueue(new Callback<EmployeesData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EmployeesData> call, Response<EmployeesData> response) {
                if (response.isSuccessful()) {
                    progressDialog.show();
                    EmployeesData responseModel = response.body();
                    if (responseModel != null && responseModel.getDocs() != null && !responseModel.getDocs().isEmpty()) {
                        EmployeesData.EmployeeDoc data = responseModel.getDocs().get(0);
                        String designation = data.getDesignation();
                        empId.setText(data.getEmployee());
                        phone.setText(data.getEmployee_number());
                        blood.setText(data.getBlood_group());
                        dateofjoining.setText(data.getDate_of_joining());
                        address.setText("No address");
                        email.setText(data.getPrefered_email());
                        dob.setText(data.getDate_of_birth());
                        address.setText(data.getCurrent_address());
                       // progressBar.setVisibility(View.GONE);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Null data", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);
                    }
                    progressDialog.dismiss();
                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Error Occurred");
                            builder.setMessage(errorResponse.getExcType());

                            // Set a positive button and its click listener
                            builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            //progressBar.setVisibility(View.GONE);
                        } catch (IOException e) {
                            e.printStackTrace();
                           // progressBar.setVisibility(View.GONE);
                        }
                    }
                    //Toast.makeText(MainActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmployeesData> call, Throwable t) {
                Toast.makeText(getContext(), "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}