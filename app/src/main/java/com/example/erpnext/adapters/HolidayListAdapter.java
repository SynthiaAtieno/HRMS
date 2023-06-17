package com.example.erpnext.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erpnext.R;
import com.example.erpnext.models.HolidayList;

import java.util.List;

public class HolidayListAdapter extends RecyclerView.Adapter<HolidayListAdapter.MyViewHolder> {

    List<HolidayList.Holiday> holidayList;
    Context context;

    public HolidayListAdapter(List<HolidayList.Holiday> holidayList, Context context) {
        this.holidayList = holidayList;
        this.context = context;
    }

    @NonNull
    @Override
    public HolidayListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_list_layout, parent, false);
        return new HolidayListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayListAdapter.MyViewHolder holder, int position) {
        HolidayList.Holiday holiday = holidayList.get(position);
     /*   String date = holiday.getHolidayDate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
            String formateddate = dateFormat.format(date);
            holder.holidaydate.setText(formateddate);
        }*/
            holder.holidaydate.setText(holiday.getHolidayDate());


        holder.holidayname.setText(holiday.getDescription());
        holder.doctype.setText(holiday.getDoctype());
    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView holidayname, doctype, holidaydate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayname = itemView.findViewById(R.id.holidayname);
            doctype = itemView.findViewById(R.id.holiday);
            holidaydate = itemView.findViewById(R.id.holidaydate);
        }
    }
}
