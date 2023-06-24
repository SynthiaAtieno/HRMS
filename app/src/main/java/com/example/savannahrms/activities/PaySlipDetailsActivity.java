package com.example.savannahrms.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.savannahrms.R;
import com.example.savannahrms.adapters.SlipDetailsAdapter;
import com.example.savannahrms.models.PermissionError;
import com.example.savannahrms.models.SalarySlipReport;
import com.example.savannahrms.services.ApiClient;
import com.example.savannahrms.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaySlipDetailsActivity extends AppCompatActivity {
    ProgressBar progressBar;
    List<SalarySlipReport.Datum> slipDetails;
    SlipDetailsAdapter adapter;
    UserSessionManager sessionManager;
    TextView firstbane, fromtodate, totaltxt;
    RecyclerView slipdetailsrecycler;

    LinearLayout linearLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip_details);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.payslipdetails_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");
        firstbane = findViewById(R.id.FirstNameTxtForSlip);
        slipDetails = new ArrayList<>();
        adapter = new SlipDetailsAdapter(slipDetails, this);

        linearLayout = findViewById(R.id.payslipnotfoundlayout);
        /*fromtodate = findViewById(R.id.fromdate);
        totaltxt = findViewById(R.id.totaltxt);*/

        /*fromtodate.setText(getIntent().getExtras().getString("period"));
        fromtodate.setText(getIntent().getExtras().getString("totalEarnings"));*/
        slipdetailsrecycler = findViewById(R.id.slip_recycler);
        slipdetailsrecycler.setLayoutManager(new LinearLayoutManager(this));
        slipdetailsrecycler.setAdapter(adapter);
        progressBar = findViewById(R.id.progressbarSlipDetails);
        sessionManager = new UserSessionManager(this);
        firstbane.setText("Hey " + sessionManager.getFullName().toUpperCase() + ",");
        getSlipData();
    }

    public void getSlipData() {
        progressBar.setVisibility(View.VISIBLE);
        String fields = "[\"start_date\",\"rounded_total\",\"end_date\",\"letter_head\",\"status\",\"total_working_days\", \"name\"]";
        ApiClient.getApiClient().getSlipDetails("Salary Slip", "sid="+sessionManager.getUserId(), fields).enqueue(new Callback<SalarySlipReport>() {
            @Override
            public void onResponse(Call<SalarySlipReport> call, Response<SalarySlipReport> response) {
                if (response.isSuccessful()) {
                    SalarySlipReport responseModel = response.body();
                    if (responseModel != null && !responseModel.getData().isEmpty()) {
                        List<SalarySlipReport.Datum> datumList1 = responseModel.getData();
                        for (SalarySlipReport.Datum datum:datumList1){
                            if (datum.getStatus().equals("Submitted")){
                                slipDetails.clear();
                                adapter.notifyDataSetChanged();
                                slipDetails.addAll(datumList1);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }

                    }
                    else {
                        linearLayout.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                    //Toast.makeText(PaySlipDetailsActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (response.errorBody() != null) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);
                            AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipDetailsActivity.this);
                            builder.setTitle(errorResponse.getException());

                            if (errorResponse.getException().equals("frappe.exceptions.PermissionError")){
                                builder.setMessage("Your session expired, please login to access your account");
                                builder.setCancelable(false);
                                builder.setPositiveButton("Login", (dialog, which) -> {
                                    dialog.dismiss();
                                    sessionManager.clearSession();
                                    startActivity(new Intent(PaySlipDetailsActivity.this, Login.class));
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }else {
                                builder.setMessage(errorResponse.getException());
                                // Set a positive button and its click listener
                                builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());
                                // Create and show the alert dialog
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                progressBar.setVisibility(View.GONE);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                             progressBar.setVisibility(View.GONE);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<SalarySlipReport> call, Throwable t) {
                //Toast.makeText(PaySlipDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                if (t.getMessage().startsWith("failed to connect to")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PaySlipDetailsActivity.this);
                    builder.setTitle("Error Occurred");
                    builder.setMessage("The server is down or you have no internet connection, Please try again");
                    // Set a positive button and its click listener
                    builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());
                    // Create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    progressBar.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
