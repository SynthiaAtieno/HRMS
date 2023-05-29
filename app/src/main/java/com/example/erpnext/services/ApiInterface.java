package com.example.erpnext.services;

import com.example.erpnext.models.EmployeePermission;
import com.example.erpnext.models.EmployeesData;
import com.example.erpnext.models.UserInfo;
import com.example.erpnext.models.UserModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("method/login")
    Call<UserModel> login(@Body Map<String, String> credentials, @Header("Authorization") String authToken,@Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("method/logout")
    Call<ResponseBody> logout();

    @GET("method/frappe.realtime.get_user_info")
    Call<UserInfo> getUserInfo(@Query("sid") String sid);

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("method/frappe.core.doctype.user_permission.user_permission.get_user_permissions")
    Call<EmployeePermission> getEmployeePermission(@Query("sid") String sid);

    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @POST("method/frappe.desk.form.load.getdoc")
    Call<EmployeesData> getEmployeeData(@Query("doctype") String empDoctype, @Query("name") String name);
}
