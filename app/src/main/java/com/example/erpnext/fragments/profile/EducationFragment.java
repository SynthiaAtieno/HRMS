package com.example.erpnext.fragments.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.EmployeEducationAdapter;
import com.example.erpnext.models.EmployeesData;
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

    UserSessionManager sessionManager;
    RecyclerView recyclerViewEducation;
    LinearLayoutManager linearLayoutManager;
    List<EmployeesData.EmployeeEducation> educationList = new ArrayList<>();
    EmployeEducationAdapter adapter;

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
        recyclerViewEducation.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getEmployeeData();
    }


    public void getEmployeeData() {
        sessionManager = new UserSessionManager(requireContext());
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), sessionManager.getUserId()).enqueue(new Callback<EmployeesData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EmployeesData> call, Response<EmployeesData> response) {
                if (response.isSuccessful()) {
                    EmployeesData employeesData = response.body();
                    if (employeesData != null && employeesData.getDocs() != null && !employeesData.getDocs().isEmpty()) {
                        EmployeesData.EmployeeDoc employees = employeesData.getDocs().get(0);
                        if (!employees.getEducation().isEmpty()){
                            educationList.addAll(employees.getEducation());
                            adapter.notifyDataSetChanged();
                 /*       for (EmployeesData.EmployeeDoc employee : employees) {
                            List<EmployeesData.EmployeeEducation> educationList = employee.getEducation();
                            List<String> qualifications = new ArrayList<>();
                            //System.out.println("educationList = " + educationList);
                            for (EmployeesData.EmployeeEducation education : educationList) {
                                String qualification = education.getQualification();
                                qualifications.add(qualification);
                            }
                            // Create a formatted string with the qualifications
                            String qualificationsText = TextUtils.join("\n\n", qualifications);

                            // Set the text in the TextView
                        }
*/
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setTitle("No Data");
                            builder.setMessage("Your education details has not been updated");

                            // Set a positive button and its click listener
                            builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            //progressBar.setVisibility(View.GONE);
                            //Toast.makeText(requireContext(), "Your education details has not been added yet", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Null data", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    if (response.errorBody() != null) {
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
                    //Toast.makeText(MainActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmployeesData> call, Throwable t) {
                Toast.makeText(getContext(), "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
                // progressBar.setVisibility(View.GONE);
            }
        });
    }
}