package com.soul.fileattente.api;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.DemandeNumeroFile;
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

public interface Api {

    @POST("authenticate")
    Call<AutheticationResult> authenticate(@Body Login login);

    @POST("login")
    Call<LoginResult> login(@Body Login login);

    @POST("demandeallparams")
    Call<List<Param>> demandeAllParams(@Body DemandeGeneric demandeGeneric);

    @POST("demandeallservicesdestination")
    Call<List<ServiceDestination>> demandeAllServicesDestination(@Body DemandeGeneric demandeGeneric);

    @POST("nbtotaldemandeursenattenteparservicepourunjourdonne")
    Call<List<ServiceAGG>> demandeAggregatAllServicesDestinationNumeroFiles(@Body DemandeGeneric demandeGeneric);

    @POST("demander-numero-suivant-files")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeNumeroFile demandeNumeroFile);

    @GET("numero-suivant-files")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();

    @POST("demandeallnumerossuivants")
    Call<List<NumeroSuivantFile>> demandeAllNumerosSuivants(@Body DemandeGeneric demandeGeneric);

    @POST("appeler-numero-suivant-files")
    Call<NumeroSuivantFile> appeler_numero_suivant_files(@Body DemandeGeneric demandeGeneric);

    @POST("annuler-numero-precedent-files")
    Call<NumeroSuivantFile> annuler_numero_precedent_files(@Body DemandeGeneric demandeGeneric);
}