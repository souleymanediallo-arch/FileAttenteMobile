package com.soul.fileattente.viewmodel;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soul.fileattente.model.User;
import com.soul.fileattente.repository.UserRepository;

//Write all Business Logic here and free the view from that
public class UserViewModel extends ViewModel {

    private MutableLiveData<User> mUser;
    private UserRepository mUserRepo;


    public void init(){
        if(mUser != null){
            return;
        }else {
            mUserRepo = UserRepository.getInstance();
            mUser = mUserRepo.getSelectedUserAsMutableLiveData(0);
        }
    }

    @MainThread
    public void doAgain(){
//        if(mUser != null){
//            System.out.println("------------------------------------> 01");
//            return;
//        }else {
            System.out.println("------------------------------------> 01");
            mUserRepo = UserRepository.getInstance();
            //mUser = mUserRepo.getSelectedUserAsMutableLiveData(1);
            User sampleUser = mUserRepo.getSelectedUser(1);
            //mUser.setValue(sampleUser);
            mUser.postValue(sampleUser);
//        }
    }

    public LiveData<User> getmUser() {
        return mUser;
    }
}
