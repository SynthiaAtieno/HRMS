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

        Spinner spinner = findViewById(R.id.spinner_item_text);  // Replace with your spinner's ID

// Create a list of months
        List<String> months = new ArrayList<>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");

// Create an instance of your custom adapter
        CustomAdapter adapter = new CustomAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, months);

// Set the adapter to the spinner
        spinner.setAdapter(adapter);
    }
}