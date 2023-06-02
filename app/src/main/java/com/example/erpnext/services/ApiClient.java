package com.example.erpnext.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String baseurl = "http://192.168.88.248:8000/api/";
    private static Retrofit retrofit = null;

    public static ApiInterface getApiClient() {
        if (retrofit == null) {
                        retrofit = new Retrofit.Builder().baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);


    }

    private static String BASEURL = "http://192.168.1.234:8000/api/";
    private static Retrofit retrofit2 = null;

    public static ApiInterface getApiClient2() {
        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2.create(ApiInterface.class);

    }
}
