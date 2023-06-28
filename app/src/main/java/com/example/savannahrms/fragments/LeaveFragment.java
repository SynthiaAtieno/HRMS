package com.example.savannahrms.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.savannahrms.R;
import com.example.savannahrms.adapters.EmployeEducationAdapter;
import com.example.savannahrms.adapters.ProjectsAdapter;
import com.example.savannahrms.models.PermissionError;
import com.example.savannahrms.models.Projects;
import com.example.savannahrms.services.ApiClient;
import com.example.savannahrms.session.UserSessionManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveFragment extends Fragment {
    RecyclerView projectsRecycler;
    ProjectsAdapter projectsAdapter;
    List<Projects.Datum> projectslist = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    UserSessionManager sessionManager;



    public LeaveFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getProjects();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        projectsRecycler = view.findViewById(R.id.projects_recycler);
        linearLayoutManager = new LinearLayoutManager(getContext());
        projectsRecycler.setLayoutManager(linearLayoutManager);
        projectsAdapter = new ProjectsAdapter(projectslist, getContext());
        progressBar = view.findViewById(R.id.projects_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        projectsRecycler.setAdapter(projectsAdapter);
        //linearLayout = view.findViewById(R.id.nothingfoundeducationlayout);
        return view;
    }

    public void getProjects(){
        String fields="[\"project_name\", \"expected_end_date\", \"priority\",\"status\",\"percent_complete\"]";
        sessionManager = new UserSessionManager(getContext());
        ApiClient.getApiClient().getProjects("sid="+sessionManager.getUserId(), fields)
                .enqueue(new Callback<Projects>() {
                    @Override
                    public void onResponse(Call<Projects> call, Response<Projects> response) {
                        if (response.isSuccessful()){
                            Projects projects = response.body();

                            if (projects!= null && !projects.getData().isEmpty() ){
                                List<Projects.Datum> datumList = projects.getData();
                                if (!datumList.isEmpty())
                                {
                                    projectslist.clear();
                                    projectsAdapter.notifyDataSetChanged();
                                    projectslist.addAll(datumList);
                                    projectsAdapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                }else {
                                    Toast.makeText(requireContext(), "The ist is empty", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(requireContext(), "projects is empty", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        else {
                            if (response.errorBody() != null) {
                                progressBar.setVisibility(View.GONE);
                                try {
                                    String errorResponseJson = response.errorBody().string();
                                    PermissionError errorResponse = new Gson().fromJson(errorResponseJson, PermissionError.class);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                    builder.setTitle("Error Occurred");

                                    if (errorResponse.getException().equals("frappe.exceptions.PermissionError: Project")){
                                        builder.setMessage("You have not been assigned any project yet");
                                        // Set a positive button and its click listener
                                        builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                                        // Create and show the alert dialog
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }

                                    else {
                                        builder.setMessage(errorResponse.getException());
                                        // Set a positive button and its click listener
                                        builder.setPositiveButton("Dismiss", (dialog, which) -> dialog.dismiss());

                                        // Create and show the alert dialog
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                        //progressBar.setVisibility(View.GONE);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Projects> call, Throwable t) {
                        Toast.makeText(requireContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}