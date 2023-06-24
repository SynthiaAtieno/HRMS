package com.example.savannahrms.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savannahrms.R;
import com.example.savannahrms.activities.PaySlipActivity2;
import com.example.savannahrms.models.SalarySlipReport;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class SlipDetailsAdapter extends RecyclerView.Adapter<SlipDetailsAdapter.MyViewHolder> {

    List<SalarySlipReport.Datum> datumList;
    Context context;

    public SlipDetailsAdapter(List<SalarySlipReport.Datum> datumList, Context context) {
        this.datumList = datumList;
        this.context = context;
    }

    @NonNull
    @Override
    public SlipDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slip_layout, parent, false);
        return new SlipDetailsAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SlipDetailsAdapter.MyViewHolder holder, int position) {

        NumberFormat kenyanCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "KE"));
        kenyanCurrencyFormat.setCurrency(Currency.getInstance("KES"));
        SalarySlipReport.Datum datum = datumList.get(position);

        String month = DateUtils
                .convertStringToDate(datum.getStartDate(),
                        "yyyy-MM-dd",
                        "MMMM");
        String slipStatus = datum.getStatus();
        if ("Submitted".equals(slipStatus)) {
            holder.slipmonth.setText(month);
            holder.fromdate.setText(DateUtils.
                    convertStringToDate(datum.getStartDate(),
                            "yyyy-MM-dd",
                            "dd/MM/yyyy"));
            holder.amount.setText(kenyanCurrencyFormat.format(datum.getRoundedTotal()));
            holder.todate.setText(DateUtils.convertStringToDate(datum.getEndDate(),"yyyy-MM-dd","dd/MM/yyyy"));
        }
        else if ("Draft".equals(datum.getStatus())){
            holder.cardView.setVisibility(View.GONE);
            //Toast.makeText(context, "You have no salary slips yet", Toast.LENGTH_SHORT).show();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaySlipActivity2.class);
                intent.putExtra("name", datum.getName());
                view.getContext().startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView slipmonth, todate, fromdate, amount;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewforslipdetails);
            slipmonth = itemView.findViewById(R.id.monthtxt);
            todate = itemView.findViewById(R.id.todatesummary);
            fromdate = itemView.findViewById(R.id.fromtodatesummary);
            amount = itemView.findViewById(R.id.totaltxtsummary);
        }
    }
}
