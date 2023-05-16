package com.soul.fileattente.viewmodel;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soul.fileattente.model.User;
import com.soul.fileattente.repository.UserRepository;

//Write all Business Logic here and free the view from that
//Veiller à utiliser les MutableLiveData et liveData uniquement dans la ViewModel et surtout pas
//Permettre à d'autres classe de renvoyer des MutableLive Data or des Live Data
//D'aielleurs cela permet de ne pas denaturer ces Classes qui au demeurant ne savent rien des LiveData
//Et devraient pouvoir etre utilisées à d'autres endroits
public class UserViewModel extends ViewModel {

    private User aGivenUser;
    private MutableLiveData<User> mUser = new MutableLiveData<>();
    private UserRepository mUserRepo;

    public void init(){
        mUserRepo = UserRepository.getInstance();
        aGivenUser = mUserRepo.getSelectedUser(0);
        mUser.postValue(aGivenUser);
    }

    @MainThread
    public void doAgain(){
            mUserRepo = UserRepository.getInstance();
            User sampleUser = mUserRepo.getSelectedUser(1);
            mUser.postValue(sampleUser);
    }

    public LiveData<User> getmUser() {
        return mUser;
    }
}
