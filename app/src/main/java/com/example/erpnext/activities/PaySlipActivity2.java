package com.example.erpnext.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.SalarySlipAdapter;
import com.example.erpnext.adapters.SalarySlipDeductionAdapter;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.SalarySlipData;
import com.example.erpnext.models.SlipNumber;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

    Spinner month, year;

    AppCompatButton generateAll;
    ProgressBar progressBar;
    PieChart pieChart;
    CardView piechartcardview;
    TextView errordesc, working_days, monthnametxt;
    LinearLayout earningslayoutpayslip, deductionlayout, errorlayout, payslipdetailslayout;
    ImageView errorimage;
    UserSessionManager sessionManager;
    SalarySlipAdapter adapter;
    SalarySlipDeductionAdapter deductionAdapter;
    RecyclerView recyclerearning, recyclerdeductions;
    RelativeLayout deductionslayout, workingdaysheading;
    List<SalarySlipData.Deduction> deductions;
    List<SalarySlipData.Earning> earnings; //data.getEarnings();
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isDarkThemePreferred()) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        Toolbar toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        errorimage = findViewById(R.id.errorimageforpayslip);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");
        errordesc = findViewById(R.id.errordescriptioforpayslip);
        earnings = new ArrayList<>();


        view = findViewById(R.id.firstviewforpayslip);
        payslipdetailslayout = findViewById(R.id.payslipdetailslayout);
        working_days = findViewById(R.id.working_days);
        generateAll = findViewById(R.id.generate_all);

        workingdaysheading = findViewById(R.id.workingdaysheading);
        month = findViewById(R.id.spinner_item_month);
        year = findViewById(R.id.spinner_item_year);
        deductions = new ArrayList<>();
        pieChart = findViewById(R.id.pieChart);
        recyclerearning = findViewById(R.id.recyclerearning);

        monthnametxt = findViewById(R.id.monthnametxt);

        adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
        recyclerearning.setAdapter(adapter);

        earningslayoutpayslip = findViewById(R.id.errorforearningslayout);
        deductionlayout = findViewById(R.id.errorfordeductionlayout);
        errorlayout = findViewById(R.id.errorlayout);
        piechartcardview = findViewById(R.id.pichartlayout);

        recyclerdeductions = findViewById(R.id.deductiondetails);
        deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);

        recyclerearning.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        recyclerdeductions.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        recyclerdeductions.setAdapter(deductionAdapter);
        progressBar = findViewById(R.id.progressbar);
        sessionManager = new UserSessionManager(this);
        //getSlipdata();
        // getPieData();
       /* generateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payslipdetailslayout.setVisibility(View.VISIBLE);
                generateAll.setVisibility(View.GONE);
//
            }
        });*/

        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
                String selectedMonth = adapterView.getItemAtPosition(position).toString();
                String selectedYear = year.getSelectedItem().toString();
                workingdaysheading.setVisibility(View.VISIBLE);
                monthnametxt.setText(selectedMonth);
                if (!selectedYear.equals("2023")) {
                    errordesc.setText("There is no payslip for the year " + selectedYear);
                    errorimage.setImageResource(R.drawable.nothing_found);
                    errorlayout.setVisibility(View.VISIBLE);
                    generateAll.setVisibility(View.GONE);
                    workingdaysheading.setVisibility(View.GONE);
                } else if (selectedMonth.equals("January")) {
                    String slipId = "00007";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);
                } else if (selectedMonth.equals("February")) {
                    String slipId = "000020";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("March")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("April")) {
                    String slipId = "00004";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("May")) {
                    String slipId = "00001";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("June")) {
                    String slipId = "00002";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("July")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("August")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("September")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("October")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("November")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("December")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else {
                    errordesc.setText("Please select a valid month and year to view slip details");
                    errorimage.setImageResource(R.drawable.nothing_found);
                    errorlayout.setVisibility(View.VISIBLE);
                    generateAll.setVisibility(View.GONE);
                    workingdaysheading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
                String selectedYear = adapterView.getItemAtPosition(position).toString();
                String selectedMonth = month.getSelectedItem().toString();
                workingdaysheading.setVisibility(View.VISIBLE);
                monthnametxt.setText(selectedMonth);
                if (!selectedYear.equals("2023")) {
                    errordesc.setText("There is no payslip for the year " + selectedYear);
                    errorimage.setImageResource(R.drawable.nothing_found);
                    errorlayout.setVisibility(View.VISIBLE);
                    generateAll.setVisibility(View.GONE);
                    workingdaysheading.setVisibility(View.GONE);
                } else if (selectedMonth.equals("January")) {
                    String slipId = "00007";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);
                } else if (selectedMonth.equals("February")) {
                    String slipId = "000020";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("March")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("April")) {
                    String slipId = "00004";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("May")) {
                    String slipId = "00001";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("June")) {
                    String slipId = "00002";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("July")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("August")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("September")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("October")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else if (selectedMonth.equals("November")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);


                } else if (selectedMonth.equals("December")) {
                    String slipId = "00003";
                    retrieveSalarySlip(selectedMonth, selectedYear, name, slipId);

                } else {
                    errordesc.setText("Please select a valid month and year to view slip details");
                    errorimage.setImageResource(R.drawable.nothing_found);
                    errorlayout.setVisibility(View.VISIBLE);
                    generateAll.setVisibility(View.GONE);
                    workingdaysheading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void getPieData() {
        progressBar.setVisibility(View.VISIBLE);
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        String name = "Sal Slip/" + sessionManager.getKeyEmployeeNamingSeries() + "/";
        ApiClient.getApiClient().getSlipData("Salary Slip", name + "00001", sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
            @Override
            public void onResponse(@NonNull Call<SalarySlipData> call, @NonNull Response<SalarySlipData> response) {
                if (response.isSuccessful()) {
                    SalarySlipData salarySlipData = response.body();
                    if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                        SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                        List<PieEntry> entries = new ArrayList<>();
                        int ded = data.getTotalDeduction().intValue();
                        System.out.println("ded = " + ded);
                        int earn = data.getGrossPay().intValue();
                        System.out.println("earn = " + earn);
                        entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                        entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));
                        float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)

                        PieDataSet dataSet = new PieDataSet(entries, ""); // Provide a label for the dataset
                        int desiredWidth = 500; // in pixels

                        ViewGroup.LayoutParams layoutParams = pieChart.getLayoutParams();
                        layoutParams.width = desiredWidth;
                        layoutParams.height = 450;

                        PieData pieData = new PieData(dataSet);

                        int orangeColor = 0xFFFFA500;
                        int purpleColor = 0xFF4169E1;
                        int[] colors = {orangeColor, purpleColor}; // Add as many colors as needed
                        dataSet.setColors(colors);
                        pieChart.notifyDataSetChanged();


                        pieData.setDrawValues(false);
                        pieChart.getDescription().setEnabled(false);
                        pieChart.setLayoutParams(layoutParams);
                        pieChart.setHoleRadius(desiredHoleRadius);

                        pieChart.setCenterText(kenyanCurrencyFormat.format(data.getBaseRoundedTotal()) + "\n\n" + "Gross Pay");
                        pieChart.setDrawEntryLabels(false);
                        pieChart.setData(pieData);
                        pieChart.invalidate(); //chart
                        if (!data.getDeductions().isEmpty()) {
                            deductions.addAll(data.getDeductions());
                            progressBar.setVisibility(View.GONE);
                            recyclerdeductions.setHasFixedSize(true);

                            deductionAdapter.notifyDataSetChanged();
                        } else {
                            deductionslayout.setVisibility(View.GONE);
                            errordesc.setText("No Deductions to show");
                            errorimage.setImageResource(R.drawable.nothing_found);
                            errorlayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);

                        }
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


                    } else {
                        piechartcardview.setVisibility(View.GONE);
                        errordesc.setText("No payslip details to show");
                        errorimage.setImageResource(R.drawable.nothing_found);
                        errorlayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        earningslayoutpayslip.setVisibility(View.GONE);
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
                            progressBar.setVisibility(View.GONE);
                        } catch (IOException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SalarySlipData> call, Throwable t) {
                Toast.makeText(PaySlipActivity2.this, "error occured" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createPieChart(List<PieEntry> entries, PieChart pieChart, float desiredHoleRadius, int desiredWidth) {
        PieDataSet dataSet = new PieDataSet(entries, ""); // Provide a label for the dataset

        ViewGroup.LayoutParams layoutParams = pieChart.getLayoutParams();
        layoutParams.width = desiredWidth;
        layoutParams.height = 450;
        pieChart.setLayoutParams(layoutParams);

        PieData pieData = new PieData(dataSet);
        int orangeColor = 0xFFFFA500;
        int purpleColor = 0xFF4169E1;
        int[] colors = {orangeColor, purpleColor}; // Add as many colors as needed
        dataSet.setColors(colors);
        pieChart.notifyDataSetChanged();

        pieData.setDrawValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(desiredHoleRadius);

        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refresh the chart
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


    public void retrieveSalarySlip(String selectedMonth, String selectedYear, String name, String slipIdentifier) {
        String slipId = name + slipIdentifier; // Generate the complete slip ID
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));

        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getSlipData("Salary Slip", slipId, sessionManager.getUserId()).enqueue(new Callback<SalarySlipData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SalarySlipData> call, Response<SalarySlipData> response) {
                if (response.isSuccessful()) {
                    SalarySlipData salarySlipData = response.body();
                    if (salarySlipData != null && salarySlipData.getDocs() != null && !salarySlipData.getDocs().isEmpty()) {
                        errorlayout.setVisibility(View.GONE);
                        SalarySlipData.Doc data = salarySlipData.getDocs().get(0);
                        double workingdays = data.getTotalWorkingDays();
                        int working = (int) workingdays;
                        working_days.setText(Integer.toString(working));
                        if (data.getDeductions().isEmpty() || data.getEarnings().isEmpty()) {
                            earnings.clear();
                            adapter.notifyDataSetChanged();
                            deductions.clear();
                            deductionAdapter.notifyDataSetChanged();
                            payslipdetailslayout.setVisibility(View.GONE);
                            errordesc.setText("No Deductions for the month of " + selectedMonth + " to show");
                            errorimage.setImageResource(R.drawable.nothing_found);
                            errorlayout.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                            /*List<PieEntry> entries = new ArrayList<>();
                            int ded = data.getTotalDeduction().intValue();
                            int earn = data.getGrossPay().intValue();
                            entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                            entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));

                            float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)
                            int desiredWidth = 500; // in pixels

                            createPieChart(entries, pieChart, desiredHoleRadius, desiredWidth);*/

                        } else {
                            payslipdetailslayout.setVisibility(View.VISIBLE);
                            earnings.clear();
                            adapter.notifyDataSetChanged();
                            earnings.addAll(data.getEarnings());
                            recyclerearning.setHasFixedSize(true);
                            adapter.notifyDataSetChanged();
                            deductions.clear();
                            deductionAdapter.notifyDataSetChanged();
                            deductions.addAll(data.getDeductions());
                            progressBar.setVisibility(View.GONE);
                            recyclerdeductions.setHasFixedSize(true);
                            deductionAdapter.notifyDataSetChanged();
                            // generateAll.setVisibility(View.VISIBLE);
                            List<PieEntry> entries = new ArrayList<>();
                            int ded = data.getTotalDeduction().intValue();
                            int earn = data.getGrossPay().intValue();
                            entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                            entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));

                            float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)
                            int desiredWidth = 500; // in pixels

                            createPieChart(entries, pieChart, desiredHoleRadius, desiredWidth);

                           /* List<PieEntry> entries = new ArrayList<>();
                            int ded = data.getTotalDeduction().intValue();
                            int earn = data.getGrossPay().intValue();
                            entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                            entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));
                            float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)

                            PieDataSet dataSet = new PieDataSet(entries, ""); // Provide a label for the dataset
                            int desiredWidth = 500; // in pixels

                            ViewGroup.LayoutParams layoutParams = pieChart.getLayoutParams();
                            layoutParams.width = desiredWidth;
                            layoutParams.height = 450;
                            PieData pieData = new PieData(dataSet);

                            int orangeColor = 0xFFFFA500;
                            int purpleColor = 0xFF4169E1;
                            int[] colors = {orangeColor, purpleColor}; // Add as many colors as needed
                            dataSet.setColors(colors);
                            pieChart.notifyDataSetChanged();


                            pieData.setDrawValues(false);
                            pieChart.getDescription().setEnabled(false);
                            pieChart.setLayoutParams(layoutParams);
                            pieChart.setHoleRadius(desiredHoleRadius);

                            pieChart.setCenterText(kenyanCurrencyFormat.format(data.getBaseRoundedTotal()) + "\n\n" + "Gross Pay");
                            pieChart.setDrawEntryLabels(false);
                            pieChart.setData(pieData);
                            pieChart.invalidate(); //chart*/
                        }

                    } else {
                        generateAll.setVisibility(View.GONE);
                        working_days.setText("0");
                        piechartcardview.setVisibility(View.GONE);
                        errordesc.setText("No payslip details for " + selectedMonth + " " + selectedYear);
                        errorimage.setImageResource(R.drawable.nothing_found);
                        errorlayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        //earningslayoutpayslip.setVisibility(View.GONE);
                        //Toast.makeText(PaySlipActivity2.this, "No salary slip for the specified month yet", Toast.LENGTH_SHORT).show();
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
                            progressBar.setVisibility(View.GONE);
                        } catch (IOException e) {
                            e.printStackTrace();
                            // progressBar.setVisibility(View.GONE);
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<SalarySlipData> call, Throwable t) {
                // Handle failure case here...
            }
        });
    }

    public void getSlipdata() {
        ApiClient.getApiClient().getSlipNumber("Salary Slip", sessionManager.getUserId()).enqueue(new Callback<SlipNumber>() {
            @Override
            public void onResponse(Call<SlipNumber> call, Response<SlipNumber> response) {
                if (response.isSuccessful()) {
                    SlipNumber slipNumber = response.body();
                    if (slipNumber != null) {
                        SlipNumber.Message message = slipNumber.getMessage();
                        List<String> keys = message.getKeys();
                        List<List<String>> values = message.getValues();

                        for (List<String> valueList : values) {
                            for (String value : valueList) {
                                System.out.println("value = " + value);
                                System.out.println(value);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SlipNumber> call, Throwable t) {

            }
        });
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

