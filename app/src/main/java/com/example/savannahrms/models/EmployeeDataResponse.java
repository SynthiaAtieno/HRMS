package com.example.savannahrms.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDataResponse {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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
    @SerializedName("employee")
    @Expose
    private String employee;
    @SerializedName("naming_series")
    @Expose
    private String namingSeries;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("salutation")
    @Expose
    private String salutation;
    @SerializedName("date_of_joining")
    @Expose
    private String dateOfJoining;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("create_user_permission")
    @Expose
    private Integer createUserPermission;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("employment_type")
    @Expose
    private String employmentType;
    @SerializedName("employee_number")
    @Expose
    private String employeeNumber;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("reports_to")
    @Expose
    private String reportsTo;
    @SerializedName("notice_number_of_days")
    @Expose
    private Integer noticeNumberOfDays;
    @SerializedName("personal_email")
    @Expose
    private String personalEmail;
    @SerializedName("prefered_contact_email")
    @Expose
    private String preferedContactEmail;
    @SerializedName("prefered_email")
    @Expose
    private String preferedEmail;
    @SerializedName("unsubscribed")
    @Expose
    private Integer unsubscribed;
    @SerializedName("current_address")
    @Expose
    private String currentAddress;
    @SerializedName("current_accommodation_type")
    @Expose
    private String currentAccommodationType;
    @SerializedName("permanent_accommodation_type")
    @Expose
    private String permanentAccommodationType;
    @SerializedName("holiday_list")
    @Expose
    private String holidayList;
    @SerializedName("default_shift")
    @Expose
    private String defaultShift;
    @SerializedName("expense_approver")
    @Expose
    private String expenseApprover;
    @SerializedName("leave_approver")
    @Expose
    private String leaveApprover;
    @SerializedName("shift_request_approver")
    @Expose
    private String shiftRequestApprover;
    @SerializedName("ctc")
    @Expose
    private Double ctc;
    @SerializedName("salary_currency")
    @Expose
    private String salaryCurrency;
    @SerializedName("salary_mode")
    @Expose
    private String salaryMode;
    @SerializedName("payroll_cost_center")
    @Expose
    private String payrollCostCenter;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("leave_encashed")
    @Expose
    private String leaveEncashed;
    @SerializedName("lft")
    @Expose
    private Integer lft;
    @SerializedName("rgt")
    @Expose
    private Integer rgt;
    @SerializedName("old_parent")
    @Expose
    private String oldParent;
    @SerializedName("doctype")
    @Expose
    private String doctype;
    @SerializedName("education")
    @Expose
    private List<Education> education;
    @SerializedName("external_work_history")
    @Expose
    private List<Object> externalWorkHistory;
    @SerializedName("internal_work_history")
    @Expose
    private List<Object> internalWorkHistory;

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

    public Integer getCreateUserPermission() {
        return createUserPermission;
    }

    public void setCreateUserPermission(Integer createUserPermission) {
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

    public Integer getNoticeNumberOfDays() {
        return noticeNumberOfDays;
    }

    public void setNoticeNumberOfDays(Integer noticeNumberOfDays) {
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

    public Integer getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(Integer unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
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

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
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

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public String getOldParent() {
        return oldParent;
    }

    public void setOldParent(String oldParent) {
        this.oldParent = oldParent;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
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

}

public class Education {

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
    @SerializedName("school_univ")
    @Expose
    private String schoolUniv;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("year_of_passing")
    @Expose
    private Integer yearOfPassing;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("parentfield")
    @Expose
    private String parentfield;
    @SerializedName("parenttype")
    @Expose
    private String parenttype;
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

    public String getSchoolUniv() {
        return schoolUniv;
    }

    public void setSchoolUniv(String schoolUniv) {
        this.schoolUniv = schoolUniv;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(Integer yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentfield() {
        return parentfield;
    }

    public void setParentfield(String parentfield) {
        this.parentfield = parentfield;
    }

    public String getParenttype() {
        return parenttype;
    }

    public void setParenttype(String parenttype) {
        this.parenttype = parenttype;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

}

}