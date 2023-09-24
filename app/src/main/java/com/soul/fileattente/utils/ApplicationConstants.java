package com.soul.fileattente.utils;

public interface ApplicationConstants {

    //General Params
    public static String gottenTokenAfterLoginOrRefresh = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTcxODIyNDkyNX0.sIxkhH9yAAExHg1CVc1pNB4EuviMExwHD8tRATFkTM_YI96s31qLsWu24lEWEIEHWdV5v2LiZAWy9q5oLeL2_g";
    public static final String userAgent = "FileAttenteMobileAPP";

    public static final long connectTimeoutDuration = 50;
    public static final long writeTimeoutDuration = 50;
    public static final long readTimeoutDuration = 50;

    public static final long tempsAttenteAvantRetourListServices = 5*1000;

    //Production
    //public static final String serverURI = "tcp://51.91.9.235:1883"; //replace with your ip //Ancien serveu
    public static final String serverURI = "tcp://92.222.164.174:1883"; //replace with your ip
    public static final String clientId = "android_client_fele_attente";
    public static final String publishTopic = "android_client_outbox";
    public static final String subscribeTopic = "android_client_inbox";
    //public static final String BASE_URL = "http://51.91.9.235:8080/api/"; //Ancien serveur
    public static final String BASE_URL = "http://92.222.164.174:8080/api/";

//    //Developpement
//    public static final String serverURI = "tcp://192.168.1.142:1883"; //replace with your ip
//    public static final String clientId = "android_client_fele_attente";
//    public static final String publishTopic = "android_client_outbox";
//    public static final String subscribeTopic = "android_client_inbox";
//    public static final String BASE_URL = "http://192.168.1.142:8080/api/";

    //Other Protocole @IP and Port
    //public static final String serverURI = "tcp://192.168.1.142:61616"; //replace with your ip
}
