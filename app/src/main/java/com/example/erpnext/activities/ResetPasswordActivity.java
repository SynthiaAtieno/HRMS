package com.example.erpnext.activities;

//import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;


import com.example.erpnext.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputLayout editTextEmail;
    private Button buttonResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEmail = (TextInputLayout) findViewById(R.id.username);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);

        // Add TextWatcher to EditText
        editTextEmail.getEditText().addTextChangedListener(emailTextWatcher);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when button is clicked
                String email = editTextEmail.getEditText().getText().toString().trim();
                // Reset password logic goes here
            }
        });
    }

    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Check if email input is valid
            String email = s.toString().trim();
            boolean isEmailValid = isEmailValid(email);

            // Enable or disable the button based on the email validity
            buttonResetPassword.setEnabled(isEmailValid);
        }
    };

    private boolean isEmailValid(String email) {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
        // Add your email validation logic here
        // This is a basic example checking for a non-empty email
       // return !email.isEmpty();
    }
}