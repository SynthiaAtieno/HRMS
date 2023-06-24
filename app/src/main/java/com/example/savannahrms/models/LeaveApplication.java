package com.example.savannahrms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class LeaveApplication {

        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("_server_messages")
        @Expose
        private String serverMessages;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getServerMessages() {
            return serverMessages;
        }

        public void setServerMessages(String serverMessages) {
            this.serverMessages = serverMessages;
        }

    public class Data {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("owner")
        @Expose
        private String owner;
        @SerializedName("creation")
        @Expose
        private String creation;
        @SerializedName("modified")
        @Expose
        private String modified;
        @SerializedName("modified_by")
        @Expose
        private String modifiedBy;
        @SerializedName("docstatus")
        @Expose
        private Integer docstatus;
        @SerializedName("idx")
        @Expose
        private Integer idx;
        @SerializedName("naming_series")
        @Expose
        private String namingSeries;
        @SerializedName("employee")
        @Expose
        private String employee;
        @SerializedName("employee_name")
        @Expose
        private String employeeName;
        @SerializedName("leave_type")
        @Expose
        private String leaveType;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("leave_balance")
        @Expose
        private Double leaveBalance;
        @SerializedName("from_date")
        @Expose
        private String fromDate;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("half_day")
        @Expose
        private Integer halfDay;
        @SerializedName("half_day_date")
        @Expose
        private Object halfDayDate;
        @SerializedName("total_leave_days")
        @Expose
        private Double totalLeaveDays;
        @SerializedName("description")
        @Expose
        private Object description;
        @SerializedName("leave_approver")
        @Expose
        private Object leaveApprover;
        @SerializedName("leave_approver_name")
        @Expose
        private Object leaveApproverName;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("salary_slip")
        @Expose
        private Object salarySlip;
        @SerializedName("posting_date")
        @Expose
        private String postingDate;
        @SerializedName("follow_via_email")
        @Expose
        private Integer followViaEmail;
        @SerializedName("color")
        @Expose
        private Object color;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("letter_head")
        @Expose
        private String letterHead;
        @SerializedName("amended_from")
        @Expose
        private Object amendedFrom;
        @SerializedName("doctype")
        @Expose
        private String doctype;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getCreation() {
            return creation;
        }

        public void setCreation(String creation) {
            this.creation = creation;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(String modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Integer getDocstatus() {
            return docstatus;
        }

        public void setDocstatus(Integer docstatus) {
            this.docstatus = docstatus;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
        }

        public String getNamingSeries() {
            return namingSeries;
        }

        public void setNamingSeries(String namingSeries) {
            this.namingSeries = namingSeries;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(String leaveType) {
            this.leaveType = leaveType;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Double getLeaveBalance() {
            return leaveBalance;
        }

        public void setLeaveBalance(Double leaveBalance) {
            this.leaveBalance = leaveBalance;
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

        public Integer getHalfDay() {
            return halfDay;
        }

        public void setHalfDay(Integer halfDay) {
            this.halfDay = halfDay;
        }

        public Object getHalfDayDate() {
            return halfDayDate;
        }

        public void setHalfDayDate(Object halfDayDate) {
            this.halfDayDate = halfDayDate;
        }

        public Double getTotalLeaveDays() {
            return totalLeaveDays;
        }

        public void setTotalLeaveDays(Double totalLeaveDays) {
            this.totalLeaveDays = totalLeaveDays;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public Object getLeaveApprover() {
            return leaveApprover;
        }

        public void setLeaveApprover(Object leaveApprover) {
            this.leaveApprover = leaveApprover;
        }

        public Object getLeaveApproverName() {
            return leaveApproverName;
        }

        public void setLeaveApproverName(Object leaveApproverName) {
            this.leaveApproverName = leaveApproverName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getSalarySlip() {
            return salarySlip;
        }

        public void setSalarySlip(Object salarySlip) {
            this.salarySlip = salarySlip;
        }

        public String getPostingDate() {
            return postingDate;
        }

        public void setPostingDate(String postingDate) {
            this.postingDate = postingDate;
        }

        public Integer getFollowViaEmail() {
            return followViaEmail;
        }

        public void setFollowViaEmail(Integer followViaEmail) {
            this.followViaEmail = followViaEmail;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getLetterHead() {
            return letterHead;
        }

        public void setLetterHead(String letterHead) {
            this.letterHead = letterHead;
        }

        public Object getAmendedFrom() {
            return amendedFrom;
        }

        public void setAmendedFrom(Object amendedFrom) {
            this.amendedFrom = amendedFrom;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

    }



}
