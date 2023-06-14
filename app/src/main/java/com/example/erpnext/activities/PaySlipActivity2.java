package com.example.erpnext.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.CustomAdapter;
import com.example.erpnext.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaySlipActivity2 extends AppCompatActivity {
    Toolbar toolbar;
    Spinner spinnerFrom, spinnerTo, spinnerYear;
    AppCompatButton generateAll;
    FrameLayout frame1, frame2;
    TextView txtfirstmonth, txtsecondmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_slip2);
        toolbar = findViewById(R.id.payslip_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payslip");

        spinnerFrom = findViewById(R.id.spinner_item_textfrom);
        spinnerTo = findViewById(R.id.spinner_item_textto);
        spinnerYear = findViewById(R.id.spinner_item_year);
        generateAll = findViewById(R.id.generate_all);
        frame1 = findViewById(R.id.framemonthpayslip);
        frame2 = findViewById(R.id.framesecondmonthpayslip);
        txtfirstmonth = findViewById(R.id.monthtxt);
        txtsecondmonth = findViewById(R.id.secondmonthtxt);

        generateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame1.setVisibility(View.VISIBLE);
                frame2.setVisibility(View.VISIBLE);
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(PaySlipActivity2.this, adapterView.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();
                txtfirstmonth.setText(adapterView.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(PaySlipActivity2.this, adapterView.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();
                txtsecondmonth.setText(adapterView.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(PaySlipActivity2.this, adapterView.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}