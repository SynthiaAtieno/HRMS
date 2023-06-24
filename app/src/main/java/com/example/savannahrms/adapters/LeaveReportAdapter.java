package com.example.savannahrms.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savannahrms.R;
import com.example.savannahrms.models.LeaveApplicationReport;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaveReportAdapter extends RecyclerView.Adapter<LeaveReportAdapter.MyViewHolder> {

    List<LeaveApplicationReport.Datum> leaveApplicationReportList;
    Context context;

    public LeaveReportAdapter(List<LeaveApplicationReport.Datum> leaveApplicationReportList, Context context) {
        this.leaveApplicationReportList = leaveApplicationReportList;
        this.context = context;
    }

    @NonNull
    @Override
    public LeaveReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leave_layout_list, parent, false);

        return new LeaveReportAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull LeaveReportAdapter.MyViewHolder holder, int position) {

        LeaveApplicationReport.Datum leaveApplicationReport = leaveApplicationReportList.get(position);

        double numberOfDays = leaveApplicationReport.getTotalLeaveDays();
        int Numberofdays = (int) numberOfDays;
        String stringValue = String.valueOf(Numberofdays);
        System.out.println("Numberofdays = " + Numberofdays);
        holder.noofdays.setText(stringValue);
        String Leavestatus = leaveApplicationReport.getStatus();
        holder.status.setText(Leavestatus);
        String fromdate = DateUtils.convertStringToDate(leaveApplicationReport.getFromDate(),"yyyy-MM-dd", "dd MMM yyy");
        String toDate = DateUtils.convertStringToDate(leaveApplicationReport.getToDate(),"yyyy-MM-dd", "dd MMM yyy");
        String appliedOn = DateUtils.convertStringToDate(leaveApplicationReport.getPostingDate(),"yyyy-MM-dd", "dd MMM yyy");
        holder.fromdate.setText(fromdate);
        holder.todate.setText(toDate);
        holder.applied_on.setText(appliedOn);
        holder.type.setText(leaveApplicationReport.getLeaveType());

        switch (Leavestatus) {
            case "Open":
                holder.open.setVisibility(View.VISIBLE);
                holder.approved.setVisibility(View.GONE);
                holder.rejected.setVisibility(View.GONE);
                break;
            case "Rejected":
                holder.rejected.setVisibility(View.VISIBLE);
                holder.approved.setVisibility(View.GONE);
                holder.open.setVisibility(View.GONE);
                break;
            case "Approved":
                holder.approved.setVisibility(View.VISIBLE);
                holder.open.setVisibility(View.GONE);
                holder.rejected.setVisibility(View.GONE);

            default:
                System.out.println(" No status found");
        }

    }

    @Override
    public int getItemCount() {
        return leaveApplicationReportList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fromdate, todate, type, noofdays, applied_on, status;
        CircleImageView open, rejected, approved;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fromdate = itemView.findViewById(R.id.fromdatereport);
            todate = itemView.findViewById(R.id.todatereport);
            type = itemView.findViewById(R.id.leavetypeforreport);
            noofdays = itemView.findViewById(R.id.numberOfDays);
            applied_on = itemView.findViewById(R.id.appliedon);
            status = itemView.findViewById(R.id.leavestatus);

            open = itemView.findViewById(R.id.openstatus);
            rejected = itemView.findViewById(R.id.rejectedstatus);
            approved = itemView.findViewById(R.id.acceptedstatus);
        }
    }
}
