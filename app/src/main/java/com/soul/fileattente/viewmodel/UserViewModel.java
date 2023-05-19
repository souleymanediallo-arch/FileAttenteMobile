package com.soul.fileattente.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceDestination;
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

    private FileAttenteRepository mFileAttenteRepository;

    private static MutableLiveData<AutheticationResult> autheticationResultForAuthenticate = new MutableLiveData<>();
    private static MutableLiveData<LoginResult> loginResultForLogin = new MutableLiveData<>();
    private static MutableLiveData<List<Param>> listParamForDemandeAllParams = new MutableLiveData<>();
    private static MutableLiveData<List<ServiceDestination>> listServiceDestinationForDemandeAllServicesDestination = new MutableLiveData<>();
    private static MutableLiveData<NumeroSuivantFile> numeroSuivantFileForDemandeNumerosSuivant = new MutableLiveData<>();
    private static MutableLiveData<List<NumeroSuivantFile>> listNumeroSuivantFileForGetAllNumerosSuivants = new MutableLiveData<>();
    private static MutableLiveData<NumeroSuivantFile> numeroSuivantFileForAppelerNumero = new MutableLiveData<>();
    private static MutableLiveData<NumeroSuivantFile> numeroSuivantFileForAnnulerAppelNumero = new MutableLiveData<>();
    private static MutableLiveData<List<NumeroSuivantFile>> listNumeroSuivantFileFordemandeAllNumerosSuivants = new MutableLiveData<>();


    public void getAllNumerosSuivants() {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.getAllNumerosSuivants();//Inside and because it's async, the postValue is done inside
    }

    public void authenticate(Login login) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.authenticate(login);//Inside and because it's async, the postValue is done inside
    }

    public void getAllbirthdays() {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.getAllbirthdays();//Inside and because it's async, the postValue is done inside
    }

    public void login(Login login) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.login(login);//Inside and because it's async, the postValue is done inside
    }


    public void demandeNumerosSuivant(DemandeNumeroFile demandeNumeroFile) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.demandeNumerosSuivant(demandeNumeroFile);//Inside and because it's async, the postValue is done inside
    }

    public void demandeAllParams(DemandeParam demandeParam) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.demandeAllParams(demandeParam);//Inside and because it's async, the postValue is done inside
    }

    public void demandeAllServicesDestination(DemandeService demandeService) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.demandeAllServicesDestination(demandeService);//Inside and because it's async, the postValue is done inside
    }

    public void appelerNumero(NumeroSuivantFile numeroSuivantFile) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.appelerNumero(numeroSuivantFile);//Inside and because it's async, the postValue is done inside
    }

    public void annulerAppelNumero(NumeroSuivantFile numeroSuivantFile) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.annulerAppelNumero(numeroSuivantFile);//Inside and because it's async, the postValue is done inside
    }


    public void demandeAllNumerosSuivants(DemandeNumSuiv demandeNumSuiv) {
        mFileAttenteRepository = FileAttenteRepository.getInstance();
        mFileAttenteRepository.demandeAllNumerosSuivants(demandeNumSuiv);//Inside and because it's async, the postValue is done inside
    }

    public static MutableLiveData<AutheticationResult> getAutheticationResultForAuthenticate() {
        return autheticationResultForAuthenticate;
    }

    public static MutableLiveData<LoginResult> getLoginResultForLogin() {
        return loginResultForLogin;
    }

    public static MutableLiveData<List<Param>> getListParamForDemandeAllParams() {
        return listParamForDemandeAllParams;
    }

    public static MutableLiveData<List<ServiceDestination>> getListServiceDestinationForDemandeAllServicesDestination() {
        return listServiceDestinationForDemandeAllServicesDestination;
    }

    public static MutableLiveData<NumeroSuivantFile> getNumeroSuivantFileForDemandeNumerosSuivant() {
        return numeroSuivantFileForDemandeNumerosSuivant;
    }

    public static MutableLiveData<List<NumeroSuivantFile>> getListNumeroSuivantFileForGetAllNumerosSuivants() {
        return listNumeroSuivantFileForGetAllNumerosSuivants;
    }

    public static MutableLiveData<NumeroSuivantFile> getNumeroSuivantFileForAppelerNumero() {
        return numeroSuivantFileForAppelerNumero;
    }

    public static MutableLiveData<NumeroSuivantFile> getNumeroSuivantFileForAnnulerAppelNumero() {
        return numeroSuivantFileForAnnulerAppelNumero;
    }

    public static MutableLiveData<List<NumeroSuivantFile>> getListNumeroSuivantFileFordemandeAllNumerosSuivants() {
        return listNumeroSuivantFileFordemandeAllNumerosSuivants;
    }
}
