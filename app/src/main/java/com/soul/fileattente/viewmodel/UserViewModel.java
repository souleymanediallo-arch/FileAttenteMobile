package com.soul.fileattente.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.User;
import com.soul.fileattente.repository.FileAttenteRepository;
import com.soul.fileattente.repository.UserRepository;
import java.util.List;

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
    private FileAttenteRepository mFileAttenteRepository;

    //private MutableLiveData<List<Results>> mListResults = new MutableLiveData<>();
    public static MutableLiveData<List<NumeroSuivantFile>> mListResults = new MutableLiveData<>();

    public void initFromViewModel(){
        mUserRepo = UserRepository.getInstance();
        aGivenUser = mUserRepo.getSelectedUser(0);
        mUser.postValue(aGivenUser);
    }

    public void doAgainFromViewModel(){
            mUserRepo = UserRepository.getInstance();
            User sampleUser = mUserRepo.getSelectedUser(1);
            mUser.postValue(sampleUser);
    }

    public void getAllNumerosSuivantsFromViewModel(){
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.getAllNumerosSuivants();//Inside and because it's async, the postValue is done inside
    }

    public void authenticate(Login login){
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.authenticate(login);//Inside and because it's async, the postValue is done inside
    }

    public void getAllbirthdays(){
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.getAllbirthdays();//Inside and because it's async, the postValue is done inside
    }

    public void login(Login login){
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.login(login);//Inside and because it's async, the postValue is done inside
    }


    public  void demandeNumerosSuivant(DemandeNumeroFile demandeNumeroFile){
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.demandeNumerosSuivant(demandeNumeroFile);//Inside and because it's async, the postValue is done inside
    }

    //public LiveData<User> getmUser() { return mUser; }

    public MutableLiveData<User> getmUser() {
        return mUser;
    }

    public MutableLiveData<List<NumeroSuivantFile>> getmListResults() {
        return mListResults;
    }
}
