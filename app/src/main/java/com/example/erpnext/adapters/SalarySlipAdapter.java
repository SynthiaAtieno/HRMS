package com.example.erpnext.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.models.SalarySlipData;

import java.util.List;

public class SalarySlipAdapter extends RecyclerView.Adapter<SalarySlipAdapter.MyViewHolder> {

    List<SalarySlipData.Earning> earningList;
    Context context;

    public SalarySlipAdapter(List<SalarySlipData.Earning> earningList, Context context) {
        this.earningList = earningList;
        this.context = context;
    }

    @NonNull
    @Override
    public SalarySlipAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salaryslipitemearning, parent,false);
        return new SalarySlipAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalarySlipAdapter.MyViewHolder holder, int position) {
        SalarySlipData.Earning earningat = earningList.get(position);
        holder.amount.setText(earningat.getAmount().toString());
        holder.component.setText(earningat.getSalaryComponent());
    }

    @Override
    public int getItemCount() {
        return earningList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView amount, component;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            component = itemView.findViewById(R.id.component);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
