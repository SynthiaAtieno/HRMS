package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.CustomAdapter;
import com.example.erpnext.R;
import com.example.erpnext.adapters.SalarySlipAdapter;
import com.example.erpnext.adapters.SalarySlipDeductionAdapter;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.SalarySlipData;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipActivity2 extends AppCompatActivity {
    Spinner spinnerFrom, spinnerTo, spinnerYear;
    AppCompatButton generateAll;
    TextView txtfirstmonth, txtsecondmonth;
    LinearLayout linearLayoutforfirstmonth;
    TextView workingdayforfisrtmonth, grosspayearning, grosspaydeductions, netpay;
    UserSessionManager sessionManager;
    SalarySlipAdapter adapter;
    SalarySlipDeductionAdapter deductionAdapter;
    RecyclerView recyclerView, recyclerdeduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        Toolbar toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");
        grosspayearning = findViewById(R.id.grossearnings);

        recyclerView = findViewById(R.id.salarysliprecycler);
        linearLayoutforfirstmonth = findViewById(R.id.linearlayoutforfirstmonth);
        sessionManager = new UserSessionManager(this);
        String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
        workingdayforfisrtmonth = findViewById(R.id.working_days);
        spinnerFrom = findViewById(R.id.spinner_item_textfrom);
        spinnerTo = findViewById(R.id.spinner_item_textto);
        spinnerYear = findViewById(R.id.spinner_item_year);
        generateAll = findViewById(R.id.generate_all);
        //frame1 = findViewById(R.id.framemonthpayslip);
        //  frame2 = findViewById(R.id.framesecondmonthpayslip);
        txtfirstmonth = findViewById(R.id.monthtxt);
        txtsecondmonth = findViewById(R.id.secondmonthtxt);
        grosspaydeductions = findViewById(R.id.grossdeductions);
        grosspayearning = findViewById(R.id.grossearnings);
        netpay = findViewById(R.id.netPay);

        recyclerdeduction = findViewById(R.id.recyclerviewdeductions);
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));

        generateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutforfirstmonth.setVisibility(View.VISIBLE);
//                frame1.setVisibility(View.VISIBLE);
//                frame2.setVisibility(View.VISIBLE);
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                txtfirstmonth.setText(adapterView.getItemAtPosition(position).toString());
                String month = adapterView.getItemAtPosition(position).toString();
                if (month.equals("January")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "000001", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    generateAll.setVisibility(View.VISIBLE);
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();

                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                                    generateAll.setVisibility(View.GONE);
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
                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    //workingdayforfisrtmonth.setText("0");
                } else if (month.equals("February")) {
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
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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
                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    //workingdayforfisrtmonth.setText("0");
                } else if (month.equals("March")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "000003", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                                    generateAll.setVisibility(View.GONE);
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

                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    //workingdayforfisrtmonth.setText("0");
                } else if (month.equals("April")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "000004", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();

                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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
                    // Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    // workingdayforfisrtmonth.setText("0");
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
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00002", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                                    generateAll.setVisibility(View.GONE);
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
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00003", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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
                    //workingdayforfisrtmonth.setText("0");
                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                } else if (month.equals("August")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00004", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();

                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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
                    //workingdayforfisrtmonth.setText("0");
                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("September")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00005", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText("0");
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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

                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("October")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00006", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText(0);
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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

                    //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

                } else if (month.equals("December")) {
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00007", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {

                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    workingdayforfisrtmonth.setText(Integer.toString(working));
                                    grosspayearning.setText(kenyanCurrencyFormat.format(data.getGrossPay()));
                                    grosspaydeductions.setText(kenyanCurrencyFormat.format(data.getTotalDeduction()));
                                    netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                                    List<SalarySlipData.Earning> earnings = new ArrayList<>();//data.getEarnings();
                                    recyclerView.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
                                    recyclerView.setAdapter(adapter);
                                    earnings.addAll(data.getEarnings());
                                    adapter.notifyDataSetChanged();
                                    generateAll.setVisibility(View.VISIBLE);
                                    recyclerdeduction.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
                                    List<SalarySlipData.Deduction> deductions = new ArrayList<>();
                                    deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
                                    recyclerdeduction.setAdapter(deductionAdapter);
                                    deductions.addAll(data.getDeductions());
                                    deductionAdapter.notifyDataSetChanged();

                                } else {
                                    generateAll.setVisibility(View.GONE);
                                    workingdayforfisrtmonth.setText(0);
                                    linearLayoutforfirstmonth.setVisibility(View.GONE);
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

                    // Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();

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
                    //   workingdaysforsecondmonth.setText("0");
                } else if (month.equals("February")) {

                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    //  workingdaysforsecondmonth.setText("0");
                } else if (month.equals("March")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    //workingdaysforsecondmonth.setText("0");
                } else if (month.equals("April")) {
                    Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    // workingdaysforsecondmonth.setText("0");
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
                                    //workingdaysforsecondmonth.setText(Integer.toString(working));
                                    SalarySlipData.Earning earnings = data.getEarnings().get(0);

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
                    ApiClient.getApiClient().getSlipData("Salary Slip", name + "00002", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
                        @Override
                        public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                            if (response.isSuccessful()) {
                                SalarySlipData salarySlipData = response.body();
                                if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                                    SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                                    double workingdays = data.getTotalWorkingDays();
                                    int working = (int) workingdays;
                                    //workingdaysforsecondmonth.setText(Integer.toString(working));
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