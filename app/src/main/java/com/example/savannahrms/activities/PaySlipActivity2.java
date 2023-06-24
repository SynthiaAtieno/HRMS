package com.example.savannahrms.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savannahrms.R;
import com.example.savannahrms.adapters.DateUtils;
import com.example.savannahrms.adapters.SalarySlipAdapter;
import com.example.savannahrms.adapters.SalarySlipDeductionAdapter;
import com.example.savannahrms.models.PaySlip;
import com.example.savannahrms.models.PermissionError;
import com.example.savannahrms.services.ApiClient;
import com.example.savannahrms.session.UserSessionManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import okhttp3.ResponseBody;
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
    View customView;

    ScrollView scrollView;
    LinearLayout linearLayout, earninglayout, errorlayoutforeanings, deductionslayout, errorlayoutfordeductions;
    private static final int MY_REQUEST_PERMISSION = 100;

    @RequiresApi(api = Build.VERSION_CODES.R)
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
      //  linearLayout = findViewById(R.id.errorlayoutforpayslip);

        downloadBtn = findViewById(R.id.downloadSalarySlipBtn);
        progressBar = findViewById(R.id.progressbar);
        pieChart = findViewById(R.id.pieChart);
        dateTxt = findViewById(R.id.datetxt);

        earninglayout = findViewById(R.id.eaningslayout);
        errorlayoutforeanings = findViewById(R.id.errorlayoutforearnings);

        errorlayoutfordeductions = findViewById(R.id.errorlayoutfordeductions);
        deductionslayout = findViewById(R.id.layoutfordeductions);

        deductionComponent = findViewById(R.id.deductionsComponent);
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

        downloadBtn.setOnClickListener(view -> {
            //if os is marshmallow or above, handle the , handle the runtime
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission denied request permission
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, MY_REQUEST_PERMISSION);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    // permission already granted perfom download
                    ApiClient.getApiClient().DownloadSlip("sid=" + sessionManager.getUserId(), "Salary Slip", "[" + "\"" + getIntent().getExtras().getString("name") + "\"" + "]")
                            .enqueue(new Callback<ResponseBody>() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        String contentDispositionHeader = response.headers().get("Content-Disposition");
                                        if (contentDispositionHeader != null && !contentDispositionHeader.isEmpty()) {
                                            // Example header: attachment; filename="example.pdf"
                                            String[] parts = contentDispositionHeader.split(";");
                                            for (String part : parts) {
                                                part = part.trim();
                                                if (part.startsWith("filename=")) {
                                                    // Remove leading and trailing quotes from the filename
                                                    String fileName = part.substring("filename=".length()).replace("\"", "");
                                                    if (response.body() != null) {
                                                        saveFileLocally(response.body().byteStream(), fileName);
                                                        customView = getLayoutInflater().inflate(R.layout.success, null);
                                                        TextView message = customView.findViewById(R.id.message);
                                                        AppCompatButton button = customView.findViewById(R.id.closebtn);
                                                        message.setText("Download successful");
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
                                                        builder.setView(customView);
                                                        AlertDialog dialog = builder.create();

                                                        button.setOnClickListener(view1 -> dialog.dismiss());
                                                        progressBar.setVisibility(View.GONE);
                                                        dialog.show();
                                                    } else {
                                                        Toast.makeText(PaySlipActivity2.this, "Body is null", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        if (response.errorBody() != null) {
                                            progressBar.setVisibility(View.GONE);
                                            try {
                                                String errorResponseJson = response.errorBody().string();
                                                PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                                                AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
                                                builder.setTitle(errorResponse.getExcType());
                                                String exceptionMessage = errorResponse.getException();
                                                int firstmaessage = exceptionMessage.indexOf(":");
                                                //int lastmessage = exceptionMessage.lastIndexOf(":");
                                                String errorMessage = exceptionMessage.substring(firstmaessage + 1).trim();
                                                builder.setMessage(errorMessage);
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
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(PaySlipActivity2.this, "Failed to download " + t.getMessage(), Toast.LENGTH_SHORT).show();


                                }
                            });
                }
            } else {
                // build version is less than marshmallow perfom download
                ApiClient.getApiClient().DownloadSlip("sid=" + sessionManager.getUserId(), "Salary Slip", "[" + "\"" + getIntent().getExtras().getString("name") + "\"" + "]")
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    String contentDispositionHeader = response.headers().get("Content-Disposition");
                                    if (contentDispositionHeader != null && !contentDispositionHeader.isEmpty()) {
                                        // Example header: attachment; filename="example.pdf"
                                        String[] parts = contentDispositionHeader.split(";");
                                        for (String part : parts) {
                                            part = part.trim();
                                            if (part.startsWith("filename=")) {
                                                // Remove leading and trailing quotes from the filename
                                                String fileName = part.substring("filename=".length()).replace("\"", "");
                                                if (response.body() != null) {
                                                    saveFileLocally(response.body().byteStream(), fileName);

                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(PaySlipActivity2.this, "Failed to download the salary slip", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getSlipData( getIntent().getExtras().getString("name"), "sid=" + sessionManager.getUserId()).enqueue(new Callback<PaySlip>() {
            @Override
            public void onResponse(Call<PaySlip> call, Response<PaySlip> response) {
                if (response.isSuccessful()) {
                    PaySlip paySlip = response.body();
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
                else {

                    if (response.errorBody() != null) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                            AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
                            builder.setTitle(errorResponse.getExcType());
                            String exceptionMessage = errorResponse.getException();
                            int firstmaessage = exceptionMessage.indexOf(":");
                            //int lastmessage = exceptionMessage.lastIndexOf(":");
                            String errorMessage = exceptionMessage.substring(firstmaessage + 1).trim();
                            builder.setMessage(errorMessage);
                            builder.setCancelable(false);
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
            public void onFailure(Call<PaySlip> call, Throwable t) {
                Toast.makeText(PaySlipActivity2.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);


            }
        });


    }


    private void saveFileLocally(InputStream inputStream, String fileName) {
        try {
            File directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(directory, fileName);
            String filePath = file.getAbsolutePath();

            System.out.println("filePath = " + filePath);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            outputStream.close();

            // File saved successfully
            // You can perform any additional operations on the saved file here
        } catch (IOException e) {
            Log.d("Exception occured", "saveFileLocally: " + e);
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission granted for popup, perform download
                ApiClient.getApiClient().DownloadSlip("sid=" + sessionManager.getUserId(), "Salary Slip", "[" + "\"" + getIntent().getExtras().getString("name") + "\"" + "]")
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    String contentDispositionHeader = response.headers().get("Content-Disposition");
                                    if (contentDispositionHeader != null && !contentDispositionHeader.isEmpty()) {
                                        // Example header: attachment; filename="example.pdf"
                                        String[] parts = contentDispositionHeader.split(";");
                                        for (String part : parts) {
                                            part = part.trim();
                                            if (part.startsWith("filename=")) {
                                                // Remove leading and trailing quotes from the filename
                                                String fileName = part.substring("filename=".length()).replace("\"", "");
                                                if (response.body() != null) {
                                                    saveFileLocally(response.body().byteStream(), fileName);

                                                }
                                            }
                                        }
                                    }
                                } else {
                                    if (response.errorBody() != null) {
                                        try {
                                            String errorResponseJson = response.errorBody().string();
                                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                            customView = getLayoutInflater().inflate(R.layout.customalertbuilder, null);
                                            //AppCompatButton button = customView.findViewById(R.id.loginbuttonerror);
                                            TextView textView = customView.findViewById(R.id.textView);
                                            TextView textView1 = customView.findViewById(R.id.alerttext);
                                            textView.setText(errorResponse.getExcType());
                                            textView1.setText(errorResponseJson);

                                            AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
                                            builder.setView(customView)
                                                    .setCancelable(false);
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.dismiss();
                                                }
                                            });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }

                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                customView = getLayoutInflater().inflate(R.layout.failure, null);
                                TextView message = customView.findViewById(R.id.message);
                                AppCompatButton button = customView.findViewById(R.id.closebtn);
                                //TextView alertText = customView.findViewById(R.id.alerttext);
                                message.setText(t.getMessage());
                                AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipActivity2.this);
                                builder.setView(customView);
                                AlertDialog dialog = builder.create();

                                button.setOnClickListener(view1 -> dialog.dismiss());

                                dialog.show();

                            }
                        });
            } else {
                //permission denied from popup show error message
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
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
        int labelColor = ContextCompat.getColor(this, R.color.text_color);
        dataSet.setValueTextColor(labelColor);

        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(labelColor);
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



