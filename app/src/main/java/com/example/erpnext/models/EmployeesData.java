package com.example.erpnext.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.List;

public class EmployeesData {


    @SerializedName("docs")
    private List<EmployeeDoc> docs;
    @SerializedName("docinfo")
    private DocInfo docinfo;
    @SerializedName("_link_titles")
    private Object _link_titles;

    public List<EmployeeDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<EmployeeDoc> docs) {
        this.docs = docs;
    }

    public DocInfo getDocinfo() {
        return docinfo;
    }

    public void setDocinfo(DocInfo docinfo) {
        this.docinfo = docinfo;
    }

    public Object get_link_titles() {
        return _link_titles;
    }

    public void set_link_titles(Object _link_titles) {
        this._link_titles = _link_titles;
    }

    public static class EmployeeDoc {
        @SerializedName("name")
        @Nullable
        private String name;
        @SerializedName("owner")
        private String owner;
        @SerializedName("creation")
        private String creation;
        @SerializedName("modified")
        private String modified;
        @SerializedName("modified_by")
        private String modified_by;
        @SerializedName("docstatus")
        private int docstatus;
        @SerializedName("idx")
        private int idx;
        @SerializedName("employee")
        private String employee;
        @SerializedName("naming_series")
        private String naming_series;
        @SerializedName("first_name")
        private String first_name;
        @SerializedName("employee_name")
        private String employee_name;
        @SerializedName("gender")
        private String gender;
        @SerializedName("date_of_birth")
        private String date_of_birth;
        @SerializedName("salutation")
        private String salutation;
        @SerializedName("date_of_joining")
        private String date_of_joining;
        @SerializedName("status")
        private String status;
        @SerializedName("user_id")
        private String user_id;
        @SerializedName("create_user_permission")
        private int create_user_permission;
        @SerializedName("company")
        private String company;
        @SerializedName("department")
        private String department;
        @SerializedName("employment_type")
        private String employment_type;
        @SerializedName("designation")
        private String designation;
        @SerializedName("notice_number_of_days")
        private int notice_number_of_days;
        @SerializedName("cell_number")
        private String cell_number;
        @SerializedName("personal_email")
        private String personal_email;
        @SerializedName("prefered_contact_email")
        private String prefered_contact_email;
        @SerializedName("prefered_email")
        private String prefered_email;
        @SerializedName("unsubscribed")
        private int unsubscribed;
        @SerializedName("current_accommodation_type")
        private String current_accommodation_type;
        @SerializedName("permanent_accommodation_type")
        private String permanent_accommodation_type;
        @SerializedName("holiday_list")
        private String holiday_list;
        @SerializedName("default_shift")
        private String default_shift;
        @SerializedName("ctc")
        private double ctc;
        @SerializedName("salary_currency")
        private String salary_currency;
        @SerializedName("salary_mode")
        private String salary_mode;
        @SerializedName("payroll_cost_center")
        private String payroll_cost_center;
        @SerializedName("marital_status")
        private String marital_status;
        @SerializedName("blood_group")
        private String blood_group;
        @SerializedName("leave_encashed")
        private String leave_encashed;
        @SerializedName("lft")
        private int lft;
        @SerializedName("rgt")
        private int rgt;
        @SerializedName("old_parent")
        private String old_parent;
        @SerializedName("doctype")
        private String doctype;
        @SerializedName("internal_work_history")
        private List<Object> internal_work_history;
        @SerializedName("external_work_history")
        private List<Object> external_work_history;
        @SerializedName("education")
        private List<Object> education;

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

        public String getModified_by() {
            return modified_by;
        }

        public void setModified_by(String modified_by) {
            this.modified_by = modified_by;
        }

        public int getDocstatus() {
            return docstatus;
        }

        public void setDocstatus(int docstatus) {
            this.docstatus = docstatus;
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

        public String getNaming_series() {
            return naming_series;
        }

        public void setNaming_series(String naming_series) {
            this.naming_series = naming_series;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getEmployee_name() {
            return employee_name;
        }

        public void setEmployee_name(String employee_name) {
            this.employee_name = employee_name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDate_of_birth() {
            return date_of_birth;
        }

        public void setDate_of_birth(String date_of_birth) {
            this.date_of_birth = date_of_birth;
        }

        public String getSalutation() {
            return salutation;
        }

        public void setSalutation(String salutation) {
            this.salutation = salutation;
        }

        public String getDate_of_joining() {
            return date_of_joining;
        }

        public void setDate_of_joining(String date_of_joining) {
            this.date_of_joining = date_of_joining;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getCreate_user_permission() {
            return create_user_permission;
        }

        public void setCreate_user_permission(int create_user_permission) {
            this.create_user_permission = create_user_permission;
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

        public String getEmployment_type() {
            return employment_type;
        }

        public void setEmployment_type(String employment_type) {
            this.employment_type = employment_type;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public int getNotice_number_of_days() {
            return notice_number_of_days;
        }

        public void setNotice_number_of_days(int notice_number_of_days) {
            this.notice_number_of_days = notice_number_of_days;
        }

        public String getCell_number() {
            return cell_number;
        }

        public void setCell_number(String cell_number) {
            this.cell_number = cell_number;
        }

        public String getPersonal_email() {
            return personal_email;
        }

        public void setPersonal_email(String personal_email) {
            this.personal_email = personal_email;
        }

        public String getPrefered_contact_email() {
            return prefered_contact_email;
        }

        public void setPrefered_contact_email(String prefered_contact_email) {
            this.prefered_contact_email = prefered_contact_email;
        }

        public String getPrefered_email() {
            return prefered_email;
        }

        public void setPrefered_email(String prefered_email) {
            this.prefered_email = prefered_email;
        }

        public int getUnsubscribed() {
            return unsubscribed;
        }

        public void setUnsubscribed(int unsubscribed) {
            this.unsubscribed = unsubscribed;
        }

        public String getCurrent_accommodation_type() {
            return current_accommodation_type;
        }

        public void setCurrent_accommodation_type(String current_accommodation_type) {
            this.current_accommodation_type = current_accommodation_type;
        }

        public String getPermanent_accommodation_type() {
            return permanent_accommodation_type;
        }

        public void setPermanent_accommodation_type(String permanent_accommodation_type) {
            this.permanent_accommodation_type = permanent_accommodation_type;
        }

        public String getHoliday_list() {
            return holiday_list;
        }

        public void setHoliday_list(String holiday_list) {
            this.holiday_list = holiday_list;
        }

        public String getDefault_shift() {
            return default_shift;
        }

        public void setDefault_shift(String default_shift) {
            this.default_shift = default_shift;
        }

        public double getCtc() {
            return ctc;
        }

        public void setCtc(double ctc) {
            this.ctc = ctc;
        }

        public String getSalary_currency() {
            return salary_currency;
        }

        public void setSalary_currency(String salary_currency) {
            this.salary_currency = salary_currency;
        }

        public String getSalary_mode() {
            return salary_mode;
        }

        public void setSalary_mode(String salary_mode) {
            this.salary_mode = salary_mode;
        }

        public String getPayroll_cost_center() {
            return payroll_cost_center;
        }

        public void setPayroll_cost_center(String payroll_cost_center) {
            this.payroll_cost_center = payroll_cost_center;
        }

        public String getMarital_status() {
            return marital_status;
        }

        public void setMarital_status(String marital_status) {
            this.marital_status = marital_status;
        }

        public String getBlood_group() {
            return blood_group;
        }

        public void setBlood_group(String blood_group) {
            this.blood_group = blood_group;
        }

        public String getLeave_encashed() {
            return leave_encashed;
        }

        public void setLeave_encashed(String leave_encashed) {
            this.leave_encashed = leave_encashed;
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

        public String getOld_parent() {
            return old_parent;
        }

        public void setOld_parent(String old_parent) {
            this.old_parent = old_parent;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public List<Object> getInternal_work_history() {
            return internal_work_history;
        }

        public void setInternal_work_history(List<Object> internal_work_history) {
            this.internal_work_history = internal_work_history;
        }

        public List<Object> getExternal_work_history() {
            return external_work_history;
        }

        public void setExternal_work_history(List<Object> external_work_history) {
            this.external_work_history = external_work_history;
        }

        public List<Object> getEducation() {
            return education;
        }

        public void setEducation(List<Object> education) {
            this.education = education;
        }
    }

    public static class DocInfo {
        @SerializedName("user_info")
        private UserInfo user_info;
        @SerializedName("comments")
        private List<Object> comments;
        @SerializedName("shared")
        private List<Object> shared;
        @SerializedName("assignment_logs")
        private List<Object> assignment_logs;
        @SerializedName("attachment_logs")
        private List<Object> attachment_logs;
        @SerializedName("info_logs")
        private List<Object> info_logs;
        @SerializedName("like_logs")
        private List<Object> like_logs;
        @SerializedName("workflow_logs")
        private List<Object> workflow_logs;
        @SerializedName("doctype")
        private String doctype;
        @SerializedName("name")
        private String name;
        @SerializedName("attachments")
        private List<Object> attachments;
        @SerializedName("communications")
        private List<Object> communications;
        @SerializedName("automated_messages")
        private List<Object> automated_messages;
        @SerializedName("total_comments")
        private int total_comments;
        @SerializedName("versions")
        private List<Object> versions;
        @SerializedName("assignments")
        private List<Object> assignments;
        @SerializedName("permissions")
        private Permissions permissions;
        @SerializedName("views")
        private List<Object> views;
        @SerializedName("energy_point_logs")
        private List<Object> energy_point_logs;
        @SerializedName("additional_timeline_content")
        private List<Object> additional_timeline_content;
        @SerializedName("milestones")
        private List<Object> milestones;
        @SerializedName("is_document_followed")
        private Object is_document_followed;
        @SerializedName("tags")
        private String tags;
        @SerializedName("document_email")
        private Object document_email;


        public UserInfo getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfo user_info) {
            this.user_info = user_info;
        }

        public List<Object> getComments() {
            return comments;
        }

        public void setComments(List<Object> comments) {
            this.comments = comments;
        }

        public List<Object> getShared() {
            return shared;
        }

        public void setShared(List<Object> shared) {
            this.shared = shared;
        }

        public List<Object> getAssignment_logs() {
            return assignment_logs;
        }

        public void setAssignment_logs(List<Object> assignment_logs) {
            this.assignment_logs = assignment_logs;
        }

        public List<Object> getAttachment_logs() {
            return attachment_logs;
        }

        public void setAttachment_logs(List<Object> attachment_logs) {
            this.attachment_logs = attachment_logs;
        }

        public List<Object> getInfo_logs() {
            return info_logs;
        }

        public void setInfo_logs(List<Object> info_logs) {
            this.info_logs = info_logs;
        }

        public List<Object> getLike_logs() {
            return like_logs;
        }

        public void setLike_logs(List<Object> like_logs) {
            this.like_logs = like_logs;
        }

        public List<Object> getWorkflow_logs() {
            return workflow_logs;
        }

        public void setWorkflow_logs(List<Object> workflow_logs) {
            this.workflow_logs = workflow_logs;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Object> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Object> attachments) {
            this.attachments = attachments;
        }

        public List<Object> getCommunications() {
            return communications;
        }

        public void setCommunications(List<Object> communications) {
            this.communications = communications;
        }

        public List<Object> getAutomated_messages() {
            return automated_messages;
        }

        public void setAutomated_messages(List<Object> automated_messages) {
            this.automated_messages = automated_messages;
        }

        public int getTotal_comments() {
            return total_comments;
        }

        public void setTotal_comments(int total_comments) {
            this.total_comments = total_comments;
        }

        public List<Object> getVersions() {
            return versions;
        }

        public void setVersions(List<Object> versions) {
            this.versions = versions;
        }

        public List<Object> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<Object> assignments) {
            this.assignments = assignments;
        }

        public Permissions getPermissions() {
            return permissions;
        }

        public void setPermissions(Permissions permissions) {
            this.permissions = permissions;
        }

        public List<Object> getViews() {
            return views;
        }

        public void setViews(List<Object> views) {
            this.views = views;
        }

        public List<Object> getEnergy_point_logs() {
            return energy_point_logs;
        }

        public void setEnergy_point_logs(List<Object> energy_point_logs) {
            this.energy_point_logs = energy_point_logs;
        }

        public List<Object> getAdditional_timeline_content() {
            return additional_timeline_content;
        }

        public void setAdditional_timeline_content(List<Object> additional_timeline_content) {
            this.additional_timeline_content = additional_timeline_content;
        }

        public List<Object> getMilestones() {
            return milestones;
        }

        public void setMilestones(List<Object> milestones) {
            this.milestones = milestones;
        }

        public Object getIs_document_followed() {
            return is_document_followed;
        }

        public void setIs_document_followed(Object is_document_followed) {
            this.is_document_followed = is_document_followed;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Object getDocument_email() {
            return document_email;
        }

        public void setDocument_email(Object document_email) {
            this.document_email = document_email;
        }
    }

    public static class Permissions {
        @SerializedName("select")
        private int select;
        @SerializedName("raed")
        private int read;
        @SerializedName("write")
        private int write;
        @SerializedName("create")
        private int create;
        @SerializedName("delete")
        private int delete;
        @SerializedName("submit")
        private int submit;
        @SerializedName("cancel")
        private int cancel;
        @SerializedName("amend")
        private int amend;
        @SerializedName("print")
        private int print;
        @SerializedName("email")
        private int email;
        @SerializedName("report")
        private int report;
        @SerializedName("import")
        private int imports;
        @SerializedName("export")
        private int export;
        @SerializedName("set_user_permissions")
        private int set_user_permissions;
        @SerializedName("share")
        private int share;

        public int getSelect() {
            return select;
        }

        public void setSelect(int select) {
            this.select = select;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public int getWrite() {
            return write;
        }

        public void setWrite(int write) {
            this.write = write;
        }

        public int getCreate() {
            return create;
        }

        public void setCreate(int create) {
            this.create = create;
        }

        public int getDelete() {
            return delete;
        }

        public void setDelete(int delete) {
            this.delete = delete;
        }

        public int getSubmit() {
            return submit;
        }

        public void setSubmit(int submit) {
            this.submit = submit;
        }

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
        }

        public int getAmend() {
            return amend;
        }

        public void setAmend(int amend) {
            this.amend = amend;
        }

        public int getPrint() {
            return print;
        }

        public void setPrint(int print) {
            this.print = print;
        }

        public int getEmail() {
            return email;
        }

        public void setEmail(int email) {
            this.email = email;
        }

        public int getReport() {
            return report;
        }

        public void setReport(int report) {
            this.report = report;
        }

        public int getImport() {
            return imports;
        }

        public void setImport(int imports) {
            this.imports = imports;
        }

        public int getExport() {
            return export;
        }

        public void setExport(int export) {
            this.export = export;
        }

        public int getSet_user_permissions() {
            return set_user_permissions;
        }

        public void setSet_user_permissions(int set_user_permissions) {
            this.set_user_permissions = set_user_permissions;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }
    }
}



