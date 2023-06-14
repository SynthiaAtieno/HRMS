package com.example.erpnext.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.erpnext.CustomAdapter;
import com.example.erpnext.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaySlipActivity2 extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");

    }
}