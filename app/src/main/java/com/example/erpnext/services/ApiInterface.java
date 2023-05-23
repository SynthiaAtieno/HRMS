package com.example.erpnext.services;

import com.example.erpnext.models.UserError;
import com.example.erpnext.models.UserModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("method/login")
    Call<UserModel> login(@Body Map<String, String> credentials, @Header("Authorization") String authToken,@Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("method/logout")
    Call<ResponseBody> logout();
}
