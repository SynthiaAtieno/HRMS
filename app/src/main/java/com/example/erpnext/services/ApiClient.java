package com.example.erpnext.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String baseurl = "http://192.168.88.249:8000/api/method/";
    private static Retrofit retrofit = null;

    public static ApiInterface getApiClient() {
        if (retrofit == null) {
                        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);


    }
}
