package com.soul.fileattente.api;

import java.io.IOException;
import java.util.Base64;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NDQ1MDEzMH0.im6I6vRvXxku_HX01Om9-H3tsQcCbRdLVRU2NOKr-nD17b7EuK6tKiosmH4smaDgsN2GW-0XLWpwzXFg95g6aw";
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