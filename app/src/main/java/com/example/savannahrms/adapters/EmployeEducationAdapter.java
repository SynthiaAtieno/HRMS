package com.example.savannahrms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savannahrms.R;
import com.example.savannahrms.models.EmployeeDataResponse;

import java.util.List;

public class EmployeEducationAdapter extends RecyclerView.Adapter<EmployeEducationAdapter.MyViewHolder> {
    List<EmployeeDataResponse.Education> employeeEducationList;
    Context context;

    public EmployeEducationAdapter(List<EmployeeDataResponse.Education> employeeEducationList, Context context) {
        this.employeeEducationList = employeeEducationList;
        this.context = context;
    }

    /*public EmployeEducationAdapter(List<EmployeesData.EmployeeEducation> employeeEducationList, Context context) {
            this.employeeEducationList = employeeEducationList;
            this.context = context;
        }
    */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_education_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeEducationAdapter.MyViewHolder holder, int position) {
        EmployeeDataResponse.Education employeeEducation = employeeEducationList.get(position);
        holder.qualification.setText(employeeEducation.getQualification());
    }

    @Override
    public int getItemCount() {
        return employeeEducationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView qualification;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            qualification = itemView.findViewById(R.id.qualification);
        }
    }
}
