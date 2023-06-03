package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.models.EmployeePermission;
import com.example.erpnext.models.EmployeesData;
import com.example.erpnext.models.PermissionError;
import com.example.erpnext.session.UserSessionManager;
import com.example.erpnext.models.UserError;
import com.example.erpnext.models.UserModel;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.services.SetCookieInterceptor;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    TextInputLayout usernametxt, passwordtxt;
    Button login;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    LinearLayout linearLayout;
    AlertDialog.Builder builder;
    UserSessionManager sessionManager;
    TextView forgotpasswordtxt;

    Calendar calendar = Calendar.getInstance();
    int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        builder = new AlertDialog.Builder(this);
        usernametxt = (TextInputLayout) findViewById(R.id.username);
        passwordtxt = (TextInputLayout) findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        forgotpasswordtxt = findViewById(R.id.forgot_password);
        sessionManager = new UserSessionManager(Login.this);

        usernametxt.getEditText().addTextChangedListener(loginTextWatcher);
        passwordtxt.getEditText().addTextChangedListener(loginTextWatcher);


        String greeting;
        if (hourOfDay >= 0 && hourOfDay < 12) {
            greeting = "Good morning!";
        } else if (hourOfDay >= 12 && hourOfDay < 18) {
            greeting = "Good afternoon!";
        } else {
            greeting = "Good evening!";
        }
        linearLayout = findViewById(R.id.linearlayout);
        progressDialog = new ProgressDialog(this);
        SetCookieInterceptor interceptor = new SetCookieInterceptor();
        forgotpasswordtxt.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, ResetPasswordActivity.class));
            //finish();
        });

        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        } else {
            login.setOnClickListener(v -> {
                login();
                //getEmployeeData();

            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void login() {

        progressDialog.setMessage("Signing in...");
        progressDialog.setTitle("Please wait");

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        String username = usernametxt.getEditText().getText().toString();
        String password = passwordtxt.getEditText().getText().toString();
        String contentType = "application/json";
        String accept = "application/json";
        String authToken = "a838616307c06c351b63";
        Map<String, String> credentials = new HashMap<>();
        credentials.put("usr", username);
        credentials.put("pwd", password);
        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            alertDialog.setTitle("Network Error");
            alertDialog.setMessage("You have no internet connection");
            alertDialog.show();
        } else {
            progressDialog.show();
            ApiClient.getApiClient().login(credentials, contentType, accept, authToken).enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    if (response.isSuccessful()) {
                        Headers headers = response.headers();
                        List<String> setCookieHeaders = headers.values("Set-Cookie");
                        String sid;
                        for (String cookieHeader : setCookieHeaders) {
                            if (cookieHeader.startsWith("sid=")) {
                                sid = cookieHeader.substring(4, cookieHeader.indexOf(';'));
                                if (!Objects.equals(sid, "Guest")) {
                                    Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(Login.this, "Full name " + response.body().getFullName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    sessionManager.setUserId(sid);
                                    sessionManager.setKeyFullName(response.body().getFullName());
                                    getNamingSeries();
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(Login.this, "You are not an authorised user", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            }
                            onBackPressed();
                        }
                        progressDialog.dismiss();
                    } else {
                        // Error
                        if (response.errorBody() != null) {
                            try {
                                String errorResponseJson = response.errorBody().string();
                                UserError errorResponse = new Gson().fromJson(errorResponseJson, UserError.class);

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setTitle("Error Occurred");
                                builder.setMessage(errorResponse.getMessage());

                                // Set a positive button and its click listener
                                builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                                // Create and show the alert dialog
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    progressDialog.dismiss();

                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Error Occurred");
                    if (t.getMessage().equals("timeout")) {
                        builder.setMessage("Kindly check your internet connection then try again");

                        // Set a positive button and its click listener
                        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                        progressDialog.dismiss();
                    } else {
                        builder.setMessage(t.getMessage());

                        // Set a positive button and its click listener
                        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                        progressDialog.dismiss();
                    }
                    // Create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    builder.setTitle("Error occurred");
                }
            });
        }
    }

    private final TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String user = editable.toString();
            String pass = editable.toString();
            boolean isUsernameValid = isUsernameNotEmpty(user);
            boolean isPasswordValid = isPasswordNotEmpty(pass);

            login.setEnabled(isPasswordValid && isUsernameValid);
        }
    };

    private boolean isUsernameNotEmpty(String username) {
        return !username.isEmpty();
    }

    private boolean isPasswordNotEmpty(String password) {
        return !password.isEmpty();
    }

    public void getNamingSeries() {
        ApiClient.getApiClient().getEmployeePermission(sessionManager.getUserId()).enqueue(new Callback<EmployeePermission>() {
            @Override
            public void onResponse(Call<EmployeePermission> call, Response<EmployeePermission> response) {
                if (response.isSuccessful()) {
                    EmployeePermission responseData = response.body();
                    //Toast.makeText(Login.this, ""+responseData, Toast.LENGTH_SHORT).show();
                    if (responseData != null && responseData.getMessage() != null) {
                        List<EmployeePermission.Employee> employees = responseData.getMessage().getEmployee();
                        if (employees != null && !employees.isEmpty()) {
                            String doc = employees.get(0).getDoc();
                            sessionManager.setKeyEmployeeNamingSeries(doc);
                            Toast.makeText(Login.this, "Naming Series" + doc, Toast.LENGTH_SHORT).show();
                            // Log.d(TAG, "onResponse: "+doc);
                        } else {
                            Toast.makeText(Login.this, "Doc is null", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Handle error response

                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                            PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setTitle("Error Occurred");
                            builder.setMessage(errorResponse.getExcType());

                            // Set a positive button and its click listener
                            builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                            // Create and show the alert dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeePermission> call, Throwable t) {

                Toast.makeText(Login.this, "An error occurred" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}