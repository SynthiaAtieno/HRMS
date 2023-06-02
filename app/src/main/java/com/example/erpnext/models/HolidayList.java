package com.example.erpnext.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayList {

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
    private int docstatus;

    @SerializedName("idx")
    @Expose
    private int idx;

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
    private int totalHolidays;

    @SerializedName("weekly_off")
    @Expose
    private String weeklyOff;

    @SerializedName("doctype")
    @Expose
    private String doctype;

    @SerializedName("holidays")
    @Expose
    private List<Holiday> holidays;

    // Getters and setters (or lombok annotations) go here

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
        private int docstatus;

        @SerializedName("idx")
        @Expose
        private int idx;

        @SerializedName("holiday_date")
        @Expose
        private String holidayDate;

        @SerializedName("weekly_off")
        @Expose
        private int weeklyOff;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("parent")
        @Expose
        private String parent;

        @SerializedName("parentfield")
        @Expose
        private String parentField;

        @SerializedName("parenttype")
        @Expose
        private String parentType;

        @SerializedName("doctype")
        @Expose
        private String doctype;

        // Getters and setters (or lombok annotations) go here


        public String getName() {
            return name;
        }

        public String getOwner() {
            return owner;
        }

        public String getCreation() {
            return creation;
        }

        public String getModified() {
            return modified;
        }

        public String getModifiedBy() {
            return modifiedBy;
        }

        public int getDocstatus() {
            return docstatus;
        }

        public int getIdx() {
            return idx;
        }

        public String getHolidayDate() {
            return holidayDate;
        }

        public int getWeeklyOff() {
            return weeklyOff;
        }

        public String getDescription() {
            return description;
        }

        public String getParent() {
            return parent;
        }

        public String getParentField() {
            return parentField;
        }

        public String getParentType() {
            return parentType;
        }

        public String getDoctype() {
            return doctype;
        }
    }
}


