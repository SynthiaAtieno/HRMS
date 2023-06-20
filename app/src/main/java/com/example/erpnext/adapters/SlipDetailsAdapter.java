package com.example.erpnext.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.activities.PaySlipActivity2;
import com.example.erpnext.models.SlipDetails;

import java.util.List;

public class SlipDetailsAdapter extends RecyclerView.Adapter<SlipDetailsAdapter.MyViewHolder> {

    List<SlipDetails.Datum> datumList;
    Context context;

    public SlipDetailsAdapter(List<SlipDetails.Datum> datumList, Context context) {
        this.datumList = datumList;
        this.context = context;
    }

    @NonNull
    @Override
    public SlipDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slip_layout, parent, false);
        return new SlipDetailsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlipDetailsAdapter.MyViewHolder holder, int position) {

        SlipDetails.Datum datum = datumList.get(position);
        holder.slipname.setText(datum.getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaySlipActivity2.class);
                intent.putExtra("name", datum.getName());
                /*intent.putExtra("amount", defaultFormat.format(loans.getAmount()));
                intent.putExtra("status", loans.getStatus());
                intent.putExtra("description", loans.getDescription());
                intent.putExtra("loanId", loans.getLoanId());
                intent.putExtra("date", loans.getDate());*/
                view.getContext().startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView slipname;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardviewforslipdetails);
            slipname = itemView.findViewById(R.id.sliptextview);
        }
    }
}
