package com.example.savannahrms.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savannahrms.R;
import com.example.savannahrms.models.HolidayList;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull HolidayListAdapter.MyViewHolder holder, int position) {
        HolidayList.Holiday holiday = holidayList.get(position);

        String dateString = holiday.getHolidayDate();
        holder.holidaydate.setText(dateString);
        holder.holidayname.setText(holiday.getDescription());

    }

    @Override
    public int getItemCount() {
        return holidayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView holidayname, doctype, holidaydate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            holidayname = itemView.findViewById(R.id.holidayname);
            doctype = itemView.findViewById(R.id.holiday);
            holidaydate = itemView.findViewById(R.id.holidaydate);
            relativeLayout = itemView.findViewById(R.id.relativelaypoutforholiday);
        }
    }


}

