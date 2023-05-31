package com.example.erpnext.activities.drawerActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.activities.MainActivity;
import com.example.erpnext.models.EmployeesData;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    UserSessionManager sessionManager;
    TextView nametxt, usertxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        nametxt = findViewById(R.id.usernametxtprofile);
        usertxt = findViewById(R.id.userroletxt);
        getEmployeeData();
        sessionManager = new UserSessionManager(ProfileActivity.this);
        Toast.makeText(this, ""+sessionManager.getKeyEmployeeNamingSeries(), Toast.LENGTH_SHORT).show();
        nametxt.setText(sessionManager.getFullName());
    }

    public void getEmployeeData() {
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), sessionManager.getUserId()).enqueue(new Callback<EmployeesData>() {
            @Override
            public void onResponse(Call<EmployeesData> call, Response<EmployeesData> response) {
                System.out.println("Session"+sessionManager.getKeyEmployeeNamingSeries());
                if (response.isSuccessful()) {
                    EmployeesData responseModel = response.body();
                    if (responseModel != null && responseModel.getDocs() != null && !responseModel.getDocs().isEmpty()) {
                        EmployeesData.EmployeeDoc data = responseModel.getDocs().get(0);
                        String designation = data.getDesignation();
                        usertxt.setText(designation);

                    } else {
                        Toast.makeText(ProfileActivity.this, "Null data", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                            builder.setTitle("Error Occurred");
                            builder.setMessage(errorResponse.getExcType());

                            // Set a positive button and its click listener
                            builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //Toast.makeText(MainActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EmployeesData> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
            }
        });
    }
}