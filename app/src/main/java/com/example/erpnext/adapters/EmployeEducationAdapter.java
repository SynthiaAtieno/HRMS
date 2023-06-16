package com.example.erpnext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.models.EmployeesData;

import java.util.List;

public class EmployeEducationAdapter extends RecyclerView.Adapter<EmployeEducationAdapter.MyViewHolder> {
    List<EmployeesData.EmployeeEducation> employeeEducation;
    Context context;

    public EmployeEducationAdapter(List<EmployeesData.EmployeeEducation> employeeEducation, Context context) {
        this.employeeEducation = employeeEducation;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeEducationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_education_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeEducationAdapter.MyViewHolder holder, int position) {
        EmployeesData.EmployeeEducation education = employeeEducation.get(position);
        holder.qualification.setText(education.getQualification());
    }

    @Override
    public int getItemCount() {
        return employeeEducation.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView qualification;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            qualification = itemView.findViewById(R.id.qualification);
        }
    }
}
