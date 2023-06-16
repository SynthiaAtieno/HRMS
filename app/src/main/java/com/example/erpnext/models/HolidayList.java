package com.example.erpnext.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayList {


        @SerializedName("docs")
        @Expose
        private List<Doc> docs;
        @SerializedName("docinfo")
        @Expose
        private Docinfo docinfo;
        @SerializedName("_link_titles")
        @Expose
        private LinkTitles linkTitles;

        public List<Doc> getDocs() {
            return docs;
        }

        public void setDocs(List<Doc> docs) {
            this.docs = docs;
        }

        public Docinfo getDocinfo() {
            return docinfo;
        }

        public void setDocinfo(Docinfo docinfo) {
            this.docinfo = docinfo;
        }

        public LinkTitles getLinkTitles() {
            return linkTitles;
        }

        public void setLinkTitles(LinkTitles linkTitles) {
            this.linkTitles = linkTitles;
        }

    public static class Doc {

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
        @SerializedName("holiday_list_name")
        @Expose
        private String holidayListName;
        @SerializedName("from_date")
        @Expose
        private String fromDate;
        @SerializedName("to_date")
        @Expose
        private String toDate;
        @SerializedName("total_holidays")
        @Expose
        private Integer totalHolidays;
        @SerializedName("weekly_off")
        @Expose
        private String weeklyOff;
        @SerializedName("doctype")
        @Expose
        private String doctype;
        @SerializedName("holidays")
        @Expose
        private List<Holiday> holidays;

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

        public String getHolidayListName() {
            return holidayListName;
        }

        public void setHolidayListName(String holidayListName) {
            this.holidayListName = holidayListName;
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

        public Integer getTotalHolidays() {
            return totalHolidays;
        }

        public void setTotalHolidays(Integer totalHolidays) {
            this.totalHolidays = totalHolidays;
        }

        public String getWeeklyOff() {
            return weeklyOff;
        }

        public void setWeeklyOff(String weeklyOff) {
            this.weeklyOff = weeklyOff;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public List<Holiday> getHolidays() {
            return holidays;
        }

        public void setHolidays(List<Holiday> holidays) {
            this.holidays = holidays;
        }

    }
    public static class Docinfo {

        @SerializedName("user_info")
        @Expose
        private UserInfo userInfo;
        @SerializedName("comments")
        @Expose
        private List<Object> comments;
        @SerializedName("shared")
        @Expose
        private List<Object> shared;
        @SerializedName("assignment_logs")
        @Expose
        private List<Object> assignmentLogs;
        @SerializedName("attachment_logs")
        @Expose
        private List<Object> attachmentLogs;
        @SerializedName("info_logs")
        @Expose
        private List<Object> infoLogs;
        @SerializedName("like_logs")
        @Expose
        private List<Object> likeLogs;
        @SerializedName("workflow_logs")
        @Expose
        private List<Object> workflowLogs;
        @SerializedName("doctype")
        @Expose
        private String doctype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("attachments")
        @Expose
        private List<Object> attachments;
        @SerializedName("communications")
        @Expose
        private List<Object> communications;
        @SerializedName("automated_messages")
        @Expose
        private List<Object> automatedMessages;
        @SerializedName("total_comments")
        @Expose
        private Integer totalComments;
        @SerializedName("versions")
        @Expose
        private List<Object> versions;
        @SerializedName("assignments")
        @Expose
        private List<Object> assignments;
        @SerializedName("permissions")
        @Expose
        private Permissions permissions;
        @SerializedName("views")
        @Expose
        private List<Object> views;
        @SerializedName("energy_point_logs")
        @Expose
        private List<Object> energyPointLogs;
        @SerializedName("additional_timeline_content")
        @Expose
        private List<Object> additionalTimelineContent;
        @SerializedName("milestones")
        @Expose
        private List<Object> milestones;
        @SerializedName("is_document_followed")
        @Expose
        private Object isDocumentFollowed;
        @SerializedName("tags")
        @Expose
        private String tags;
        @SerializedName("document_email")
        @Expose
        private Object documentEmail;

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
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

        public List<Object> getAssignmentLogs() {
            return assignmentLogs;
        }

        public void setAssignmentLogs(List<Object> assignmentLogs) {
            this.assignmentLogs = assignmentLogs;
        }

        public List<Object> getAttachmentLogs() {
            return attachmentLogs;
        }

        public void setAttachmentLogs(List<Object> attachmentLogs) {
            this.attachmentLogs = attachmentLogs;
        }

        public List<Object> getInfoLogs() {
            return infoLogs;
        }

        public void setInfoLogs(List<Object> infoLogs) {
            this.infoLogs = infoLogs;
        }

        public List<Object> getLikeLogs() {
            return likeLogs;
        }

        public void setLikeLogs(List<Object> likeLogs) {
            this.likeLogs = likeLogs;
        }

        public List<Object> getWorkflowLogs() {
            return workflowLogs;
        }

        public void setWorkflowLogs(List<Object> workflowLogs) {
            this.workflowLogs = workflowLogs;
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

        public List<Object> getAutomatedMessages() {
            return automatedMessages;
        }

        public void setAutomatedMessages(List<Object> automatedMessages) {
            this.automatedMessages = automatedMessages;
        }

        public Integer getTotalComments() {
            return totalComments;
        }

        public void setTotalComments(Integer totalComments) {
            this.totalComments = totalComments;
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

        public List<Object> getEnergyPointLogs() {
            return energyPointLogs;
        }

        public void setEnergyPointLogs(List<Object> energyPointLogs) {
            this.energyPointLogs = energyPointLogs;
        }

        public List<Object> getAdditionalTimelineContent() {
            return additionalTimelineContent;
        }

        public void setAdditionalTimelineContent(List<Object> additionalTimelineContent) {
            this.additionalTimelineContent = additionalTimelineContent;
        }

        public List<Object> getMilestones() {
            return milestones;
        }

        public void setMilestones(List<Object> milestones) {
            this.milestones = milestones;
        }

        public Object getIsDocumentFollowed() {
            return isDocumentFollowed;
        }

        public void setIsDocumentFollowed(Object isDocumentFollowed) {
            this.isDocumentFollowed = isDocumentFollowed;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Object getDocumentEmail() {
            return documentEmail;
        }

        public void setDocumentEmail(Object documentEmail) {
            this.documentEmail = documentEmail;
        }

    }

    public static class Holiday {

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
        @SerializedName("holiday_date")
        @Expose
        private String holidayDate;
        @SerializedName("weekly_off")
        @Expose
        private Integer weeklyOff;
        @SerializedName("description")
        @Expose
        private String description;
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

        public String getHolidayDate() {
            return holidayDate;
        }

        public void setHolidayDate(String holidayDate) {
            this.holidayDate = holidayDate;
        }

        public Integer getWeeklyOff() {
            return weeklyOff;
        }

        public void setWeeklyOff(Integer weeklyOff) {
            this.weeklyOff = weeklyOff;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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




    public static class IfOwner {


    }

    public static class LinkTitles {


    }

    public class Permissions {

        @SerializedName("if_owner")
        @Expose
        private IfOwner ifOwner;
        @SerializedName("has_if_owner_enabled")
        @Expose
        private Boolean hasIfOwnerEnabled;
        @SerializedName("select")
        @Expose
        private Integer select;
        @SerializedName("read")
        @Expose
        private Integer read;
        @SerializedName("write")
        @Expose
        private Integer write;
        @SerializedName("create")
        @Expose
        private Integer create;
        @SerializedName("delete")
        @Expose
        private Integer delete;
        @SerializedName("submit")
        @Expose
        private Integer submit;
        @SerializedName("cancel")
        @Expose
        private Integer cancel;
        @SerializedName("amend")
        @Expose
        private Integer amend;
        @SerializedName("print")
        @Expose
        private Integer print;
        @SerializedName("email")
        @Expose
        private Integer email;
        @SerializedName("report")
        @Expose
        private Integer report;
        @SerializedName("import")
        @Expose
        private Integer _import;
        @SerializedName("export")
        @Expose
        private Integer export;
        @SerializedName("set_user_permissions")
        @Expose
        private Integer setUserPermissions;
        @SerializedName("share")
        @Expose
        private Integer share;

        public IfOwner getIfOwner() {
            return ifOwner;
        }

        public void setIfOwner(IfOwner ifOwner) {
            this.ifOwner = ifOwner;
        }

        public Boolean getHasIfOwnerEnabled() {
            return hasIfOwnerEnabled;
        }

        public void setHasIfOwnerEnabled(Boolean hasIfOwnerEnabled) {
            this.hasIfOwnerEnabled = hasIfOwnerEnabled;
        }

        public Integer getSelect() {
            return select;
        }

        public void setSelect(Integer select) {
            this.select = select;
        }

        public Integer getRead() {
            return read;
        }

        public void setRead(Integer read) {
            this.read = read;
        }

        public Integer getWrite() {
            return write;
        }

        public void setWrite(Integer write) {
            this.write = write;
        }

        public Integer getCreate() {
            return create;
        }

        public void setCreate(Integer create) {
            this.create = create;
        }

        public Integer getDelete() {
            return delete;
        }

        public void setDelete(Integer delete) {
            this.delete = delete;
        }

        public Integer getSubmit() {
            return submit;
        }

        public void setSubmit(Integer submit) {
            this.submit = submit;
        }

        public Integer getCancel() {
            return cancel;
        }

        public void setCancel(Integer cancel) {
            this.cancel = cancel;
        }

        public Integer getAmend() {
            return amend;
        }

        public void setAmend(Integer amend) {
            this.amend = amend;
        }

        public Integer getPrint() {
            return print;
        }

        public void setPrint(Integer print) {
            this.print = print;
        }

        public Integer getEmail() {
            return email;
        }

        public void setEmail(Integer email) {
            this.email = email;
        }

        public Integer getReport() {
            return report;
        }

        public void setReport(Integer report) {
            this.report = report;
        }

        public Integer getImport() {
            return _import;
        }

        public void setImport(Integer _import) {
            this._import = _import;
        }

        public Integer getExport() {
            return export;
        }

        public void setExport(Integer export) {
            this.export = export;
        }

        public Integer getSetUserPermissions() {
            return setUserPermissions;
        }

        public void setSetUserPermissions(Integer setUserPermissions) {
            this.setUserPermissions = setUserPermissions;
        }

        public Integer getShare() {
            return share;
        }

        public void setShare(Integer share) {
            this.share = share;
        }

    }

    public class UserInfo {


    }
}
