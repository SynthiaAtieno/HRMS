package com.example.erpnext.models.holiday_list;

import com.example.erpnext.models.UserInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Docinfo {

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
