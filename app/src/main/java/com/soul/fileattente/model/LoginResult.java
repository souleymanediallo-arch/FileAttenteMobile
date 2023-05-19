package com.soul.fileattente.model;

import java.io.Serializable;

public class LoginResult implements Serializable {

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
