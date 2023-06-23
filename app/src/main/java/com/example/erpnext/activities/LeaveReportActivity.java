package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.LeaveReportAdapter;
import com.example.erpnext.models.LeaveAllocation;
import com.example.erpnext.models.LeaveApplicationReport;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveReportActivity extends AppCompatActivity {
    AppCompatButton button;

    UserSessionManager sessionManager;
    Toolbar toolbar;
    View failureView;
    ProgressBar progressBar;

    TextView total_leave_days_txt, leave_days_left, leave_days_used;
    LinearLayout leavereportlayout;
    RecyclerView recyclerView;
    LeaveReportAdapter adapter;
    List<LeaveApplicationReport.Datum> datumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_report);
        toolbar = findViewById(R.id.leave_report_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Leave Report");
        sessionManager = new UserSessionManager(this);

        datumList = new ArrayList<>();
        recyclerView = findViewById(R.id.recentleaverecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LeaveReportAdapter(datumList, this);
        recyclerView.setAdapter(adapter);

        leave_days_left = findViewById(R.id.leave_days_left);
        leave_days_used = findViewById(R.id.leave_days_used);
        total_leave_days_txt = findViewById(R.id.total_leave_days);
        leavereportlayout = findViewById(R.id.nothingfoundleavereportlayout);
        progressBar = findViewById(R.id.progressBarLeaveReport);
        button = findViewById(R.id.applyleavebtn);

        button.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            ApiClient.getApiClient().getLeaveTypesForLeaveReport("Leave Allocation", "sid="+sessionManager.getUserId()).enqueue(new Callback<LeaveAllocation>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<LeaveAllocation> call, Response<LeaveAllocation> response) {
                    if (response.isSuccessful()) {
                        LeaveAllocation leaveAllocation = response.body();
                        if (leaveAllocation != null && !leaveAllocation.getData().isEmpty()) {
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(LeaveReportActivity.this, ApplyLeaveActivity.class));
                        }else {
                            failureView = getLayoutInflater().inflate(R.layout.failure, null);
                            TextView message = failureView.findViewById(R.id.message);
                            AppCompatButton button = failureView.findViewById(R.id.closebtn);
                            message.setText("You Have not been allocated any leave yet, Please contact your HR manager");
                            AlertDialog.Builder builder = new AlertDialog.Builder(LeaveReportActivity.this);
                            builder.setView(failureView);
                            builder.setCancelable(false);
                            AlertDialog dialog = builder.create();

                            button.setOnClickListener(view1 -> dialog.dismiss());
                            progressBar.setVisibility(View.GONE);
                            dialog.show();
                        }

                       // progressBar.setVisibility(View.GONE);
                    } else {
                        //error body
                        if (response.errorBody() != null) {
                            try {
                                String errorResponseJson = response.errorBody().string();
                                PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                AlertDialog.Builder builder = new AlertDialog.Builder(LeaveReportActivity.this);
                                builder.setTitle(errorResponse.getExcType());
                                String exceptionMessage = errorResponse.getException();
                                int firstmaessage = exceptionMessage.indexOf(":");
                                //int lastmessage = exceptionMessage.lastIndexOf(":");
                                String errorMessage = exceptionMessage.substring(firstmaessage+1).trim();
                                builder.setMessage(errorMessage);
                                builder.setCancelable(false);
                                builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());


                                // Set a positive button and its click listener


                                // Create and show the alert dialog
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LeaveReportActivity.this, "Response not successfull", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LeaveAllocation> call, Throwable t) {

                    Toast.makeText(LeaveReportActivity.this, "Leave Report "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        });
        String fields = "[\"leave_type\", \"from_date\", \"to_date\",\"leave_balance\", \"total_leave_days\", \"posting_date\",\"status\"]";
        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getAppliedLeavesReport("Leave Application", "sid="+sessionManager.getUserId(), fields)
                .enqueue(new Callback<LeaveApplicationReport>() {
                    @Override
                    public void onResponse(Call<LeaveApplicationReport> call, Response<LeaveApplicationReport> response) {
                        if (response.isSuccessful())
                        {
                            LeaveApplicationReport leaveApplicationReport = response.body();


                            if (leaveApplicationReport != null && leaveApplicationReport.getData()!= null && !leaveApplicationReport.getData().isEmpty()){
                                List<LeaveApplicationReport.Datum> datumList1 = leaveApplicationReport.getData();
                                if (datumList1 != null) {
                                    double totalLeaveDays = 0.0;
                                    double leavedaysused = 0.0;
                                    datumList.addAll(datumList1);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    for (LeaveApplicationReport.Datum leaveApplicationReport1:datumList1){
                                        if (leaveApplicationReport1.getLeave_balance() != null){
                                            totalLeaveDays += leaveApplicationReport1.getLeave_balance();
                                            if (leaveApplicationReport1.getTotalLeaveDays() != null){
                                                leavedaysused += leaveApplicationReport1.getTotalLeaveDays();
                                            }
                                            //leaveBalance = 0.0;
                                        }
                                        else {
                                            totalLeaveDays = 0.0;
                                        }
                                    }

                                    int TotalLeaveDays = (int) totalLeaveDays;
                                    int leaveBalanceInt = (int) leavedaysused;
                                    int leaveDaysUsed = (int) (TotalLeaveDays - leavedaysused);

                                    String total_leave_days = String.valueOf(TotalLeaveDays);
                                    String total_leave_balance = String.valueOf(leaveBalanceInt);
                                    String total_leave_used = String.valueOf(leaveDaysUsed);

                                    total_leave_days_txt.setText(total_leave_days);
                                    leave_days_left.setText(total_leave_balance);
                                    leave_days_used.setText(total_leave_used);


                                }
                                else {
                                    total_leave_days_txt.setText("0");
                                    Toast.makeText(LeaveReportActivity.this, "data list is null", Toast.LENGTH_SHORT).show();
                                }
                                }



                            else {
                                leavereportlayout.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        else {
                            if (response.errorBody() != null) {
                                try {
                                    String errorResponseJson = response.errorBody().string();
                                    PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(LeaveReportActivity.this);
                                    builder.setTitle(errorResponse.getExcType());
                                    String exceptionMessage = errorResponse.getException();
                                    int firstmaessage = exceptionMessage.indexOf(":");
                                    //int lastmessage = exceptionMessage.lastIndexOf(":");
                                    String errorMessage = exceptionMessage.substring(firstmaessage+1).trim();
                                    builder.setMessage(errorMessage);
                                    builder.setCancelable(false);
                                    builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());


                                    // Set a positive button and its click listener


                                    // Create and show the alert dialog
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LeaveReportActivity.this, "Response not successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LeaveApplicationReport> call, Throwable t) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(LeaveReportActivity.this);
                                builder.setTitle("Error Occurred");
                                String exceptionMessage = t.getMessage();


                                builder.setMessage(exceptionMessage);
                                builder.setCancelable(false);
                                builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());


                                // Set a positive button and its click listener


                                // Create and show the alert dialog
                                AlertDialog dialog = builder.create();
                                dialog.show();


                        Toast.makeText(LeaveReportActivity.this, "Error occurred"+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}