package com.soul.fileattente.repository;

import androidx.lifecycle.MutableLiveData;

import com.soul.fileattente.model.User;

//Singleton Pattern
public class UserRepository {

    static UserRepository instance;
    User sampleUser = new User();

    public static UserRepository getInstance() {
        if(instance != null) {
            return instance;
        }else{
            return new UserRepository();
        }
    }

    //Pretend to get data from database of remote Url call
    public MutableLiveData<User> getSelectedUser(){
        setSampleUser();
        MutableLiveData data = new MutableLiveData();
        //data.setValue(sampleUser);
        data.postValue(sampleUser);
        return data;
    }

    public MutableLiveData<User> getSelectedUserAsMutableLiveData(int version){
        System.out.println("------------------------------------> 02");
        setSampleUser(version);
        MutableLiveData data = new MutableLiveData();
        //data.setValue(sampleUser);
        data.postValue(sampleUser);
        System.out.println(sampleUser.toString());
        //System.out.println("data.getValue().toString()------->" + data.toString());
        return data;
    }

    public User getSelectedUser(int version){
        System.out.println("------------------------------------> 02");
        setSampleUser(version);
        return sampleUser;
    }

    //mimic retreve from database of remote Url call
    private User setSampleUser(){
        //User sampleUser = new User();
        sampleUser.setUserName("DIALLO");
        sampleUser.setUserPwd("Souleymane");
        sampleUser.setUserStatus(true);
        return sampleUser;
    }

    //mimic retreve from database of remote Url call
//    private User setSampleUser(int version){
//        //User sampleUser = new User();
//        if(version==0) {
//            sampleUser.setUserName("DIALLO");
//            sampleUser.setUserPwd("Souleymane");
//            sampleUser.setUserStatus(true);
//        }else{
//            sampleUser.setUserName("FADIGA");
//            sampleUser.setUserPwd("Aminata");
//            sampleUser.setUserStatus(true);
//        }
//        return sampleUser;
//    }

    private void setSampleUser(int version){
        //User sampleUser = new User();
        if(version==0) {
            sampleUser.setUserName("DIALLO");
            sampleUser.setUserPwd("Souleymane");
            sampleUser.setUserStatus(true);
        }else{
            System.out.println("------------------------------------> 03");
            sampleUser.setUserName("FADIGA");
            sampleUser.setUserPwd("Aminata");
            sampleUser.setUserStatus(true);
        }
    }

//    //Pretend to get data from database of remote Url call
//    public MutableLiveData<User> getScondSelectedUser(){
//        setSecondSampleUser();
//        MutableLiveData data = new MutableLiveData();
//        data.setValue(sampleUser);
//        return data;
//    }
//
//    //mimic retreve from database of remote Url call
//    private User setSecondSampleUser(){
//        //User sampleUser = new User();
//        sampleUser.setUserName("FADIGA");
//        sampleUser.setUserPwd("Aminata");
//        sampleUser.setUserStatus(true);
//        return sampleUser;
//    }
}
