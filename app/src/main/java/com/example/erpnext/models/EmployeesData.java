package com.example.erpnext.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeesData {


    @SerializedName("data")
    private EmployeeData data;

    public EmployeeData getData() {
        return data;
    }

    public void setData(EmployeeData data) {
        this.data = data;
    }

    public static class EmployeeData {
        @SerializedName("name")
        private String name;

        @SerializedName("owner")
        private String owner;

        @SerializedName("creation")
        private String creation;

        @SerializedName("modified")
        private String modified;

        @SerializedName("modified_by")
        private String modifiedBy;

        @SerializedName("docstatus")
        private int docStatus;

        @SerializedName("idx")
        private int idx;

        @SerializedName("employee")
        private String employee;

        @SerializedName("naming_series")
        private String namingSeries;

        @SerializedName("first_name")
        private String firstName;

        @SerializedName("last_name")
        private String lastName;

        @SerializedName("employee_name")
        private String employeeName;

        @SerializedName("gender")
        private String gender;

        @SerializedName("date_of_birth")
        private String dateOfBirth;

        @SerializedName("salutation")
        private String salutation;

        @SerializedName("date_of_joining")
        private String dateOfJoining;

        @SerializedName("status")
        private String status;

        @SerializedName("user_id")
        private String userId;

        @SerializedName("create_user_permission")
        private int createUserPermission;

        @SerializedName("company")
        private String company;

        @SerializedName("department")
        private String department;

        @SerializedName("employment_type")
        private String employmentType;

        @SerializedName("employee_number")
        private String employeeNumber;

        @SerializedName("designation")
        private String designation;

        @SerializedName("reports_to")
        private String reportsTo;

        @SerializedName("notice_number_of_days")
        private int noticeNumberOfDays;

        @SerializedName("personal_email")
        private String personalEmail;

        @SerializedName("prefered_contact_email")
        private String preferedContactEmail;

        @SerializedName("prefered_email")
        private String preferedEmail;

        @SerializedName("unsubscribed")
        private int unsubscribed;

        @SerializedName("current_accommodation_type")
        private String currentAccommodationType;

        @SerializedName("permanent_accommodation_type")
        private String permanentAccommodationType;

        @SerializedName("holiday_list")
        private String holidayList;

        @SerializedName("default_shift")
        private String defaultShift;

        @SerializedName("expense_approver")
        private String expenseApprover;

        @SerializedName("leave_approver")
        private String leaveApprover;

        @SerializedName("shift_request_approver")
        private String shiftRequestApprover;

        @SerializedName("ctc")
        private double ctc;

        @SerializedName("salary_currency")
        private String salaryCurrency;

        @SerializedName("salary_mode")
        private String salaryMode;

        @SerializedName("payroll_cost_center")
        private String payrollCostCenter;

        @SerializedName("marital_status")
        private String maritalStatus;

        @SerializedName("blood_group")
        private String bloodGroup;

        @SerializedName("leave_encashed")
        private String leaveEncashed;

        @SerializedName("lft")
        private int lft;

        @SerializedName("rgt")
        private int rgt;

        @SerializedName("old_parent")
        private String oldParent;

        @SerializedName("doctype")
        private String docType;

        @SerializedName("external_work_history")
        private List<Object> externalWorkHistory;

        @SerializedName("internal_work_history")
        private List<Object> internalWorkHistory;

        @SerializedName("education")
        private List<Object> education;

        // Getters and setters for the fields


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

        public int getDocStatus() {
            return docStatus;
        }

        public void setDocStatus(int docStatus) {
            this.docStatus = docStatus;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getEmployee() {
            return employee;
        }

        public void setEmployee(String employee) {
            this.employee = employee;
        }

        public String getNamingSeries() {
            return namingSeries;
        }

        public void setNamingSeries(String namingSeries) {
            this.namingSeries = namingSeries;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getSalutation() {
            return salutation;
        }

        public void setSalutation(String salutation) {
            this.salutation = salutation;
        }

        public String getDateOfJoining() {
            return dateOfJoining;
        }

        public void setDateOfJoining(String dateOfJoining) {
            this.dateOfJoining = dateOfJoining;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getCreateUserPermission() {
            return createUserPermission;
        }

        public void setCreateUserPermission(int createUserPermission) {
            this.createUserPermission = createUserPermission;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getEmploymentType() {
            return employmentType;
        }

        public void setEmploymentType(String employmentType) {
            this.employmentType = employmentType;
        }

        public String getEmployeeNumber() {
            return employeeNumber;
        }

        public void setEmployeeNumber(String employeeNumber) {
            this.employeeNumber = employeeNumber;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getReportsTo() {
            return reportsTo;
        }

        public void setReportsTo(String reportsTo) {
            this.reportsTo = reportsTo;
        }

        public int getNoticeNumberOfDays() {
            return noticeNumberOfDays;
        }

        public void setNoticeNumberOfDays(int noticeNumberOfDays) {
            this.noticeNumberOfDays = noticeNumberOfDays;
        }

        public String getPersonalEmail() {
            return personalEmail;
        }

        public void setPersonalEmail(String personalEmail) {
            this.personalEmail = personalEmail;
        }

        public String getPreferedContactEmail() {
            return preferedContactEmail;
        }

        public void setPreferedContactEmail(String preferedContactEmail) {
            this.preferedContactEmail = preferedContactEmail;
        }

        public String getPreferedEmail() {
            return preferedEmail;
        }

        public void setPreferedEmail(String preferedEmail) {
            this.preferedEmail = preferedEmail;
        }

        public int getUnsubscribed() {
            return unsubscribed;
        }

        public void setUnsubscribed(int unsubscribed) {
            this.unsubscribed = unsubscribed;
        }

        public String getCurrentAccommodationType() {
            return currentAccommodationType;
        }

        public void setCurrentAccommodationType(String currentAccommodationType) {
            this.currentAccommodationType = currentAccommodationType;
        }

        public String getPermanentAccommodationType() {
            return permanentAccommodationType;
        }

        public void setPermanentAccommodationType(String permanentAccommodationType) {
            this.permanentAccommodationType = permanentAccommodationType;
        }

        public String getHolidayList() {
            return holidayList;
        }

        public void setHolidayList(String holidayList) {
            this.holidayList = holidayList;
        }

        public String getDefaultShift() {
            return defaultShift;
        }

        public void setDefaultShift(String defaultShift) {
            this.defaultShift = defaultShift;
        }

        public String getExpenseApprover() {
            return expenseApprover;
        }

        public void setExpenseApprover(String expenseApprover) {
            this.expenseApprover = expenseApprover;
        }

        public String getLeaveApprover() {
            return leaveApprover;
        }

        public void setLeaveApprover(String leaveApprover) {
            this.leaveApprover = leaveApprover;
        }

        public String getShiftRequestApprover() {
            return shiftRequestApprover;
        }

        public void setShiftRequestApprover(String shiftRequestApprover) {
            this.shiftRequestApprover = shiftRequestApprover;
        }

        public double getCtc() {
            return ctc;
        }

        public void setCtc(double ctc) {
            this.ctc = ctc;
        }

        public String getSalaryCurrency() {
            return salaryCurrency;
        }

        public void setSalaryCurrency(String salaryCurrency) {
            this.salaryCurrency = salaryCurrency;
        }

        public String getSalaryMode() {
            return salaryMode;
        }

        public void setSalaryMode(String salaryMode) {
            this.salaryMode = salaryMode;
        }

        public String getPayrollCostCenter() {
            return payrollCostCenter;
        }

        public void setPayrollCostCenter(String payrollCostCenter) {
            this.payrollCostCenter = payrollCostCenter;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public String getLeaveEncashed() {
            return leaveEncashed;
        }

        public void setLeaveEncashed(String leaveEncashed) {
            this.leaveEncashed = leaveEncashed;
        }

        public int getLft() {
            return lft;
        }

        public void setLft(int lft) {
            this.lft = lft;
        }

        public int getRgt() {
            return rgt;
        }

        public void setRgt(int rgt) {
            this.rgt = rgt;
        }

        public String getOldParent() {
            return oldParent;
        }

        public void setOldParent(String oldParent) {
            this.oldParent = oldParent;
        }

        public String getDocType() {
            return docType;
        }

        public void setDocType(String docType) {
            this.docType = docType;
        }

        public List<Object> getExternalWorkHistory() {
            return externalWorkHistory;
        }

        public void setExternalWorkHistory(List<Object> externalWorkHistory) {
            this.externalWorkHistory = externalWorkHistory;
        }

        public List<Object> getInternalWorkHistory() {
            return internalWorkHistory;
        }

        public void setInternalWorkHistory(List<Object> internalWorkHistory) {
            this.internalWorkHistory = internalWorkHistory;
        }

        public List<Object> getEducation() {
            return education;
        }

        public void setEducation(List<Object> education) {
            this.education = education;
        }
    }
}

