package com.soul.fileattente.model;

public class AutheticationResult {
    String id_token;

    public AutheticationResult() {
    }

    public AutheticationResult(String id_token) {
        this.id_token = id_token;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    @Override
    public String toString() {
        return "AutheticationResult{" +
                "id_token='" + id_token + '\'' +
                '}';
    }
}

