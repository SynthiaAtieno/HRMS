package com.example.savannahrms.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savannahrms.Greetings;
import com.example.savannahrms.activities.LeaveReportActivity;
import com.example.savannahrms.activities.Login;
import com.example.savannahrms.activities.MainActivity;
import com.example.savannahrms.activities.PaySlipDetailsActivity;
import com.example.savannahrms.R;
import com.example.savannahrms.activities.drawerActivities.HolidayActivity;
import com.example.savannahrms.models.EmployeeDataResponse;
import com.example.savannahrms.models.PermissionError;
import com.example.savannahrms.services.ApiClient;
import com.example.savannahrms.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    UserSessionManager sessionManager;
    TextView greting, first_name, txviewslip, viewallholidays;
    View customView;

    LinearLayout payslip, claims, leave, attendance;

    public HomeFragment() {
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
        //Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), isDarkThemePreferred() ? R.style.AppTheme_Dark : R.style.AppTheme_Light);
       // LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String greeting = Greetings.getGreeting();
        first_name = view.findViewById(R.id.firstname);
        greting = view.findViewById(R.id.greetings);
        greting.setText(greeting);
        payslip = view.findViewById(R.id.payslipview);
        leave = view.findViewById(R.id.leaveview);
        viewallholidays = view.findViewById(R.id.viewallholidays);
        txviewslip = view.findViewById(R.id.textViewslip);


        leave.setOnClickListener(view1 -> startActivity(new Intent(requireContext(), LeaveReportActivity.class)));

        viewallholidays.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), HolidayActivity.class));
            }
        });

        sessionManager = new UserSessionManager(requireContext());
        if (sessionManager.getUserFirstName() == null) {
            System.out.println(" First name is null ");
            // Toast.makeText(getContext(), "First Name is null", Toast.LENGTH_SHORT).show();
        } else {
            first_name.setText(sessionManager.getFullName().toUpperCase());
        }

        payslip.setOnClickListener(v ->
                startActivity(new Intent(getContext(), PaySlipDetailsActivity.class)));
        return view;
    }

    private int getLayoutResId() {
        return R.id.homefragment;
    }

   /* private boolean isDarkThemePreferred() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
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
    }*/


    public void getEmployeeData() {
        sessionManager = new UserSessionManager(requireContext());
        ApiClient.getApiClient().getEmployeeData(sessionManager.getKeyEmployeeNamingSeries(),"sid="+ sessionManager.getUserId()).enqueue(new Callback<EmployeeDataResponse>() {
            @Override
            public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                if (response.isSuccessful()) {
                    EmployeeDataResponse responseModel = response.body();
                    if (responseModel != null && responseModel.getData() != null) {
                        EmployeeDataResponse.Data data = responseModel.getData();
                        sessionManager.setUserFirstName(data.getFirstName().toUpperCase());
                        sessionManager.setUserLeaveApprover(data.getLeaveApprover());

                    } else {
                        Toast.makeText(requireContext(), "Null data", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setTitle(errorResponse.getExcType());
                            String exceptionMessage = errorResponse.getException();
                            int firstmaessage = exceptionMessage.indexOf(":");
                            int lastmessage = exceptionMessage.lastIndexOf(":");
                            if (exceptionMessage.equals("frappe.exceptions.PermissionError")){
                                /*ApiClient.getApiClient().logout().enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()){
                                            builder.setMessage("Your Session expired, please login to access your account");
                                            builder.setCancelable(false);
                                            builder.setPositiveButton("Ok", (dialog, which) -> {
                                                dialog.dismiss();
                                                startActivity(new Intent(requireContext(), Login.class));
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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

                                    }
                                });*/
                                builder.setMessage("Your session expired, please login to access your account");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Login", (dialog, which) -> {
                                    dialog.dismiss();
                                    sessionManager.clearSession();
                                    startActivity(new Intent(requireContext(), Login.class));
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }
                            else {
                                String errorMessage = exceptionMessage.substring(firstmaessage+1, lastmessage).trim();
                                builder.setMessage(errorMessage);
                                builder.setCancelable(false);
                                builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());


                                // Set a positive button and its click listener


                                // Create and show the alert dialog
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                System.out.println("t.getMessage() = " + t.getMessage());
                Toast.makeText(requireContext(), "Error occurred in home fragment" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}