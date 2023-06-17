package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipActivity2 extends AppCompatActivity {

    ProgressBar progressBar;
    PieChart pieChart;
    Spinner spinnerFrom, spinnerYear;
    AppCompatButton generateAll;
    CardView piechartcardview;
    TextView txtfirstmonth, txtsecondmonth, errordesc;
    LinearLayout linearLayoutforfirstmonth, earningslayoutpayslip, deductionlayout, errorlayout;
    TextView workingdayforfisrtmonth, workingdaystomonth, grosspayearning, grosspaydeductions, netpay;
    CircleImageView errorimage;
    UserSessionManager sessionManager;
    LinearLayoutManager linearLayoutManager;
    SalarySlipAdapter adapter;
    SalarySlipDeductionAdapter deductionAdapter;
    RecyclerView recyclerView, recyclerdeduction, recyclerearning, recyclerdeductions;
    RelativeLayout workingdaysrelativeLayout, earningslayout, deductionslayout;
    View firstview;
    List<SalarySlipData.Deduction> deductions;
    List<SalarySlipData.Earning> earnings; //data.getEarnings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        Toolbar toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");
        earnings = new ArrayList<>();
        deductions = new ArrayList<>();
        //grosspayearning = findViewById(R.id.grossearnings);
        pieChart = findViewById(R.id.pieChart);
        recyclerearning = findViewById(R.id.recyclerearning);


        adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
        recyclerearning.setAdapter(adapter);

        earningslayoutpayslip = findViewById(R.id.errorforearningslayout);
        deductionlayout = findViewById(R.id.errorfordeductionlayout);
        errorlayout = findViewById(R.id.errorlayout);
        piechartcardview = findViewById(R.id.pichartlayout);

        recyclerdeductions = findViewById(R.id.recyclerdeductions);

        recyclerearning.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        recyclerdeductions.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);
        recyclerdeductions.setAdapter(deductionAdapter);
        progressBar = findViewById(R.id.progressbar);
        //firstview = findViewById(R.id.firstviewforpayslip);
       /* earningslayout = findViewById(R.id.earningslayout);
        deductionslayout = findViewById(R.id.deductionslayout);
        workingdaysrelativeLayout = findViewById(R.id.wrkingdaysheading);
        // recyclerView = findViewById(R.id.salarysliprecycler);
        linearLayoutforfirstmonth = findViewById(R.id.linearlayoutforfirstmonth);*/
        sessionManager = new UserSessionManager(this);
        String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
       /* workingdayforfisrtmonth = findViewById(R.id.working_days);
        spinnerFrom = findViewById(R.id.spinner_item_textfrom);
        //spinnerTo = findViewById(R.id.spinner_item_textto);
        spinnerYear = findViewById(R.id.spinner_item_year);
        generateAll = findViewById(R.id.generate_all);
        txtfirstmonth = findViewById(R.id.monthtxt);
        txtsecondmonth = findViewById(R.id.tomonthtxt);
        workingdaystomonth = findViewById(R.id.working_dayssecondmonth);
        grosspaydeductions = findViewById(R.id.grossdeductions);
        grosspayearning = findViewById(R.id.grossearnings);
        netpay = findViewById(R.id.netPay);*/
        getPieData();


        //recyclerdeduction = findViewById(R.id.recyclerviewdeductions);
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));

/*
        generateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutforfirstmonth.setVisibility(View.VISIBLE);
                workingdaysrelativeLayout.setVisibility(View.VISIBLE);
                generateAll.setVisibility(View.GONE);
            }
        });
*/

      /*  spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                txtfirstmonth.setText(adapterView.getItemAtPosition(position).toString());
                String month = adapterView.getItemAtPosition(position).toString();
                if (month.equals("Select a month") || spinnerYear.getSelectedItem().toString().equals("Year")) {
                    workingdaysrelativeLayout.setVisibility(View.GONE);
                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                    firstview.setVisibility(View.GONE);
                    generateAll.setVisibility(View.GONE);
                    Toast.makeText(PaySlipActivity2.this, "Please select a month and a year", Toast.LENGTH_SHORT).show();
                } else if (month.equals("January") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("February") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("March") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("April") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("May") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("June") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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

                } else if (month.equals("July") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else if (month.equals("August") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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

                } else if (month.equals("September") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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

                } else if (month.equals("October") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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

                } else if (month.equals("December") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                } else {
                    generateAll.setVisibility(View.GONE);
                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                    Toast.makeText(PaySlipActivity2.this, "There is no salary slip for the selected month and year", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                //txtfirstmonth.setText(spinnerFrom.getItemAtPosition(position).toString());
                String month = adapterView.getItemAtPosition(position).toString();
                if (month.equals("Year") || spinnerFrom.getSelectedItem().toString().equals("Select a month")) {
                    workingdaysrelativeLayout.setVisibility(View.GONE);
                    linearLayoutforfirstmonth.setVisibility(View.GONE);
                    firstview.setVisibility(View.GONE);
                    generateAll.setVisibility(View.GONE);
                    Toast.makeText(PaySlipActivity2.this, "Please select a month and a year", Toast.LENGTH_SHORT).show();
                } else {


                    if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("January")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("February")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("March")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("April")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("May")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("June")) {
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

                    } else if (month.equals("July") && spinnerYear.getSelectedItem().toString().equals("2023")) {
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
                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("August")) {
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

                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("September")) {
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

                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("October")) {
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

                    } else if (month.equals("2023") && spinnerFrom.getSelectedItem().toString().equals("December")) {
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
                    } else {
                        workingdayforfisrtmonth.setText("0");
                        generateAll.setVisibility(View.GONE);
                        linearLayoutforfirstmonth.setVisibility(View.GONE);
                        Toast.makeText(PaySlipActivity2.this, "There is no salary slip for the selected month and year", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

    }

    public void getPieData() {
        progressBar.setVisibility(View.VISIBLE);
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
        ApiClient.getApiClient().getSlipData("Salary Slip", name + "00001", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
            @Override
            public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                if (response.isSuccessful()) {
                    SalarySlipData salarySlipData = response.body();
                    if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                       // generateAll.setVisibility(View.VISIBLE);
                        SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                        double workingdays = data.getTotalWorkingDays();
                        int working = (int) workingdays;
                        List<PieEntry> entries = new ArrayList<>();

                       // workingdayforfisrtmonth.setText(Integer.toString(working));
                        //Double grossearning = kenyanCurrencyFormat.format(data.getGrossPay());
                        //grosspayearning.setText(grossearning);
                        // Double grossdeduction = kenyanCurrencyFormat.format(data.getTotalDeduction());
                        //grosspaydeductions.setText(grossdeduction);


                        int ded = data.getTotalDeduction().intValue();
                        System.out.println("ded = " + ded);
                        int earn = data.getGrossPay().intValue();

                        System.out.println("earn = " + earn);
                        entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                        entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));
                        float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)

                        PieDataSet dataSet = new PieDataSet(entries, ""); // Provide a label for the dataset
                        //dataSet.setColors(ColorTemplate.MATERIAL_COLORS); // Use predefined colors for the dataset
                        int desiredWidth = 500; // in pixels

// Adjust the width programmatically
                        ViewGroup.LayoutParams layoutParams = pieChart.getLayoutParams();
                        layoutParams.width = desiredWidth;
                        layoutParams.height = 500;

                        PieData pieData = new PieData(dataSet);
//
                        int orangeColor = 0xFFFFA500;
                        int purpleColor = 0xFF4169E1;
                        int[] colors = {orangeColor, purpleColor}; // Add as many colors as needed
                        dataSet.setColors(colors);
                        pieChart.notifyDataSetChanged();

//                        pieChart.setDrawEntryLabels(false);
//                        pieChart.setEntryLabelColor(Color.BLACK);
//                        pieChart.setEntryLabelTextSize(12f);
                        pieData.setDrawValues(false);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setLayoutParams(layoutParams);
                        pieChart.setHoleRadius(desiredHoleRadius);

                        pieChart.setCenterText(kenyanCurrencyFormat.format(data.getBaseRoundedTotal()) + "\n\n" + "Gross Pay");
                        pieChart.setDrawEntryLabels(false);
                        pieChart.setData(pieData);
                        pieChart.invalidate(); //chart
                        //netpay.setText(kenyanCurrencyFormat.format(data.getRoundedTotal()));
                        if (!data.getEarnings().isEmpty()) {
                            earnings.addAll(data.getEarnings());
                            progressBar.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        } else {
                            earningslayoutpayslip.setVisibility(View.GONE);
                            errordesc.setText("No Earnings to show");
                            errorimage.setImageResource(R.drawable.nothing_found);
                            errorlayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }

                        if (!data.getDeductions().isEmpty()) {
                            deductions.addAll(data.getDeductions());
                            //System.out.println("data.getDeductions() = " + data.getDeductions());
                            progressBar.setVisibility(View.GONE);
                            deductionAdapter.notifyDataSetChanged();
                        } else {
                            deductionslayout.setVisibility(View.GONE);
                            errordesc.setText("No Deductions to show");
                            errorimage.setImageResource(R.drawable.nothing_found);
                            errorlayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);

                        }

                    } else {
                        piechartcardview.setVisibility(View.GONE);
                        errordesc.setText("No Earnings to show");
                        errorimage.setImageResource(R.drawable.nothing_found);
                        errorlayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        /*workingdayforfisrtmonth.setText("0");
                        linearLayoutforfirstmonth.setVisibility(View.GONE);
                        generateAll.setVisibility(View.GONE);*/
                        Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pdf_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.downldpdf) {
            // Handle menu item click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}