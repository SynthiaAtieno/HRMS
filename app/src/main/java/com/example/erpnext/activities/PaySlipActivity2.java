package com.example.erpnext.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.adapters.DateUtils;
import com.example.erpnext.adapters.SalarySlipAdapter;
import com.example.erpnext.adapters.SalarySlipDeductionAdapter;
import com.example.erpnext.models.PaySlip;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.SalarySlipData;
import com.example.erpnext.models.SlipDetails;
import com.example.erpnext.models.SlipNumber;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.session.UserSessionManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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
    AppCompatButton downloadBtn;

    TextView deductionComponent, totalDeductionstxt, dateTxt;
    UserSessionManager sessionManager;
    RecyclerView deductionRecycler, earningRecycler;
    List<PaySlip.Deduction> deductionList;
    List<PaySlip.Earning> earningList;
    SalarySlipAdapter earniAdapter;
    SalarySlipDeductionAdapter deductionAdapter;
    ProgressBar progressBar;
    PieChart pieChart;

    ScrollView scrollView;
    LinearLayout linearLayout, earninglayout, errorlayoutforeanings, deductionslayout, errorlayoutfordeductions;

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
        sessionManager = new UserSessionManager(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip Report");


        scrollView = findViewById(R.id.payslipscrollview);
        linearLayout = findViewById(R.id.errorlayoutforpayslip);

        downloadBtn = findViewById(R.id.downloadBtn);
        progressBar = findViewById(R.id.progressbar);
        pieChart = findViewById(R.id.pieChart);
        dateTxt = findViewById(R.id.datetxt);

        earninglayout = findViewById(R.id.eaningslayout);
        errorlayoutforeanings = findViewById(R.id.errorlayoutforearnings);

        errorlayoutfordeductions = findViewById(R.id.errorlayoutfordeductions);
        deductionslayout = findViewById(R.id.layoutfordeductions);

        deductionComponent = findViewById(R.id.deductioncomponent);
        totalDeductionstxt = findViewById(R.id.total_deductions);
        deductionRecycler = findViewById(R.id.deductionsRecyclerview);
        earningRecycler = findViewById(R.id.earningRecyclerview);
        deductionRecycler.setLayoutManager(new LinearLayoutManager(this));
        earningRecycler.setLayoutManager(new LinearLayoutManager(this));
        deductionList = new ArrayList<>();
        earningList = new ArrayList<>();
        earniAdapter = new SalarySlipAdapter(earningList, this);
        deductionAdapter = new SalarySlipDeductionAdapter(deductionList, this);
        deductionRecycler.setAdapter(deductionAdapter);
        earningRecycler.setAdapter(earniAdapter);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popupwinddowfordownload, null);

// Create the popup window
                PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true); // Enable focus
                popupWindow.setOutsideTouchable(true);

                // Show the popup window at a specific location
                popupWindow.showAtLocation(downloadBtn, Gravity.CENTER, 1, -1);
                popupWindow.setOutsideTouchable(false);
                popupWindow.setWidth(400);
                popupWindow.setHeight(400);


// Example: Close the popup window when clicked outside
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                            popupWindow.dismiss();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
// Inflate the layout for the popup window


        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getSlipData("Salary Slip", getIntent().getExtras().getString("name"), "sid="+sessionManager.getUserId()).enqueue(new Callback<PaySlip>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<PaySlip> call, Response<PaySlip> response) {
                if (response.isSuccessful()) {
                    PaySlip paySlip = response.body();

                    if (paySlip != null && paySlip.getData().getEarnings().isEmpty() && paySlip.getData().getDeductions().isEmpty() ){
                        scrollView.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }else {
                       /* scrollView.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);*/

                        if (paySlip != null && !paySlip.getData().getDeductions().isEmpty()) {
                            String totalDeductions = kenyanCurrencyFormat.format(paySlip.getData().getTotalDeduction());
                            totalDeductionstxt.setText(totalDeductions);
                            String formatedDate = DateUtils.convertStringToDate(paySlip.getData().getStartDate(), "yyyy-MM-dd", "MMM yyyy");
                            dateTxt.setText(formatedDate);


                            List<PieEntry> entries = new ArrayList<>();
                            int ded = paySlip.getData().getTotalDeduction().intValue();
                            int earn = paySlip.getData().getGrossPay().intValue();
                            entries.add(new PieEntry(ded, kenyanCurrencyFormat.format(ded) + "\n" + "Deductions"));
                            entries.add(new PieEntry(earn, kenyanCurrencyFormat.format(earn) + "\n" + "Earnings"));

                            float desiredHoleRadius = 85f; // Range: 0.0f (no hole) to 1.0f (full size)
                            int desiredWidth = 500; // in pixels

                            createPieChart(entries, pieChart, desiredHoleRadius, desiredWidth);
                            pieChart.setCenterText(kenyanCurrencyFormat.format(paySlip.getData().getBaseRoundedTotal()) + "\n\n" + "Gross Pay");


                            List<PaySlip.Deduction> deductions = paySlip.getData().getDeductions();
                            deductionList.addAll(deductions);
                            deductionAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        } else {

                            errorlayoutfordeductions.setVisibility(View.VISIBLE);
                            deductionslayout.setVisibility(View.GONE);
                            //Toast.makeText(PaySlipActivity2.this, "No deductions", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                        if (paySlip != null && !paySlip.getData().getEarnings().isEmpty()) {
                            List<PaySlip.Earning> earnings = paySlip.getData().getEarnings();
                            earningList.addAll(earnings);
                            earniAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);

                        } else {
                            errorlayoutforeanings.setVisibility(View.VISIBLE);
                            earninglayout.setVisibility(View.GONE);
                            //Toast.makeText(PaySlipActivity2.this, "No Earnings", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }


                    }
                } else {

                    Toast.makeText(PaySlipActivity2.this, "failure", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);


                }
            }

            @Override
            public void onFailure(Call<PaySlip> call, Throwable t) {
                Toast.makeText(PaySlipActivity2.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        int orangeColor = Color.parseColor("#F86F03");
        int purpleColor = 0xFF4169E1;
        int[] colors = {orangeColor, purpleColor}; // Add as many colors as needed
        dataSet.setColors(colors);
        pieChart.notifyDataSetChanged();

        pieData.setDrawValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleRadius(desiredHoleRadius);

        pieChart.setData(pieData);
        pieChart.setDrawEntryLabels(false); // Disable default entry labels
        pieChart.setDrawMarkers(true);

        dataSet.setDrawIcons(true);
        dataSet.setValueFormatter(new PercentFormatter(pieChart));
        dataSet.setValueLineColor(Color.TRANSPARENT); // Hide value line
        dataSet.setValueLinePart1Length(0.5f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueLineWidth(2f);

        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);
        pieChart.invalidate(); // Refresh the chart
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

