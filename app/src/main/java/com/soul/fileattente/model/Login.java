package com.soul.fileattente.model;

import java.io.Serializable;

public class Login implements Serializable {

    private String username;
    private String password;
    private String token;
    private String response;
    private String profil;

    public Login() {
    }

    public Login(String username, String password, String token, String response, String profil) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.response = response;
        this.profil = profil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
