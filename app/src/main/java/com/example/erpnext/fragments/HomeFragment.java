package com.example.erpnext.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erpnext.R;
import com.example.erpnext.models.UserInfo;
import com.example.erpnext.session.UserSessionManager;
import com.example.erpnext.activities.Login;
import com.example.erpnext.services.ApiClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    UserSessionManager sessionManager ;
    TextView nametxt, usertxt;
    ImageView imageView;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        nametxt = view.findViewById(R.id.nametxt);
        usertxt = view.findViewById(R.id.roletxt);
        imageView = view.findViewById(R.id.logouttxt);
        sessionManager = new UserSessionManager(getContext());
        nametxt.setText(sessionManager.getFullName());
        getUserInfo();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        return view;
    }

    public void logout() {
        ApiClient.getApiClient().logout().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Alert");
                    builder.setMessage("Do you want to Quit?");

                    // Set a positive button and its click listener
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Perform any action you want when the "Yes" button is clicked
                            Toast.makeText(getContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), Login.class));
                            getActivity().finish();
                            sessionManager.clearSession();

                        }
                    });

                    // Set a negative button and its click listener
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Perform any action you want when the "No" button is clicked
                            dialog.dismiss();
                        }
                    });

                    // Create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error Occurred" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getUserInfo(){
        ApiClient.getApiClient().getUserInfo(sessionManager.getUserId()).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()){
                    UserInfo info = response.body();
                    if (info != null){
                        UserInfo.Message message = info.getMessage();
                        usertxt.setText(message.getUserType());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }
}