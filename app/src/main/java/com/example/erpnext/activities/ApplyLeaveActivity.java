package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.models.LeaveApplicationData;
import com.example.erpnext.R;
import com.example.erpnext.models.LeaveAllocation;
import com.example.erpnext.models.LeaveApplication;
import com.example.erpnext.models.LeaveType;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyLeaveActivity extends AppCompatActivity {
    Spinner leaveTypeSpinner;
    UserSessionManager sessionManager;
    Calendar calendar;
    TextView from, to;
    AppCompatButton submitleave;
    TextInputEditText reasonforleaveapplication;
    private ProgressBar progressBar;
    View successView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
        Toolbar toolbar = findViewById(R.id.apply_leave_toolbar);
        setSupportActionBar(toolbar);
        calendar = Calendar.getInstance();
        from = findViewById(R.id.fromdate);
        to = findViewById(R.id.todate);
        reasonforleaveapplication = findViewById(R.id.reasonforleaveapplication);
        sessionManager = new UserSessionManager(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Apply Leave");
        submitleave = findViewById(R.id.submitleaveapplication);
        leaveTypeSpinner = findViewById(R.id.leave_type_spinner);
        progressBar = findViewById(R.id.progrssbarforleaveapplication);

        leaveTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(ApplyLeaveActivity.this, "" + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String spinnerIte = leaveTypeSpinner.getSelectedItem().toString();
                String todate = to.getText().toString();
                String fromtxt = from.getText().toString();
                String reasonforleaveApplication = reasonforleaveapplication.getText().toString();
                if (todate.equals("") && fromtxt.equals("") && reasonforleaveApplication.equals("")){
                    Toast.makeText(ApplyLeaveActivity.this, "Please fill all the fields withe the appropriate data", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
                else {
                    LeaveApplicationData leaveApplicationData = new LeaveApplicationData();
                    leaveApplicationData.setEmployeeId(sessionManager.getKeyEmployeeNamingSeries());
                    leaveApplicationData.setStartDate(fromtxt);
                    leaveApplicationData.setEndDate(todate);
                    leaveApplicationData.setLeaveType(spinnerIte);
                    leaveApplicationData.setReasonForApplication(reasonforleaveapplication.getText().toString());
                    leaveApplicationData.setLeaveApprover(sessionManager.getLeaveApprover());
                    ApiClient.getApiClient().ApplyLeave(leaveApplicationData, "Leave Application", "sid=" + sessionManager.getUserId()).enqueue(new Callback<LeaveApplication>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onResponse(Call<LeaveApplication> call, Response<LeaveApplication> response) {
                            if (response.code() == 200) {

                                // Toast.makeText(ApplyLeaveActivity.this, "hey 200", Toast.LENGTH_SHORT).show();
                                LeaveApplication responseModel = response.body();
                                if (responseModel != null) {
                                    successView = getLayoutInflater().inflate(R.layout.success, null);
                                    TextView message = successView.findViewById(R.id.message);
                                    AppCompatButton button = successView.findViewById(R.id.closebtn);
                                    message.setText("Leave Application successful");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                    builder.setView(successView);
                                    AlertDialog dialog = builder.create();

                                    button.setOnClickListener(view1 -> dialog.dismiss());

                                    dialog.show();
                                    //System.out.println("responseModel.getData() = " + responseModel.getData());
                                   // System.out.println("responseModel = " + responseModel);
                                }
                                if (responseModel.getData() != null) {
                                    System.out.println("responseModel.getData().getLeaveBalance() = " + responseModel.getData().getLeaveBalance());
                                } else {
                                    System.out.println("null data = ");
                                }

                                progressBar.setVisibility(View.GONE);
                            } else {
                                //Toast.makeText(ApplyLeaveActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                                if (response.errorBody() != null) {
                                    progressBar.setVisibility(View.GONE);
                                    try {
                                        String errorResponseJson = response.errorBody().string();
                                        PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                        builder.setTitle(errorResponse.getExcType());
                                        String exceptionMessage = errorResponse.getException();
                                        int firstmaessage = exceptionMessage.indexOf(":");
                                        //int lastmessage = exceptionMessage.lastIndexOf(":");
                                        String errorMessage = exceptionMessage.substring(firstmaessage+1).trim();
                                        builder.setMessage(errorMessage);
                                        builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());


                                        // Set a positive button and its click listener


                                        // Create and show the alert dialog
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                        //progressBar.setVisibility(View.GONE);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        // progressBar.setVisibility(View.GONE);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LeaveApplication> call, Throwable t) {
                            Toast.makeText(ApplyLeaveActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
                }
        });
        from.setOnClickListener(view -> showDatePickerDialog(from));


        to.setOnClickListener(view -> showDatePickerDialog(to));
        progressBar.setVisibility(View.VISIBLE);
        String fields = "[\"leave_type\", \"name\", \"total_leaves_allocated\"]";
        ApiClient.getApiClient().getLeaveTypes("Leave Allocation", "sid="+sessionManager.getUserId(), fields).enqueue(new Callback<LeaveAllocation>() {
            @Override
            public void onResponse(Call<LeaveAllocation> call, Response<LeaveAllocation> response) {
                if (response.isSuccessful()) {
                    LeaveAllocation leaveAllocation = response.body();
                    if (leaveAllocation != null && !leaveAllocation.getData().isEmpty()) {
                        List<LeaveAllocation.Datum> datum = leaveAllocation.getData();
                        List<String> leaveTypes = new ArrayList<>();
                        for (LeaveAllocation.Datum datum1 : datum) {
                            leaveTypes.add(datum1.getLeaveType());
                        }
                        ArrayAdapter<LeaveType.Datum> adapter = new ArrayAdapter(ApplyLeaveActivity.this,
                                android.R.layout.simple_spinner_item, leaveTypes);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Set the adapter to the spinner
                        leaveTypeSpinner.setAdapter(adapter);
                    }
                    progressBar.setVisibility(View.GONE);
                } else {
                    //error body
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
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
                    Toast.makeText(ApplyLeaveActivity.this, "Response not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeaveAllocation> call, Throwable t) {

            }
        });
    }

    private void showDatePickerDialog(TextView textView) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextView(textView);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        //datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        Calendar maxDateCalender = Calendar.getInstance();
        maxDateCalender.add(Calendar.YEAR, 1);
        datePickerDialog.getDatePicker().setMaxDate(maxDateCalender.getTimeInMillis());

        datePickerDialog.show();
    }

    private void updateTextView(TextView textView) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        textView.setText(formattedDate);
    }
}