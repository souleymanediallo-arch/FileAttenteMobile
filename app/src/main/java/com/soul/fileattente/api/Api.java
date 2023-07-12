package com.soul.fileattente.api;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Birthday;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.model.ServiceDestination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("birthdays") //A des fins de tests sur le BackEnd local qui n avait des chsoess que sur les Birthday
    Call<List<Birthday>> getAllbirthdays();

    @POST("authenticate")
    Call<AutheticationResult> authenticate(@Body Login login);

    @POST("login")
    Call<LoginResult> login(@Body Login login);

    @POST("demandeallparams")
    Call<List<Param>> demandeAllParams(@Body DemandeGeneric demandeGeneric);

    @POST("demandeallservicesdestination")
    Call<List<ServiceDestination>> demandeAllServicesDestination(@Body DemandeGeneric demandeGeneric);

//    @POST("demandeAggregatAllServicesDestinationNumeroFiles")
    @POST("nbtotaldemandeursenattenteparservicepourunjourdonne")
    Call<List<ServiceAGG>> demandeAggregatAllServicesDestinationNumeroFiles(@Body DemandeGeneric demandeGeneric);

    //@POST("demandenumerossuivant")
    @POST("demander-numero-suivant-files")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeNumeroFile demandeNumeroFile);

//    @GET("allnumerossuivants")
    @GET("numero-suivant-files")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();

    //@POST("appelernumero")
    //@POST("appeler-numero-suivant-files/10")
    //Call<NumeroSuivantFile> appelerNumero(@Body NumeroSuivantFile numeroSuivantFile);
    //@POST("appelernumero")
//    @GET("appeler-numero-suivant-files/{idNumeroSuivantFile}")
//    Call<NumeroSuivantFile> appelerNumero(@Path("idNumeroSuivantFile") Long idNumeroSuivantFile);
//
//    @POST("annulerappelnumero")
//    Call<NumeroSuivantFile> annulerAppelNumero(@Body NumeroSuivantFile numeroDejaAppele);

    @POST("demandeallnumerossuivants")
    Call<List<NumeroSuivantFile>> demandeAllNumerosSuivants(@Body DemandeGeneric demandeGeneric);

    //------
    @POST("appeler-numero-suivant-files")
    Call<NumeroSuivantFile> appeler_numero_suivant_files(@Body DemandeGeneric demandeGeneric);

    @POST("annuler-numero-precedent-files")
    Call<NumeroSuivantFile> annuler_numero_precedent_files(@Body DemandeGeneric demandeGeneric);
    //------


}