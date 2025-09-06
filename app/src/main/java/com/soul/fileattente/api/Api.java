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
    Call<Login> authenticate(@Body Login login);

    @POST("users/login")
    Call<Login> login(@Body Login login);

    @POST("etablissements/find-specific-etablissement")
    Call<Etablissement> demanderEtablissement(@Body DemandeGeneric demandeGeneric);

    @POST("services/find-all-services-for-specific-etablissement")
    Call<List<ServiceDestination>> demandeAllServicesDestination(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/find-service-queue-aggregation-for-secretaire")
    Call<List<ServiceAGG>> demandeAggregatAllServicesDestinationNumeroFiles(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/find-service-queue-aggregation-for-medecin")
    Call<List<ServiceAGG>> demandeMedecinAggregatAllServicesDestinationNumeroFiles(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/get-next-number-for-given-service")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeGeneric demandeGeneric);

    @GET("numero-suivant-files")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();

    @POST("demandeallnumerossuivants")
    Call<List<NumeroSuivantFile>> demandeAllNumerosSuivants(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/call-next-waiting-number-in-waiting-queue-secretaire")
    Call<NumeroSuivantFile> appeler_numero_suivant_files(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/call-next-waiting-number-in-waiting-queue-medecin")
    Call<NumeroSuivantFile> appeler_medecin_numero_suivant_files(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/cancel-call-waiting-queue-secretaire")
    Call<NumeroSuivantFile> annuler_numero_precedent_files(@Body DemandeGeneric demandeGeneric);

    @POST("waiting-queues/cancel-call-waiting-queue-medecin")
    Call<NumeroSuivantFile> annuler_medecin_numero_precedent_files(@Body DemandeGeneric demandeGeneric);

    //Les endpoints du cote Baack sont (notification/send-sms-mock, notification/send-sms-orange, notification/send-sms qui seletion le 1er ou le 2eme en fonction du mode de lancement du back "prod" ou non)
    //@POST("notification/send-sms")
    @POST("notification/send-sms-broker")
    Call<SmsMessageRetour> sendSmsNotification(@Body NumeroSuivantFile numeroSuivantFile);
}