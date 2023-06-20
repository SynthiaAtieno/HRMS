package com.example.erpnext.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.models.PaySlip;
import com.example.erpnext.models.SalarySlipData;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SalarySlipDeductionAdapter extends RecyclerView.Adapter<SalarySlipDeductionAdapter.MyViewHolder> {
    List<PaySlip.Deduction> deductionList;
    Context context;

    public SalarySlipDeductionAdapter(List<PaySlip.Deduction> deductionList, Context context) {
        this.deductionList = deductionList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.salaryslipitemdeductions, parent, false);
        return new SalarySlipDeductionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalarySlipDeductionAdapter.MyViewHolder holder, int position) {
        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        PaySlip.Deduction deduction = deductionList.get(position);
        holder.deductioncomponent.setText(deduction.getSalaryComponent());
        holder.deductionamount.setText(kenyanCurrencyFormat.format(deduction.getAmount()));
    }

    @Override
    public int getItemCount() {
        return deductionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView deductionamount, deductioncomponent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deductionamount = itemView.findViewById(R.id.deductionamount);
            deductioncomponent = itemView.findViewById(R.id.deductioncomponent);
        }
    }
}
