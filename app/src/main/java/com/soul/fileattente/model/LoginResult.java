package com.soul.fileattente.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {

//    {
//        "response": "ok"
//    }

//    @SerializedName("response")
    String response;

    public LoginResult() {
    }

    public LoginResult(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "response='" + response + '\'' +
                '}';
    }
}
