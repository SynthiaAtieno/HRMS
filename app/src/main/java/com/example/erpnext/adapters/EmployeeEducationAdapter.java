package com.example.erpnext.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.models.EmployeesData;

import java.util.List;

public class EmployeeEducationAdapter extends RecyclerView.Adapter<EmployeeEducationAdapter.ViewHolder> {

    List<EmployeesData.EmployeeDoc> employeeDocList;

    public EmployeeEducationAdapter(List<EmployeesData.EmployeeDoc> employeeDocList) {
        this.employeeDocList = employeeDocList;
    }

    @NonNull
    @Override
    public EmployeeEducationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_layout, parent, false);
        return new EmployeeEducationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeEducationAdapter.ViewHolder holder, int position) {

        EmployeesData.EmployeeDoc employee = employeeDocList.get(position);

        // Access the education data for the current employee
        List<EmployeesData.EmployeeEducation> educationList = employee.getEducation();

        for (EmployeesData.EmployeeEducation education : educationList) {
            holder.qualificationstxt.setText(education.getQualification());
        }

    }

    @Override
    public int getItemCount() {
        return employeeDocList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView qualificationstxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qualificationstxt = itemView.findViewById(R.id.qualifications);
        }

    }
}
