package com.soul.fileattente.repository;

import com.soul.fileattente.model.User;

//Manage to get always one single instance of the
// repository thanks to  Singleton Pattern
public class UserRepository {

    static UserRepository instance;

    public static UserRepository getInstance() {
        if(instance != null) {
            return instance;
        }else{
            return new UserRepository();
        }
    }

    public User getSelectedUser(int version){
        return setSampleUser(version);
    }

    //mimic retreve from database of remote Url call
    private User setSampleUser(){
        User sampleUser = new User();
        sampleUser.setUserName("DIALLO");
        sampleUser.setUserPwd("Souleymane");
        sampleUser.setUserStatus(true);
        return sampleUser;
    }

    //mimic retreve from database of remote Url call varying version
    private User setSampleUser(int version){
        User sampleUser = new User();
        if(version==0) {
            sampleUser.setUserName("DIALLO");
            sampleUser.setUserPwd("Souleymane");
            sampleUser.setUserStatus(true);
        }else{
            sampleUser.setUserName("FADIGA");
            sampleUser.setUserPwd("Aminata");
            sampleUser.setUserStatus(true);
        }
        return sampleUser;
    }
}
