package com.soul.fileattente.utils;

public interface ApplicationConstants {


    //General Params
    public static String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTg3NTM4NjAwMH0.YXb52s-3u0VtpV29undvxDlUFrdRVIMczf5G5JmVCkVq4m0p65cXW9g7iWExktWeiSW8VB3-KQhSAm4m94K0Ew"; //5 years
    public static final String userAgent = "FileAttenteMobileAPP";
    public static final long connectTimeoutDuration = 50;
    public static final long writeTimeoutDuration = 50;
    public static final long readTimeoutDuration = 50;
    public static final long tempsAttenteAvantRetourListServices = 5*1000;
    public static final String IdEtablissementForThisMobileAPP = "674999cad5d72103924e0d46";
    //Ce sont les memes definitions de statut qye cote BAck
    public static final String STATUT_NON_DEFINI ="Non_Defini";
    public static final String STATUT_ATTENTE ="Attente";
    public static final String STATUT_APPELE_SECRETAIRE ="Appele_Secretaire";
    public static final String STATUT_APPELE_MEDECIN ="Appele_Medecin";
    public static final String STATUT_ERROR ="Error";
    public static final String GLOBAL_PREFERENCE_KEY_ID_ETABLISSEMENT = "global_preference_key_id_etablissement";
    //

//    //Developpement (192.168.1.142 -> localhost), cela dit, il ne faut surtout pas remplacer par localhost qui renvoie au devide android lui meme
//    public static final String serverURI = "tcp://192.168.1.142:1889"; //replace with your ip with initial Port: 1883
//    public static final String clientId = "android_client_file_attente";
//    public static final String publishTopic = "android_client_outbox";
//    public static final String subscribeTopic = "android_client_inbox";
//    public static final String BASE_URL = "http://192.168.1.142:8089/api/"; //Replace with your Port 8080

    //Production-Test (37.187.90.58 -> serveur en ligne), mais sur une instance de test
    public static final String serverURI = "tcp://37.187.90.58:1889"; //replace with your ip
    public static final String clientId = "android_client_file_attente";
    public static final String publishTopic = "android_client_outbox";
    public static final String subscribeTopic = "android_client_inbox";
    public static final String BASE_URL = "http://37.187.90.58:8089/api/";
//
//    //Production (37.187.90.58 -> serveur en ligne), sur la vraie instance de Production
//    public static final String serverURI = "tcp://37.187.90.58:1883"; //replace with your ip
//    public static final String clientId = "android_client_file_attente";
//    public static final String publishTopic = "android_client_outbox";
//    public static final String subscribeTopic = "android_client_inbox";
//    public static final String BASE_URL = "http://37.187.90.58:8080/api/";
}
