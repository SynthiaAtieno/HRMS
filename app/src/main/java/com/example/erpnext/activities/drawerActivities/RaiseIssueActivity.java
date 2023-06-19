package com.example.erpnext.activities.drawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.erpnext.R;

import java.util.Objects;

public class RaiseIssueActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_issue);
        toolbar = findViewById(R.id.raise_isseu_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Raise Issue");
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