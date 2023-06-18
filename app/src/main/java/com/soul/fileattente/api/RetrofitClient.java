package com.soul.fileattente.api;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //public static String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NjU3ODgyMH0.Mmx22TANirlqYiY6xpNucZ9mIu_PLuyMgG_o6VLZ6A0JcuQSYLDEyx2mcP708ke8i_kXokmtRSGgKDh9exx5YA";
    private String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NzE4NTAwMn0.9cpmSZYG6MITUV9NVK0nosc6XVxmqmhedcZPeUvr294DfFyXtOpbsa5a4hXOANHJndRjFtdPoptE--exUTNQYQ";
    private String gottenAPIKeyfromAPIProvider = "API";
    private String gottenAPIKeyValuefromAPIProvider = "gottenAPIKeyValuefromAPIProvider";
    private String username = "username";
    private String password = "password";

    private final String userAgent = "FileAttenteMobileApp";
    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient() {
        //Initial Request
        //Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
        //.addConverterFactory(GsonConverterFactory.create())
        //.build();
        //myApi = retrofit.create(Api.class);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                //HTP codesUltimate Reference : https://netnut.io/how-to-solve-proxy-error-codes/
                //Go the website : https://www.infobip.com/docs/essentials/api-authentication for muich more details and Check Auth 2.0(https://datatracker.ietf.org/doc/html/rfc6749)
                //Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                //        Credentials.basic("aUsername", "aPassword"));
                //String concatenedUsernamePwd = username + ":" + password;
                //String base64EncodedUsernamePwd = Base64.getEncoder().encodeToString(concatenedUsernamePwd.getBytes("UTF-8")); //in case you want to encode
                Headers.Builder headers = new Headers.Builder();
                headers.add("Authorization", gottenTokenAfterLoginOrRefresh); //When dealing with a token that expires
                //headers.add("Authorization", Credentials.basic("aUsername", "aPassword")) //Basic Authentication
                //headers.add("Authorization", gottenAPIKeyfromAPIProvider + " " + gottenAPIKeyfromAPIProvider); //When dealing with API Key
                //headers.add("Content-Type", "application/json"); //When precising the format of content to submit but with retrofit this might be implicit
                //headers.add("Accept", "application/json"); //When precising the format of content expected but with retrofit this might be implicit
                //headers.add("User-Agent", userAgent); //When dealing with a user Agent
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