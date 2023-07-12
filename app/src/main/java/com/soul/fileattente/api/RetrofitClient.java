package com.soul.fileattente.api;

import static com.soul.fileattente.utils.ApplicationConstants.BASE_URL;
import static com.soul.fileattente.utils.ApplicationConstants.connectTimeoutDuration;
import static com.soul.fileattente.utils.ApplicationConstants.gottenTokenAfterLoginOrRefresh;
import static com.soul.fileattente.utils.ApplicationConstants.readTimeoutDuration;
import static com.soul.fileattente.utils.ApplicationConstants.userAgent;
import static com.soul.fileattente.utils.ApplicationConstants.writeTimeoutDuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                //Add as many key value pairs as you want in the header below
                Headers.Builder headers = new Headers.Builder();
                headers.add("Authorization", gottenTokenAfterLoginOrRefresh); //When dealing with a token that expires
                headers.add("Content-Type", "application/json"); //When precising the format of content to submit but with retrofit this might be implicit
                headers.add("Accept", "application/json"); //When precising the format of content expected but with retrofit this might be implicit
                headers.add("User-Agent", userAgent); //When dealing with a user Agent

                Request.Builder builder = originalRequest.newBuilder().headers(headers.build());
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(connectTimeoutDuration, TimeUnit.SECONDS)
                .writeTimeout(writeTimeoutDuration, TimeUnit.SECONDS)
                .readTimeout(readTimeoutDuration, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}