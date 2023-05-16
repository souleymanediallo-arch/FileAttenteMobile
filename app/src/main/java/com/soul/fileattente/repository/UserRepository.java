package com.soul.fileattente.repository;

import android.os.Handler;

import com.soul.fileattente.model.User;


//Manage to get always one single instance of the
//repository thanks to  Singleton Pattern
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
        User sampleUser = new User("DIALLO", "Souleymane", true);
        return sampleUser;
    }

    //mimic retreve from database of remote Url call varying version
    private User setSampleUser(int version){

        User sampleUser = new User();
        if(version==0) {
            sampleUser = new User("DIALLO", "Souleymane", true);
        }else{
            sampleUser = new User("FADIGA", "Aminata", true);
        }
        return sampleUser;
    }

    private void waitForAWhile(long delay){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("............................. I am done with the delay.......................");
            }
        }, delay);
    }
}
