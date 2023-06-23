package com.example.erpnext.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SetCookieInterceptor implements Interceptor {
    private List<String> setCookieValues;

    public List<String> getSetCookieValues() {
        return setCookieValues;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        List<String> setCookieHeaders = response.headers().values("Set-Cookie");
        setCookieValues = new ArrayList<>();
        for (String setCookieHeader : setCookieHeaders) {
                parseSpecificValueFromSetCookieHeader(setCookieHeader);
            }

         

        return response;
    }

    private void parseSpecificValueFromSetCookieHeader(String setCookieHeader) {
        String[] cookiePairs = setCookieHeader.split(";");

        for (String pair : cookiePairs) {
            setCookieValues.add(pair.trim());
            }
        }
}