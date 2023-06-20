package com.example.erpnext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

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

import com.example.erpnext.models.LeaveApplication;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

                submitleave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                        String contentType = "application/json";
                        String accept = "application/json";
                        Map<String, String> parameters = new HashMap<>();
                        String employee = sessionManager.getKeyEmployeeNamingSeries();
                        String leaveApplicationReason = reasonforleaveapplication.getText().toString();
                        String sid = sessionManager.getUserId();
                        String todate = to.getText().toString();
                        String fromtxt = from.getText().toString();
                        parameters.put("reason", leaveApplicationReason);
                        parameters.put("to_date", todate);
                        parameters.put("from_date", fromtxt);
                        parameters.put("leave_type", adapterView.getItemAtPosition(position).toString());
                        parameters.put("employee", employee);
                        // parameters.put("sid", sid);
                      //  Toast.makeText(ApplyLeaveActivity.this, "" + parameters, Toast.LENGTH_LONG).show();

                        ApiClient.getApiClient().ApplyLeave(parameters, "Leave Application", sessionManager.getUserId(), contentType, accept).enqueue(new Callback<LeaveApplication>() {
                            @Override
                            public void onResponse(Call<LeaveApplication> call, Response<LeaveApplication> response) {
                                if (response.isSuccessful()) {

                                    Toast.makeText(ApplyLeaveActivity.this, "Response successful", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                                else {
                                    if (response.errorBody() != null) {
                                        progressBar.setVisibility(View.GONE);
                                        try {
                                            String errorResponseJson = response.errorBody().string();
                                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(ApplyLeaveActivity.this);
                                            builder.setTitle("Error Occurred");
                                            builder.setMessage(errorResponseJson);

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
                                }
                            }

                            @Override
                            public void onFailure(Call<LeaveApplication> call, Throwable t) {
                                Toast.makeText(ApplyLeaveActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        from.setOnClickListener(view -> showDatePickerDialog(from));


        to.setOnClickListener(view -> showDatePickerDialog(to));
        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getLeaveTypes("Leave Type", sessionManager.getUserId()).enqueue(new Callback<LeaveType>() {
            @Override
            public void onResponse(Call<LeaveType> call, Response<LeaveType> response) {
                if (response.isSuccessful()) {
                    LeaveType leaveType = response.body();
                    if (leaveType != null && !leaveType.getData().isEmpty()) {
                        List<LeaveType.Datum> datum = leaveType.getData();
                        List<String> leaveTypes = new ArrayList<>();
                        for (LeaveType.Datum datum1 : datum) {
                            leaveTypes.add(datum1.getName());
                        }
                        ArrayAdapter<LeaveType.Datum> adapter = new ArrayAdapter(ApplyLeaveActivity.this,
                                android.R.layout.simple_spinner_item, leaveTypes);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Set the adapter to the spinner
                        leaveTypeSpinner.setAdapter(adapter);
                    }
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ApplyLeaveActivity.this, "Response not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LeaveType> call, Throwable t) {

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

        datePickerDialog.show();
    }

    private void updateTextView(TextView textView) {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        String formattedDate = simpleDateFormat.format(calendar.getTime());
        textView.setText(formattedDate);
    }
}