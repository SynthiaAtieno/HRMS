package com.example.savannahrms.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savannahrms.R;
import com.example.savannahrms.models.Projects;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.MyViewHolder> {

    List<Projects.Datum> projectList;
    Context context;

    public ProjectsAdapter(List<Projects.Datum> projectList, Context context) {
        this.projectList = projectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_layout, parent, false);
        return new ProjectsAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.MyViewHolder holder, int position) {
        Projects.Datum projects = projectList.get(position);


        holder.progressbar.setMax(100);
        int progressValue = projects.getPercentComplete().intValue();
        holder.project_name.setText(projects.getProjectName());
        holder.project_progress.setText(progressValue+"% Complete");
        holder.project_due_date.setText("Due "+DateUtils.convertStringToDate(projects.getExpectedEndDate(), "yyyy-MM-dd", "dd/MMM/yyyy"));
        holder.progressbar.setProgress(progressValue);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView project_name, project_due_date, project_progress;
        ProgressBar progressbar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            project_name = itemView.findViewById(R.id.project_name);
            project_due_date = itemView.findViewById(R.id.project_due_date);
            project_progress = itemView.findViewById(R.id.progress_text);
            progressbar = itemView.findViewById(R.id.progressbarprojects);

        }
    }
}
