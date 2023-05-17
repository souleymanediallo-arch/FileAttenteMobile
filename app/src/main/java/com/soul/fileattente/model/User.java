package com.soul.fileattente.model;

public class User {

    private String username;
    private String password;
    private Boolean userstatus;


    public User() {
    }

    public User(String userName, String userPwd, Boolean userStatus) {
        this.username = userName;
        this.password = userPwd;
        this.userstatus = userStatus;
    }

    public User(String docteur, String passworddocteur) {

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

    public boolean getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(boolean userstatus) {
        this.userstatus = userstatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", userPwd='" + password + '\'' +
                ", userStatus=" + userstatus +
                '}';
    }
}
