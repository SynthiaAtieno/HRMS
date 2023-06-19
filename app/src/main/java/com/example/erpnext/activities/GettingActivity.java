package com.example.erpnext.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.erpnext.R;
import com.example.erpnext.session.UserSessionManager;



public class GettingActivity extends AppCompatActivity {
    AppCompatButton button;
    UserSessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting);

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