package com.soul.fileattente.api;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Birthday;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceDestination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    //{{BaseUrl}}/api/params-etablissements
    String BASE_URL = "http://192.168.1.142:8080/api/";
    //String BASE_URL = "https://18c8ff90-b4d1-4f45-be00-9cb07599f97c.mock.pstmn.io/fileattente/";
    //String BASE_URL = "http://192.168.1.142:8080/api/";

    @GET("birthdays") //A des fins de tests sur le BackEnd local qui n avait des chsoess que sur les Birthday
    Call<List<Birthday>> getAllbirthdays();

    @POST("authenticate")
    Call<AutheticationResult> authenticate(@Body Login login);

    @POST("login")
    Call<LoginResult> login(@Body Login login);

    @POST("demandeallparams")
    Call<List<Param>> demandeAllParams(@Body DemandeParam demandeParam);

    @POST("demandeallservicesdestination")
    Call<List<ServiceDestination>> demandeAllServicesDestination(@Body DemandeService demandeService);

    //@POST("demandenumerossuivant")
    @POST("demander-numero-suivant-files")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeNumeroFile demandeNumeroFile);

    @GET("allnumerossuivants")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();

    //@POST("appelernumero")
    //@POST("appeler-numero-suivant-files/10")
    //Call<NumeroSuivantFile> appelerNumero(@Body NumeroSuivantFile numeroSuivantFile);
    //@POST("appelernumero")
    @GET("appeler-numero-suivant-files/{idNumeroSuivantFile}")
    Call<NumeroSuivantFile> appelerNumero(@Path("idNumeroSuivantFile") Long idNumeroSuivantFile);

    @POST("annulerappelnumero")
    Call<NumeroSuivantFile> annulerAppelNumero(@Body NumeroSuivantFile numeroDejaAppele);

    @POST("demandeallnumerossuivants")
    Call<List<NumeroSuivantFile>> demandeAllNumerosSuivants(@Body DemandeNumSuiv demandeNumSuiv);

}