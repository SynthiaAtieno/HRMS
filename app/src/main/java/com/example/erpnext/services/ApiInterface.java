package com.example.erpnext.services;

import com.example.erpnext.models.UserError;
import com.example.erpnext.models.UserModel;
import com.example.erpnext.models.sidebar.Message;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("method/login")
    Call<UserModel> login(@Body Map<String, String> credentials, @Header("Authorization") String authToken,@Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("method/logout")
    Call<ResponseBody> logout();

    @GET("method/frappe.desk.desktop.get_workspace_sidebar_items")
    Call<Message> getSideBar(@Query("sid") String sid);
}
