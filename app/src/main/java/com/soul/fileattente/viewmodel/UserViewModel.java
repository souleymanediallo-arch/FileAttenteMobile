package com.soul.fileattente.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//Write all Business Logic here and free the view from that
public class UserViewModel extends ViewModel {

    private MutableLiveData<String> userName;
    private MutableLiveData<String> userPwd;
    private MutableLiveData<Boolean> userStatus;

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<String> getUserPwd() {
        return userPwd;
    }

    public LiveData<Boolean> getUserStatus() {
        return userStatus;
    }
}
