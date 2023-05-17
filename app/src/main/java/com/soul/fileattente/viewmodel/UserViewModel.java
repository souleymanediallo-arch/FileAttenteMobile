package com.soul.fileattente.viewmodel;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.model.Results;
import com.soul.fileattente.model.User;
import com.soul.fileattente.repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Write all Business Logic here and free the view from that
//Veiller à utiliser les MutableLiveData et liveData uniquement dans la ViewModel et surtout pas
//Permettre à d'autres classe de renvoyer des MutableLive Data or des Live Data
//D'aielleurs cela permet de ne pas denaturer ces Classes qui au demeurant ne savent rien des LiveData
//Et devraient pouvoir etre utilisées à d'autres endroits.
//Par ailleurs c'est qui empechait l'application de fonctionner correctement des le debut
public class UserViewModel extends ViewModel {

    private User aGivenUser;
    private MutableLiveData<User> mUser = new MutableLiveData<>();
    private UserRepository mUserRepo;

    private MutableLiveData<List<Results>> mListResults = new MutableLiveData<>();

    public void init(){
        mUserRepo = UserRepository.getInstance();
        aGivenUser = mUserRepo.getSelectedUser(0);
        mUser.postValue(aGivenUser);
    }

    public void doAgain(){
            mUserRepo = UserRepository.getInstance();
            User sampleUser = mUserRepo.getSelectedUser(1);
            mUser.postValue(sampleUser);
    }


//    public LiveData<User> getmUser() {
//        return mUser;
//    }

    public MutableLiveData<User> getmUser() {
        return mUser;
    }

    public MutableLiveData<List<Results>> getmListResults() {
        return mListResults;
    }

    private void getSuperHeroes() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                mListResults.postValue(myheroList);
            }
            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
            }
        });
    }
}
