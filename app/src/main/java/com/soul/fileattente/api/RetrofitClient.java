package com.soul.fileattente.api;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NDQ1MDEzMH0.im6I6vRvXxku_HX01Om9-H3tsQcCbRdLVRU2NOKr-nD17b7EuK6tKiosmH4smaDgsN2GW-0XLWpwzXFg95g6aw";
    private final String userAgent = "FileAttenteMobileApp";
    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {
//Initial Request
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        myApi = retrofit.create(Api.class);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

//                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                        Credentials.basic("aUsername", "aPassword"));

                Headers.Builder headers = new Headers.Builder();
                headers.add("Authorization", gottenTokenAfterLoginOrRefresh);
                headers.add("User-Agent", userAgent);
                //Add as many key value pair as you want in the header below

                Request.Builder builder = originalRequest.newBuilder().headers(headers.build());
                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
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