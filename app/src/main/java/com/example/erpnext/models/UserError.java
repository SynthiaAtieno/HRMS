package com.example.erpnext.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserError {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("exc_type")
    @Expose
    private String excType;
    @SerializedName("exception")
    @Expose
    private String exception;
    @SerializedName("exc")
    @Expose
    private String exc;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExcType() {
        return excType;
    }

    public void setExcType(String excType) {
        this.excType = excType;
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

}