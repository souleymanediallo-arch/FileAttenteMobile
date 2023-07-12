package com.soul.fileattente.utils;

public interface ApplicationConstants {

    //Production
    public static final String serverURI = "tcp://https://51.91.9.235:1883"; //replace with your ip
    public static final String clientId = "android_client_fele_attente";
    public static final String publishTopic = "android_client_outbox";
    public static final String subscribeTopic = "android_client_inbox";

    public static final String BASE_URL = "http://51.91.9.235:8080/api/";

    public static final long connectTimeoutDuration = 50;
    public static final long writeTimeoutDuration = 50;
    public static final long readTimeoutDuration = 50;


//    //Developpement
//    public static final String serverURI = "tcp://192.168.1.142:1883"; //replace with your ip
//    public static final String clientId = "android_client_fele_attente";
//    public static final String publishTopic = "android_client_outbox";
//    public static final String subscribeTopic = "android_client_inbox";

//    public static final String BASE_URL = "http://192.168.1.142:8080/api/";

    //Other Protocole @IP and Port
    //public static final String serverURI = "tcp://192.168.1.142:61616"; //replace with your ip
}
