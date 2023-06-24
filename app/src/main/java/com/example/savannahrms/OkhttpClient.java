package com.example.savannahrms;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpClient {
    public static OkHttpClient getClientToken(String accessToken){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + accessToken);
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });
        return httpClient.build();
    }
}
