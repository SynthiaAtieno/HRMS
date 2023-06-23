package com.example.erpnext.models;

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

    @SerializedName("leaveApprover")
    String leaveApprover;

    public LeaveApplicationData() {
    }

    public LeaveApplicationData(String employeeId, String startDate, String endDate, String leaveType, String reasonForApplication, String leaveApprover) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.reasonForApplication = reasonForApplication;
        this.leaveApprover = leaveApprover;
    }


// Constructor, getters, and setters


    public String getLeaveApprover() {
        return leaveApprover;
    }

    public void setLeaveApprover(String leaveApprover) {
        this.leaveApprover = leaveApprover;
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

