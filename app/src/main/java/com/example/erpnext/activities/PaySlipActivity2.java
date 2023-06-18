package com.example.erpnext.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

    ProgressBar progressBar;
    PieChart pieChart;
    CardView piechartcardview;
    TextView errordesc;
    LinearLayout earningslayoutpayslip, deductionlayout, errorlayout;
    CircleImageView errorimage;
    UserSessionManager sessionManager;
    SalarySlipAdapter adapter;
    SalarySlipDeductionAdapter deductionAdapter;
    RecyclerView recyclerearning, recyclerdeductions;
    RelativeLayout deductionslayout;
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
        pieChart = findViewById(R.id.pieChart);
        recyclerearning = findViewById(R.id.recyclerearning);


        adapter = new SalarySlipAdapter(earnings, PaySlipActivity2.this);
        recyclerearning.setAdapter(adapter);

        earningslayoutpayslip = findViewById(R.id.errorforearningslayout);
        deductionlayout = findViewById(R.id.errorfordeductionlayout);
        errorlayout = findViewById(R.id.errorlayout);
        piechartcardview = findViewById(R.id.pichartlayout);

        recyclerdeductions = findViewById(R.id.recyclerviewdeductions);
        deductionAdapter = new SalarySlipDeductionAdapter(deductions, PaySlipActivity2.this);

        recyclerearning.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        recyclerdeductions.setLayoutManager(new LinearLayoutManager(PaySlipActivity2.this));
        recyclerdeductions.setAdapter(deductionAdapter);
        progressBar = findViewById(R.id.progressbar);
        sessionManager = new UserSessionManager(this);
        getSlipdata();
        getPieData();


        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));


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
                        layoutParams.height = 500;

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
                        errordesc.setText("No Earnings to show");
                        errorimage.setImageResource(R.drawable.nothing_found);
                        errorlayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
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
                Toast.makeText(PaySlipActivity2.this, "error occured"+ t.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void getSlipdata(){
        ApiClient.getApiClient().getSlipNumber("Salary Slip", sessionManager.getUserId()).enqueue(new Callback<SlipNumber>() {
            @Override
            public void onResponse(Call<SlipNumber> call, Response<SlipNumber> response) {
                if (response.isSuccessful()){
                    SlipNumber slipNumber = response.body();
                    if (slipNumber != null){
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

}