package com.soul.fileattente.api;

import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.Etablissement;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.model.SmsMessageRetour;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @POST("users/authenticate")
    //Call<AutheticationResult> authenticate(@Body Login login);
    Call<Login> authenticate(@Body Login login);

    @POST("users/login")
    //Call<LoginResult> login(@Body Login login);
    Call<Login> login(@Body Login login);

    //@POST("demandeallparams")
    //Call<List<Param>> demandeAllParams(@Body DemandeGeneric demandeGeneric);
    //http://192.168.1.142:8080/api/etablissements/demanderetablissement

    //@POST("etablissements/demanderetablissement")
    @POST("etablissements/find-specific-etablissement")
    Call<Etablissement> demanderEtablissement(@Body DemandeGeneric demandeGeneric);

    //@POST("demandeallservicesdestination")
    @POST("services/find-all-services-for-specific-etablissement")
    Call<List<ServiceDestination>> demandeAllServicesDestination(@Body DemandeGeneric demandeGeneric);

    //@POST("nbtotaldemandeursenattenteparservicepourunjourdonne")
    @POST("waiting-queues/find-service-queue-aggregation-for-secretaire")
    Call<List<ServiceAGG>> demandeAggregatAllServicesDestinationNumeroFiles(@Body DemandeGeneric demandeGeneric);

    //@POST("demander-numero-suivant-files")
    @POST("waiting-queues/get-next-number-for-given-service")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeGeneric demandeGeneric);
    //Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeNumeroFile demandeNumeroFile);

    @GET("numero-suivant-files")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();

    @POST("demandeallnumerossuivants")
    Call<List<NumeroSuivantFile>> demandeAllNumerosSuivants(@Body DemandeGeneric demandeGeneric);

    //@POST("appeler-numero-suivant-files")
    @POST("waiting-queues/call-next-waiting-number-in-waiting-queue-secretaire")
    Call<NumeroSuivantFile> appeler_numero_suivant_files(@Body DemandeGeneric demandeGeneric);

    //@POST("annuler-numero-precedent-files")
    @POST("waiting-queues/cancel-call-waiting-queue-secretaire")
    Call<NumeroSuivantFile> annuler_numero_precedent_files(@Body DemandeGeneric demandeGeneric);

    @POST("notification/send-sms")
    Call<SmsMessageRetour> sendSmsNotification(@Body NumeroSuivantFile numeroSuivantFile);
}