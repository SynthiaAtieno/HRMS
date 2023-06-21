package com.example.erpnext;

import com.google.gson.annotations.SerializedName;

public class LeaveApplicationData {

    @SerializedName("employee")
        private String employeeId;
    @SerializedName("from_date")
        private String startDate;
    @SerializedName("to_date")
        private String endDate;
    @SerializedName("leave_type")
        private String leaveType;
    @SerializedName("description")
        String reasonForApplication;

    @SerializedName("sid")
    String sid;

    public LeaveApplicationData() {
    }

    public LeaveApplicationData(String employeeId, String startDate, String endDate, String leaveType, String reasonForApplication, String sid) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reasonForApplication = reasonForApplication;
        this.sid = sid;
    }


// Constructor, getters, and setters


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getReasonForApplication() {
        return reasonForApplication;
    }

    public void setReasonForApplication(String reasonForApplication) {
        this.reasonForApplication = reasonForApplication;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
}

