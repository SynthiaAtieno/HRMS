package com.example.erpnext.activities.drawerActivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
//import android.provider.CalendarContract;
//import android.widget.ImageView;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.fragments.profile.AchievementFragment;
import com.example.erpnext.fragments.profile.EducationFragment;
import com.example.erpnext.fragments.profile.ExperienceFragment;
import com.example.erpnext.fragments.profile.OverviewFragment;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    UserSessionManager sessionManager;
    TextView nametxt, usertxt;
    AppCompatButton overviewbtn, expebtn, achievebtn, educationbtn;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isDarkThemePreferred()) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        nametxt = findViewById(R.id.usernametxtprofile);
        usertxt = findViewById(R.id.userroletxt);
        overviewbtn = findViewById(R.id.btn_overview);
        expebtn = findViewById(R.id.btn_experience);
        achievebtn = findViewById(R.id.btn_achievement);
        educationbtn = findViewById(R.id.btn_education);

        replaceFragment(new OverviewFragment());
        overviewbtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
        overviewbtn.setTextColor(Color.WHITE);
        overviewbtn.setOnClickListener(view -> {
            replaceFragment(new OverviewFragment());
            overviewbtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            overviewbtn.setTextColor(Color.WHITE);
            achievebtn.setTextColor(Color.WHITE);
            expebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            expebtn.setTextColor(Color.BLACK);
            educationbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            educationbtn.setTextColor(Color.BLACK);
            achievebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            achievebtn.setTextColor(Color.BLACK);
        });
        expebtn.setOnClickListener(view -> {
            replaceFragment(new ExperienceFragment());
            expebtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            expebtn.setTextColor(Color.WHITE);
            educationbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            educationbtn.setTextColor(Color.BLACK);
            achievebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            achievebtn.setTextColor(Color.BLACK);
            overviewbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            overviewbtn.setTextColor(Color.BLACK);

        });
        achievebtn.setOnClickListener(view -> {
            replaceFragment(new AchievementFragment());
            achievebtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            achievebtn.setTextColor(Color.WHITE);

            expebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            expebtn.setTextColor(Color.BLACK);
            educationbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            educationbtn.setTextColor(Color.BLACK);
            overviewbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            overviewbtn.setTextColor(Color.BLACK);
        });
        educationbtn.setOnClickListener(view -> {
            replaceFragment(new EducationFragment());
            educationbtn.setBackgroundColor(getResources().getColor(R.color.purple_700));
            educationbtn.setTextColor(Color.WHITE);

            achievebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            achievebtn.setTextColor(Color.BLACK);
            expebtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            expebtn.setTextColor(Color.BLACK);
            overviewbtn.setBackground(getResources().getDrawable(R.drawable.btn_profile));
            overviewbtn.setTextColor(Color.BLACK);
        });
        getEmployeeData();
        sessionManager = new UserSessionManager(this);
        nametxt.setText(sessionManager.getFullName());

    }

    public void getEmployeeData() {
        sessionManager = new UserSessionManager(ProfileActivity.this);
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(),"sid="+ sessionManager.getUserId()).enqueue(new Callback<EmployeeDataResponse>() {
            @Override
            public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                if (response.isSuccessful()) {
                    EmployeeDataResponse responseModel = response.body();
                    if (responseModel != null && responseModel.getData() != null) {
                        EmployeeDataResponse.Data data = responseModel.getData();
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
            public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
    private boolean isDarkThemePreferred() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themePreference = sharedPreferences.getString("theme_preference", "system");

        if (themePreference.equals("dark")) {
            return true;
        } else if (themePreference.equals("light")) {
            return false;
        } else {
            // If the theme preference is set to "system", use the system default
            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
        }
    }
}