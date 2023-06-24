package com.example.savannahrms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveApplicationReport {

    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public static class Datum {

        @SerializedName("leave_type")
        @Expose
        private String leaveType;

        @SerializedName("leave_balance")
        @Expose
        private Double leave_balance;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("from_date")
        @Expose
        private String fromDate;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("total_leave_days")
        @Expose
        private Double totalLeaveDays;
        @SerializedName("posting_date")
        @Expose
        private String postingDate;

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public Double getTotalLeaveDays() {
            return totalLeaveDays;
        }

        public void setTotalLeaveDays(Double totalLeaveDays) {
            this.totalLeaveDays = totalLeaveDays;
        }

        public String getPostingDate() {
            return postingDate;
        }

        public void setPostingDate(String postingDate) {
            this.postingDate = postingDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getLeave_balance() {
            return leave_balance;
        }

        public void setLeave_balance(Double leave_balance) {
            this.leave_balance = leave_balance;
        }
    }



}