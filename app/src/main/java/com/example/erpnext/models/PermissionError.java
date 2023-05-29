package com.example.erpnext.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermissionError {


    @SerializedName("exc_type")
    @Expose
    private String excType;
    @SerializedName("session_expired")
    @Expose
    private Integer sessionExpired;
    @SerializedName("exception")
    @Expose
    private String exception;
    @SerializedName("exc")
    @Expose
    private String exc;
    @SerializedName("_server_messages")
    @Expose
    private String serverMessages;

    public String getExcType() {
        return excType;
    }

    public void setExcType(String excType) {
        this.excType = excType;
    }

    public Integer getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(Integer sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getExc() {
        return exc;
    }

    public void setExc(String exc) {
        this.exc = exc;
    }

    public String getServerMessages() {
        return serverMessages;
    }

    public void setServerMessages(String serverMessages) {
        this.serverMessages = serverMessages;
    }

}

