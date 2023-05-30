package com.example.erpnext.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.activities.drawerActivities.AppointmentsActivity;
import com.example.erpnext.activities.drawerActivities.BenefitsActivity;
import com.example.erpnext.activities.drawerActivities.HolidayActivity;
import com.example.erpnext.activities.drawerActivities.ProfileActivity;
import com.example.erpnext.activities.drawerActivities.RaiseIssueActivity;
import com.example.erpnext.activities.drawerActivities.SettingsActivity;
import com.example.erpnext.activities.drawerActivities.TaskInformationActivity;
import com.example.erpnext.activities.drawerActivities.TrainingActivity;
import com.example.erpnext.fragments.AttendanceFragment;
import com.example.erpnext.fragments.HomeFragment;
import com.example.erpnext.fragments.LeaveFragment;
import com.example.erpnext.fragments.MarkAttendanceFragment;
import com.example.erpnext.fragments.ProfileFragment;
import com.example.erpnext.models.EmployeesData;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.UserError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    UserSessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        replaceFragment(new HomeFragment());
        sessionManager = new UserSessionManager(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getEmployeeData();
        navigationView.setNavigationItemSelectedListener(this);
        System.out.println("sid"+sessionManager.getUserId());

        View headerView = navigationView.getHeaderView(0);
        TextView nametxt = headerView.findViewById(R.id.header_name_txt);
        TextView roletxt = headerView.findViewById(R.id.header_role_txt);

        nametxt.setText(sessionManager.getFullName());
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    return true;

                case R.id.attendance:
                    replaceFragment(new AttendanceFragment());
                    return true;

                case R.id.leave:
                    replaceFragment(new LeaveFragment());
                    return true;

                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    return true;

                case R.id.mark_attendance:
                    replaceFragment(new MarkAttendanceFragment());
                    return true;
            }
            return true;
        });


    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                break;
            case R.id.drawer_appointments:
                startActivity(new Intent(MainActivity.this, AppointmentsActivity.class));
                break;
            case R.id.drawer_benefits:
                startActivity(new Intent(MainActivity.this, BenefitsActivity.class));

                break;
            case R.id.drawer_holidays:
                startActivity(new Intent(MainActivity.this, HolidayActivity.class));

                break;
            case R.id.raise_issue:
                startActivity(new Intent(MainActivity.this, RaiseIssueActivity.class));

                break;

            case R.id.settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));

                break;
            case R.id.drawer_training:
                startActivity(new Intent(MainActivity.this, TrainingActivity.class));

                break;

            case R.id.drawer_tax:
                startActivity(new Intent(MainActivity.this, TaskInformationActivity.class));

                break;
            case R.id.drawer_logout:
                logout();
        }

        return true;
    }

    public void logout() {

        ApiClient.getApiClient().logout().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Alert");
                    builder.setMessage("Do you want to Quit?");

                    // Set a positive button and its click listener
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Perform any action you want when the "Yes" button is clicked
                            Toast.makeText(MainActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, Login.class));
                            finish();
                            sessionManager.clearSession();

                        }
                    });
                    // Set a negative button and its click listener
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Perform any action you want when the "No" button is clicked
                            dialog.dismiss();
                        }
                    });

                    // Create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Error Occurred");
                if (t.getMessage().equals("timeout")) {
                    builder.setMessage("Kindly check your internet connection then try again");

                    // Set a positive button and its click listener
                    builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                } else {
                    builder.setMessage(t.getMessage());

                    // Set a positive button and its click listener
                    builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                }
                // Create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                builder.setTitle("Error occurred");
            }
        });
    }

    public void getEmployeeData() {
        //String Auth_Token = "a838616307c06c351b63";
        ApiClient.getApiClient().getEmployeeData("Employee", sessionManager.getKeyEmployeeNamingSeries(), sessionManager.getUserId()).enqueue(new Callback<EmployeesData>() {
            @Override
            public void onResponse(Call<EmployeesData> call, Response<EmployeesData> response) {
                System.out.println(sessionManager.getKeyEmployeeNamingSeries());
                if (response.isSuccessful()) {
                    EmployeesData responseModel = response.body();
                    if (responseModel != null && responseModel.getDocs() != null && !responseModel.getDocs().isEmpty()) {
                        EmployeesData.EmployeeDoc data = responseModel.getDocs().get(0);

                        // Access the designation from the data model
                        String designation = data.getDesignation();
                        Toast.makeText(MainActivity.this, "Designation is" + designation, Toast.LENGTH_SHORT).show();
                        // Set the designation in a TextView
                        //textView.setText(designation);
                    } else {
                        Toast.makeText(MainActivity.this, "Null data", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                Toast.makeText(MainActivity.this, "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("Onfailure" + t.getMessage());
            }
        });
    }
}