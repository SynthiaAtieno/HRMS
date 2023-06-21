package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.EmployeePermission;
import com.example.erpnext.models.LeaveType;
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
    View customView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isDarkThemePreferred()) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        builder = new AlertDialog.Builder(this);
        usernametxt = (TextInputLayout) findViewById(R.id.username);
        passwordtxt = (TextInputLayout) findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        forgotpasswordtxt = findViewById(R.id.forgot_password);
        sessionManager = new UserSessionManager(Login.this);

        usernametxt.getEditText().addTextChangedListener(loginTextWatcher);
        passwordtxt.getEditText().addTextChangedListener(loginTextWatcher);

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
                //getEmployeeId();
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

            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setTitle("Network Error");
            builder.setMessage("You have no internet connection"+"\n"+"Please connect and try again");

            // Set a positive button and its click listener
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());

            // Create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
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
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    sessionManager.setUserId(sid);
                                    sessionManager.setKeyFullName(response.body().getFullName());

                                    getEmployeeId();
                                    getEmployeeData();
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
                                builder.setTitle(errorResponse.getExcType());
                                builder.setMessage(errorResponseJson);
                                builder.setCancelable(false);
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
                    builder.setTitle("Login Error");

                        builder.setMessage(t.getMessage());
                        builder.setCancelable(false);
                        // Set a positive button and its click listener
                        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                        progressDialog.dismiss();


                        // Set a positive button and its click listener
                      /*  builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                        progressDialog.dismiss();*/

                    // Create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    builder.setTitle("Error occurred");
                }
            });
        }
    }

    public void getEmployeeData() {

        ApiClient.getApiClient().getEmployeeData("Employee",  sessionManager.getKeyEmployeeNamingSeries(), "sid="+ sessionManager.getUserId()).enqueue(new Callback<EmployeeDataResponse>() {
            @Override
            public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                if (response.isSuccessful()) {
                    EmployeeDataResponse responseModel = response.body();
                    if (responseModel != null && responseModel.getData() != null) {
                        EmployeeDataResponse.Data data = responseModel.getData();
                        sessionManager.setUserFirstName(data.getFirstName());
                        System.out.println("data.getFirstName() = " + data.getFirstName());
                    } else {

                        Toast.makeText(Login.this, "Null data", Toast.LENGTH_LONG).show();
                    }

                } else {
                    if (response.errorBody() != null) {
                        try {
                            String errorResponseJson = response.errorBody().string();
                                PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                customView = getLayoutInflater().inflate(R.layout.customalertbuilder, null);
                                //AppCompatButton button = customView.findViewById(R.id.loginbuttonerror);
                                TextView textView = customView.findViewById(R.id.textView);
                                TextView textView1 = customView.findViewById(R.id.alerttext);
                                textView.setText(errorResponse.getExcType());
                                textView1.setText(errorResponseJson);

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setView(customView)
                                        .setCancelable(false);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                System.out.println("t.getMessage() = " + t.getMessage());
                //Toast.makeText(Login.this, "Error occurred " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

    public void getEmployeeId(){
        sessionManager = new UserSessionManager(this);
        ApiClient.getApiClient().getEmployeeId("Employee", "sid="+sessionManager.getUserId()).enqueue(new Callback<LeaveType>() {
            @Override
            public void onResponse(Call<LeaveType> call, Response<LeaveType> response) {
                if (response.isSuccessful()){
                    LeaveType leaveType = response.body();
                    if (leaveType != null && !leaveType.getData().isEmpty()){
                        LeaveType.Datum datum = leaveType.getData().get(0);
                        sessionManager.setKeyEmployeeNamingSeries(datum.getName());
                        Toast.makeText(Login.this, "Login Naming Series "+sessionManager.getKeyEmployeeNamingSeries(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LeaveType> call, Throwable t) {
                Toast.makeText(Login.this, "Error retrieving employee naming series", Toast.LENGTH_LONG).show();

            }
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
