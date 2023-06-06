package com.example.erpnext.fragments.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.EmployeeEducationAdapter;
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
    TextView qualificationstxt;

    private RecyclerView recyclerView;
    private EmployeeEducationAdapter adapter;
    private List<EmployeesData.EmployeeDoc> employees;

    public EducationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getEmployeeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        qualificationstxt = view.findViewById(R.id.qualifications);
        return view;
    }

    public void getEmployeeData() {
        //progressBar.setVisibility(View.VISIBLE);
        sessionManager = new UserSessionManager(getContext());
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), sessionManager.getUserId()).enqueue(new Callback<EmployeesData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EmployeesData> call, Response<EmployeesData> response) {
                if (response.isSuccessful()) {
                    EmployeesData responseModel = response.body();
                    if (responseModel != null && responseModel.getDocs() != null && !responseModel.getDocs().isEmpty()) {

                        List<EmployeesData.EmployeeDoc> employees = responseModel.getDocs();

                        for (EmployeesData.EmployeeDoc employee : employees) {
                            List<EmployeesData.EmployeeEducation> educationList = employee.getEducation();
                            List<String> qualifications = new ArrayList<>();
                            System.out.println("educationList = " + educationList);
                            for (EmployeesData.EmployeeEducation education : educationList) {
//                                List<String> institutionName = education.;
//                                institutionName += education.getQualification()+"\n";
//                                qualifications.setText(institutionName);
                                String qualification = education.getQualification();
                                qualifications.add(qualification);
                                //int yearOfPassing = education.getYearOfPassing();

                                // Do something with the education data
//                                System.out.println("Institution Name: " + institutionName);
//                             System.out.println("Year of Passing: " + yearOfPassing);
                            }
                            // Create a formatted string with the qualifications
                            String qualificationsText = TextUtils.join("\n\n", qualifications);

                            // Set the text in the TextView
                            qualificationstxt.setText(qualificationsText);
                        }

                    } else {
                        Toast.makeText(getContext(), "Null data", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);
                    }

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
                // progressBar.setVisibility(View.GONE);
            }
        });
    }
}