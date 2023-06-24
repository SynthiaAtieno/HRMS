package com.example.savannahrms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlipNumber {


    @SerializedName("message")
    @Expose
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


    public static class Message {

        @SerializedName("keys")
        @Expose
        private List<String> keys;
        @SerializedName("values")
        @Expose
        private List<List<String>> values;
        @SerializedName("user_info")
        @Expose
        private UserInfo userInfo;

        public List<String> getKeys() {
            return keys;
        }

        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        public List<List<String>> getValues() {
            return values;
        }

        public void setValues(List<List<String>> values) {
            this.values = values;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

    }


    public static class UserInfo {


    }
}

