package com.example.erpnext.models;


import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("message")
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public static class Message {
        @SerializedName("user")
        private String user;

        @SerializedName("user_type")
        private String userType;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}


