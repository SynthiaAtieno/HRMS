package com.example.erpnext.services;

import com.example.erpnext.models.LeaveApplicationData;
import com.example.erpnext.models.EmployeePermission;
import com.example.erpnext.models.LeaveAllocation;
import com.example.erpnext.models.LeaveApplicationReport;
import com.example.erpnext.models.LeaveType;
import com.example.erpnext.models.EmployeeDataResponse;
import com.example.erpnext.models.HolidayList;
import com.example.erpnext.models.LeaveApplication;
import com.example.erpnext.models.PaySlip;
import com.example.erpnext.models.SalarySlipReport;
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
import retrofit2.http.Streaming;

public interface ApiInterface {

    @POST("method/login")
    Call<UserModel> login(@Body Map<String, String> credentials, @Header("Authorization") String authToken, @Header("Content-Type") String contentType, @Header("Accept") String accept);

    @POST("method/logout")
    Call<ResponseBody> logout();

    @GET("method/frappe.auth.get_logged_user")
    @Headers({"Authorization: ca703ed7be711da:a7f3d6e351f608c","Content-Type:application/json"})
    Call<ResponseBody> login();

    //@GET("")
    @Headers({"Content-Type:application/json", "Accept:application/json"})
    @GET("method/frappe.core.doctype.user_permission.user_permission.get_user_permissions")
    Call<EmployeePermission> getEmployeePermission(@Query("sid") String sid);
    @GET("resource/{doctype}")
    Call<LeaveType> getEmployeeId(@Path("doctype") String doctype, @Header("Cookie") String sid);

    @GET("resource/Employee/{name}")
    Call<EmployeeDataResponse> getEmployeeData(@Path("name") String name, @Header("Cookie") String sid);


    @GET("resource/Salary Slip/{name}")
    Call<PaySlip> getSlipData(@Path("name") String name, @Header("Cookie") String sid);

    @GET("resource/Holiday List/{name}")
    Call<HolidayList> getHolidayList(@Path("name") String name, @Header("Cookie") String sid);


    @GET("resource/{doctype}")
    Call<LeaveAllocation> getAllocatedLeaveTypes(@Path("doctype") String doctype, @Header("Cookie") String sid, @Query("fields") String fields);

    @POST("resource/{doctype}")
    Call<LeaveApplication> ApplyLeave(@Body LeaveApplicationData leaveApplicationData, @Path("doctype") String doctype, @Header("Cookie") String authorization);

    @GET("resource/{doctype}")
    Call<SalarySlipReport> getSlipDetails(@Path("doctype") String doctype, @Header("Cookie") String sid, @Query("fields") String fields);


    @GET("resource/{doctype}")
    Call<LeaveApplicationReport> getAppliedLeavesReport(@Path("doctype") String doctype, @Header("Cookie") String sid, @Query("fields") String fields);


    @GET("resource/{doctype}")
    Call<LeaveAllocation> getLeaveTypesForLeaveReport(@Path("doctype") String doctype, @Header("Cookie") String sid);

    //getting leave balance for an employee
    @GET("resource/Leave Allocation")
    Call<LeaveAllocation> getLeaveReportForEmployee(@Header("Cookie") String sid, @Query("fields") String fields);


    //endpoint for downloading salary slip in pdf format
    @Streaming
    @GET("method/frappe.utils.print_format.download_multi_pdf")
    Call<ResponseBody> DownloadSlip(@Header("Cookie") String sid, @Query("doctype") String doctype, @Query("name") String name);


}

