package com.example.erpnext.fragments.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.EmployeEducationAdapter;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EducationFragment extends Fragment {

    ProgressBar progressBar;
    UserSessionManager sessionManager;
    RecyclerView recyclerViewEducation;
    LinearLayoutManager linearLayoutManager;
    List<EmployeeDataResponse.Education> educationList = new ArrayList<>();
    EmployeEducationAdapter adapter;

    LinearLayout linearLayout;

    public EducationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        recyclerViewEducation = view.findViewById(R.id.educationrecycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewEducation.setLayoutManager(linearLayoutManager);
        adapter = new EmployeEducationAdapter(educationList, getContext());
        progressBar = view.findViewById(R.id.educationProgressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewEducation.setAdapter(adapter);
        linearLayout = view.findViewById(R.id.nothingfoundeducationlayout);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getEmployeeData();
    }


    public void getEmployeeData() {
        sessionManager = new UserSessionManager(requireContext());
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), "sid="+ sessionManager.getUserId()).enqueue(new Callback<EmployeeDataResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                if (response.isSuccessful()) {
                    EmployeeDataResponse employeesData = response.body();
                    if (employeesData != null && employeesData.getData() != null) {
                        List<EmployeeDataResponse.Education> employees = employeesData.getData().getEducation();
                        if (!employees.isEmpty()){
                            educationList.addAll(employees);
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);

                        }else {
                            linearLayout.setVisibility(View.VISIBLE);
                            /*AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setTitle("No Data");
                            builder.setMessage("Your education details has not been updated");

                            // Set a positive button and its click listener
                            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();*/
                            progressBar.setVisibility(View.GONE);
                            //Toast.makeText(requireContext(), "Your education details has not been added yet", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Null data", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                    }

                } else {
                    if (response.errorBody() != null) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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
                    Toast.makeText(requireContext(), "Response not successful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
                 progressBar.setVisibility(View.GONE);
            }
        });
    }
}