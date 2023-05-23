package com.example.erpnext.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("home_page")
    @Expose
    private String homePage;
    @SerializedName("full_name")
    @Expose
    private String fullName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}