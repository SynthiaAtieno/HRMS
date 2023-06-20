package com.example.erpnext.services;

import com.example.erpnext.LeaveType;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.EmployeePermission;
import com.example.erpnext.models.HolidayList;
import com.example.erpnext.models.LeaveApplication;
import com.example.erpnext.models.PaySlip;
import com.example.erpnext.models.SlipDetails;
import com.example.erpnext.models.UserModel;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("method/login")
    Call<UserModel> login(@Body Map<String, String> credentials, @Header("Authorization") String authToken, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("method/logout")
    Call<ResponseBody> logout();

//    @GET("frappe.realtime.get_user_info")
//    Call<UserInfo> getUserInfo(@Query("sid") String sid);

    /*@Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("method/frappe.core.doctype.user_permission.user_permission.get_user_permissions")
    Call<EmployeePermission> getEmployeePermission(@Query("sid") String sid);*/

    @GET("resource/{doctype}/{name}")
    Call<EmployeeDataResponse> getEmployeeData(@Path("doctype") String empDoctype, @Path("name") String name, @Query("sid") String sid);


    @GET("resource/{doctype}/{name}")
    Call<PaySlip> getSlipData(@Path("doctype") String doctype, @Path("name") String name, @Query("sid") String sid);

    @GET("method/frappe.desk.form.load.getdoc")
    Call<HolidayList> getHolidayList(@Query("doctype") String doctype, @Query("name") String name, @Query("sid") String sid);



    @GET("resource/{doctype}")
    Call<LeaveType> getLeaveTypes(@Path("doctype") String doctype, @Query("sid") String sid);

    @POST("resource/{doctype}/{sid}")
    Call<LeaveApplication> ApplyLeave(@Body Map<String, String> parameters, @Path("doctype") String doctype, @Path("sid") String sid,@Header("Content-Type") String contentType, @Header("Accept") String accept);

    @GET("resource/{doctype}")
    Call<SlipDetails> getSlipDetails(@Path("doctype") String doctype, @Query("sid") String sid);


}

