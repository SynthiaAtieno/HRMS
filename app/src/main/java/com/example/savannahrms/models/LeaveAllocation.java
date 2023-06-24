package com.example.savannahrms.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaveAllocation {

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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("total_leaves_allocated")
        @Expose
        private Double totalLeavesAllocated;

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getTotalLeavesAllocated() {
            return totalLeavesAllocated;
        }

        public void setTotalLeavesAllocated(Double totalLeavesAllocated) {
            this.totalLeavesAllocated = totalLeavesAllocated;
        }

    }
}