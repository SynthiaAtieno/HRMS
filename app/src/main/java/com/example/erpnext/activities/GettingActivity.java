package com.example.erpnext.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.example.erpnext.R;
import com.example.erpnext.session.UserSessionManager;

import java.util.Objects;

public class GettingActivity extends AppCompatActivity {
    AppCompatButton button;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting);
        Objects.requireNonNull(getSupportActionBar()).hide();

        sessionManager = new UserSessionManager(this);

        if (sessionManager.isLoggedIn()){
            startActivity(new Intent(GettingActivity.this, MainActivity.class));
            finish();
        }
        button = findViewById(R.id.getting_started_btn);
        button.setOnClickListener(view -> {
            startActivity(new Intent(GettingActivity.this, OnboardingScreen.class));
            finish();
        });
    }
}