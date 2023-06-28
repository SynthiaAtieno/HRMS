package com.example.savannahrms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Projects {

    @SerializedName("data")
    @Expose
    private List<Datum> data;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
    public static class Datum {

        @SerializedName("project_name")
        @Expose
        private String projectName;
        @SerializedName("expected_end_date")
        @Expose
        private String expectedEndDate;
        @SerializedName("priority")
        @Expose
        private String priority;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("percent_complete")
        @Expose
        private Double percentComplete;

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getExpectedEndDate() {
            return expectedEndDate;
        }

        public void setExpectedEndDate(String expectedEndDate) {
            this.expectedEndDate = expectedEndDate;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getPercentComplete() {
            return percentComplete;
        }

        public void setPercentComplete(Double percentComplete) {
            this.percentComplete = percentComplete;
        }

    }

}