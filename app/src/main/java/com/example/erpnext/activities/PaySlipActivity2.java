package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.CustomAdapter;
import com.example.erpnext.R;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.SalarySlipData;
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

public class PaySlipActivity2 extends AppCompatActivity {
    Toolbar toolbar;
    Spinner spinnerFrom, spinnerTo, spinnerYear;
    AppCompatButton generateAll;
    FrameLayout frame1, frame2;
    TextView txtfirstmonth, txtsecondmonth;
    TextView baseearning, houseallowance, transportallowance, workingdayforfisrtmonth, workingdaysforsecondmonth;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");
        sessionManager = new UserSessionManager(this);
        String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
        workingdayforfisrtmonth = findViewById(R.id.working_days);
        spinnerFrom = findViewById(R.id.spinner_item_textfrom);
        spinnerTo = findViewById(R.id.spinner_item_textto);
        spinnerYear = findViewById(R.id.spinner_item_year);
        generateAll = findViewById(R.id.generate_all);
        frame1 = findViewById(R.id.framemonthpayslip);
        frame2 = findViewById(R.id.framesecondmonthpayslip);
        txtfirstmonth = findViewById(R.id.monthtxt);
        txtsecondmonth = findViewById(R.id.secondmonthtxt);
        workingdaysforsecondmonth = findViewById(R.id.working_daysecondmonth);

        generateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame1.setVisibility(View.VISIBLE);
                frame2.setVisibility(View.VISIBLE);
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                txtfirstmonth.setText(adapterView.getItemAtPosition(position).toString());
                String month = adapterView.getItemAtPosition(position).toString();
                if (month.equals("January")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdayforfisrtmonth.setText("0");
                } else if (month.equals("February")) {

                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdayforfisrtmonth.setText("0");
                } else if (month.equals("March")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdayforfisrtmonth.setText("0");
                } else if (month.equals("April")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdayforfisrtmonth.setText("0");
                } else if (month.equals("May")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00001", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                } else {
                                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        String errorResponseJson = response.errorBody().string();
                                        PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
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
                            }
                        }

                        @Override
                        public void onFailure(Call<SalarySlipData> call, Throwable t) {
                            Toast.makeText(PaySlipActivity2.this, "An error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (month.equals("June")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "000002", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                } else {
                                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        String errorResponseJson = response.errorBody().string();
                                        PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
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
                            }
                        }

                        @Override
                        public void onFailure(Call<SalarySlipData> call, Throwable t) {

                        }
                    });

                } else if (month.equals("July")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                } else if (month.equals("August")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("September")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("October")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("December")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PaySlipActivity2.this, "Please select a month", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                txtsecondmonth.setText(adapterView.getItemAtPosition(position).toString());
                String month = adapterView.getItemAtPosition(position).toString();
                if (month.equals("January")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdaysforsecondmonth.setText("0");
                } else if (month.equals("February")) {

                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdaysforsecondmonth.setText("0");
                } else if (month.equals("March")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdaysforsecondmonth.setText("0");
                } else if (month.equals("April")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    workingdaysforsecondmonth.setText("0");
                } else if (month.equals("May")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00001", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdaysforsecondmonth.setText(Integer.toString(working));
                                } else {
                                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        String errorResponseJson = response.errorBody().string();
                                        PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
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
                            }
                        }

                        @Override
                        public void onFailure(Call<SalarySlipData> call, Throwable t) {
                            Toast.makeText(PaySlipActivity2.this, "An error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (month.equals("June")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "000002", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdaysforsecondmonth.setText(Integer.toString(working));
                                } else {
                                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (response.errorBody() != null) {
                                    try {
                                        String errorResponseJson = response.errorBody().string();
                                        PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                        AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
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
                            }
                        }

                        @Override
                        public void onFailure(Call<SalarySlipData> call, Throwable t) {
                            Toast.makeText(PaySlipActivity2.this, "An error occurred", Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (month.equals("July")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                } else if (month.equals("August")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("September")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("October")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("December")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(PaySlipActivity2.this, "Please select a month", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}