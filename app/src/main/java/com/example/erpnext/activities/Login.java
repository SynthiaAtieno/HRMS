package com.example.erpnext.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.session.UserSessionManager;
import com.example.erpnext.models.UserError;
import com.example.erpnext.models.UserModel;
import com.example.erpnext.services.ApiClient;
import com.example.erpnext.services.SetCookieInterceptor;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.IOException;
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
    AppCompatButton login;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    LinearLayout linearLayout;
    String username, password;
    TextView errorMessageTextView, errorMessage;
    AlertDialog.Builder builder;
    UserSessionManager sessionManager;


    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_SID = "sid";
    private static final String KEY_NAME = "full_name";

    View dialogView;
    private SetCookieInterceptor interceptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        builder = new AlertDialog.Builder(this);
        usernametxt = (TextInputLayout) findViewById(R.id.username);
        passwordtxt = (TextInputLayout) findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        builder = new AlertDialog.Builder(this);
        sessionManager = new UserSessionManager(Login.this);

        linearLayout = findViewById(R.id.linearlayout);
        progressDialog = new ProgressDialog(this);
        interceptor = new SetCookieInterceptor();
        if (sessionManager.isLoggedIn()){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
        }
    }

    private void displayUserInfo(String message) {
        Snackbar.make(linearLayout, message, Snackbar.LENGTH_SHORT).show();
        progressDialog.dismiss();
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
        username = usernametxt.getEditText().getText().toString();
        password = passwordtxt.getEditText().getText().toString();
        String contentType = "application/json";
        String accept = "application/json";
        String authToken = "a838616307c06c351b63";
        Map<String, String> credentials = new HashMap<>();
        credentials.put("usr", username);
        credentials.put("pwd", password);
        if (username.isEmpty()) {
            usernametxt.setError("username is required");
            usernametxt.requestFocus();
        } else if (password.isEmpty()) {
            passwordtxt.setError("password is required");
            passwordtxt.requestFocus();
        } else if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
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
                                if (!Objects.equals(sid, "Guest")){
                                    Toast.makeText(Login.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(Login.this, "Full name " + response.body().getFullName(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    //intent.putExtra("username", response.body().getFullName());
                                    //Toast.makeText(Login.this, "sid = "+sid, Toast.LENGTH_SHORT).show();
                                    sessionManager.setUserId(sid);
                                    sessionManager.setKeyFullName(response.body().getFullName());
                                    startActivity(intent);
                                    finish();
                                }else {

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
                                //Toast.makeText(Login.this, "" + errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                //errorMessageTextView.setText("An error occurred.");
                                //errorMessage.setText(errorResponse.getMessage());
                                // Set button click listener
                                /*alertDialog.setTitle("Error occurred");
                                alertDialog.setMessage(errorResponse.getMessage());
                                Button dismissButton = dialogView.findViewById(R.id.dismiss_button);
                                alertDialog.setButton("Dismiss","Dismiss",Onc);
                                dismissButton.setVisibility(View.VISIBLE);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                dismissButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // Dismiss the dialog
                                        dialog.dismiss();
                                    }
                                });*/

                                // Create and show the dialog
//                                alertDialog.setTitle("User Does not exist");
//                                alertDialog.setMessage(errorResponse.getMessage());
//                                alertDialog.show();

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setTitle("Error Occurred");
                                builder.setMessage(errorResponse.getMessage());

                                // Set a positive button and its click listener
                                builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

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
                    alertDialog.setTitle("Error occurred");
                    if (t.getMessage().equals("timeout")){
                        alertDialog.setMessage("Kindly check your internet connection then try again");
                        progressDialog.dismiss();
                    }
                    else {
                        alertDialog.setMessage(t.getMessage());
                    }
                    alertDialog.show();
                }
            });
        }
    }

}