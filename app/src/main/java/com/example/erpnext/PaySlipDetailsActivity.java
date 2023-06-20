package com.example.erpnext;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.adapters.SlipDetailsAdapter;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.models.SlipDetails;
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

public class PaySlipDetailsActivity extends AppCompatActivity {
    ProgressBar progressBar;
    List<SlipDetails.Datum> slipDetails;
    SlipDetailsAdapter adapter;
    UserSessionManager sessionManager;
    TextView firstbane, fromtodate, totaltxt;
    RecyclerView slipdetailsrecycler;


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

        /*fromtodate = findViewById(R.id.fromdate);
        totaltxt = findViewById(R.id.totaltxt);*/

        /*fromtodate.setText(getIntent().getExtras().getString("period"));
        fromtodate.setText(getIntent().getExtras().getString("totalEarnings"));*/
        slipdetailsrecycler = findViewById(R.id.slip_recycler);
        slipdetailsrecycler.setLayoutManager(new LinearLayoutManager(this));
        slipdetailsrecycler.setAdapter(adapter);
        progressBar = findViewById(R.id.progressbarSlipDetails);
        sessionManager = new UserSessionManager(this);
        firstbane.setText("Hey " + sessionManager.getUserFirstName() + ",");
        getSlipData();
    }

    public void getSlipData() {
        progressBar.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getSlipDetails("Salary Slip", sessionManager.getUserId()).enqueue(new Callback<SlipDetails>() {
            @Override
            public void onResponse(Call<SlipDetails> call, Response<SlipDetails> response) {
                if (response.isSuccessful()) {
                    SlipDetails responseModel = response.body();
                    if (responseModel != null && !responseModel.getData().isEmpty()) {
                        List<SlipDetails.Datum> datumList1 = responseModel.getData();
                        slipDetails.addAll(datumList1);
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(PaySlipDetailsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<SlipDetails> call, Throwable t) {
                Toast.makeText(PaySlipDetailsActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}