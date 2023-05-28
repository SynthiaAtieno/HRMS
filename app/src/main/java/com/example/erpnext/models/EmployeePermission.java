package com.example.erpnext.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class EmployeePermission {
    @SerializedName("message")
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        @SerializedName("Company")
        private List<Company> Company;
        @SerializedName("Employee")
        private List<Employee> Employee;

        public List<Company> getCompany() {
            return Company;
        }

        public void setCompany(List<Company> Company) {
            this.Company = Company;
        }

        public List<Employee> getEmployee() {
            return Employee;
        }

        public void setEmployee(List<Employee> Employee) {
            this.Employee = Employee;
        }
    }

    public static class Company {
        @SerializedName("doc")
        private String doc;
        @SerializedName("applicable_for")
        private Object applicable_for;
        private int is_default;

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }

        public Object getApplicableFor() {
            return applicable_for;
        }

        public void setApplicableFor(Object applicable_for) {
            this.applicable_for = applicable_for;
        }

        public int getIsDefault() {
            return is_default;
        }

        public void setIsDefault(int is_default) {
            this.is_default = is_default;
        }
    }

    public static class Employee {
        @SerializedName("doc")
        private String doc;
        @SerializedName("applicable_for")
        private Object applicable_for;
        @SerializedName("is_default")
        private int is_default;

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }

        public Object getApplicableFor() {
            return applicable_for;
        }

        public void setApplicableFor(Object applicable_for) {
            this.applicable_for = applicable_for;
        }

        public int getIsDefault() {
            return is_default;
        }

        public void setIsDefault(int is_default) {
            this.is_default = is_default;
        }
    }
}


