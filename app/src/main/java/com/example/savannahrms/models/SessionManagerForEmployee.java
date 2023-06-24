package com.example.savannahrms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionManagerForEmployee {


    @SerializedName("session_expired")
    @Expose
    private Integer sessionExpired;
    @SerializedName("exception")
    @Expose
    private String exception;
    @SerializedName("exc")
    @Expose
    private String exc;
    @SerializedName("_error_message")
    @Expose
    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}

