package com.example.erpnext.session;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSessionManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_FULL_NAME = "full_name";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public UserSessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USER_ID, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public void  setKeyFullName(String full_name){
        editor.putString(KEY_FULL_NAME, full_name);
        editor.apply();
    }

    public String getFullName(){
        return sharedPreferences.getString(KEY_FULL_NAME, null);
    }
    public boolean isLoggedIn() {
        return getUserId() != null && getFullName() != null;
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }
}

