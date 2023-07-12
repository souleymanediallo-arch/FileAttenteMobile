package com.soul.fileattente.utils;

public interface ApplicationConstants {

    //Production
    public static final String serverURI = "tcp://https://51.91.9.235:1883"; //replace with your ip
    public static final String clientId = "android_client_fele_attente";
    public static final String publishTopic = "android_client_outbox";
    public static final String subscribeTopic = "android_client_inbox";

//    //Developpement
//    public static final String serverURI = "tcp://192.168.1.142:1883"; //replace with your ip
//    public static final String clientId = "android_client_fele_attente";
//    public static final String publishTopic = "android_client_outbox";
//    public static final String subscribeTopic = "android_client_inbox";

    //Other Protocole @IP and Port
    //public static final String serverURI = "tcp://192.168.1.142:61616"; //replace with your ip
}
