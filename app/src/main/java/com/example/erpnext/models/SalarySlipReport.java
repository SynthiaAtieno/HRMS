package com.example.erpnext.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalarySlipReport {

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

        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("name")
        @Expose
        String name;
        @SerializedName("rounded_total")
        @Expose
        private Double roundedTotal;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("letter_head")
        @Expose
        private String letterHead;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("total_working_days")
        @Expose
        private Double totalWorkingDays;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public Double getRoundedTotal() {
            return roundedTotal;
        }

        public void setRoundedTotal(Double roundedTotal) {
            this.roundedTotal = roundedTotal;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getLetterHead() {
            return letterHead;
        }

        public void setLetterHead(String letterHead) {
            this.letterHead = letterHead;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getTotalWorkingDays() {
            return totalWorkingDays;
        }

        public void setTotalWorkingDays(Double totalWorkingDays) {
            this.totalWorkingDays = totalWorkingDays;
        }

    }


}