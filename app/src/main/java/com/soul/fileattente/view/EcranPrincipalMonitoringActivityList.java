package com.soul.fileattente.view;

import static com.soul.fileattente.utils.ApplicationConstants.clientId;
import static com.soul.fileattente.utils.ApplicationConstants.publishTopic;
import static com.soul.fileattente.utils.ApplicationConstants.serverURI;
import static com.soul.fileattente.utils.ApplicationConstants.subscribeTopic;
import static com.soul.fileattente.utils.ApplicationConstants.STATUT_APPELE_SECRETAIRE;
import static com.soul.fileattente.utils.ApplicationConstants.STATUT_APPELE_MEDECIN;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.R;
import com.soul.fileattente.adapters.ServiceAGGListData;
import com.soul.fileattente.adapters.ServiceAGGMonitoringListDataAdapter;
import com.soul.fileattente.databinding.ActivityEcranPrincipalMonitoringListBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.utils.ApplicationConstants;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;

import com.somsakelect.android.mqtt.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EcranPrincipalMonitoringActivityList extends AppCompatActivity {

    public static UserViewModel userViewModel;
    private ActivityEcranPrincipalMonitoringListBinding binding;
    private ServiceAGGMonitoringListDataAdapter serviceAGGMonitoringListDataAdapter;
    private ArrayList<ServiceAGGListData> serviceAGGListData;
    private DemandeGeneric demandeGeneric;

    private TextToSpeech initializedTextToSpeechInstancefromCallingActivity;

    private MqttAndroidClient client;
    private static final int MQTT_QOS_2 = 2;
    private static final String TAG = "MQTT_EcranPrincipalMonitoringActivityList";
    private final MqttConnectOptions connectOptions = new MqttConnectOptions();
    String uniqueClientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEcranPrincipalMonitoringListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting GlobalSetOfExtra
        GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra) getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);
        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mEtablissement.toString());
        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalMonitoringActivityList.this).get(UserViewModel.class);
        demandeGeneric = new DemandeGeneric();
        demandeGeneric.setIdEtablissement(ApplicationConstants.IdEtablissementForThisMobileAPP); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setMonitorDeviceId(Utils.getUniqueId(this.getApplicationContext()));//Infomations à calculer
        userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
        //Process whenever there is a change
        processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged();
        //binding.recyclerView. - Managing the list of service List
        serviceAGGListData = new ArrayList<>();
        serviceAGGMonitoringListDataAdapter = new ServiceAGGMonitoringListDataAdapter(serviceAGGListData, mGlobalSetOfExtra, Utils.SCREEN_MONITOR);//
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceAGGMonitoringListDataAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);
        System.out.println("ActiveMQ-------------------------------------------------------------------------------------------------------------->");
        uniqueClientId = clientId + Utils.getUniqueId(this);
        initMqttOptions();
        //connect to mqtt queue and subscribe if connextion is successuful..
        connect();
        processWhenNumeroSuivantFileForAppelerNumeroChanged();
        processWhenNumeroSuivantFileForAnnulerAppelNumeroChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
        Log.d(TAG, "Activity resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectClient();
        Log.d(TAG, "Activity destroyed");
    }

    void processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged() {
        userViewModel.getListForDemandeAggregatAllServicesDestinationNumeroFiles().observe(this, new Observer<List<ServiceAGG>>() {
            @Override
            public void onChanged(List<ServiceAGG> serviceAGGs) {
                System.out.println("processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged Data Changed............................................" + serviceAGGs + "******");
                if (serviceAGGs != null) {
                    serviceAGGListData.clear();
                    for (ServiceAGG serviceAGG : serviceAGGs) {
                        System.out.println("------------> serviceAGG " + serviceAGG);
                        System.out.println("------------> serviceAGG.getNomServiceDestination() " + serviceAGG.getNomServiceDestination());
                        int imageId = Utils.getRihtImageIdGivenServiceName(serviceAGG.getNomServiceDestination());
                        serviceAGGListData.add(new ServiceAGGListData(serviceAGG, imageId));
                    }
                    serviceAGGMonitoringListDataAdapter.notifyDataSetChanged();
                    binding.progressBar.setVisibility(View.INVISIBLE);
                } else {
                    System.out.println("Enable to get data from the serveur check...if erreur....");
                }
            }
        });
    }

    private void initMqttOptions() {
        connectOptions.setAutomaticReconnect(true);
        connectOptions.setCleanSession(false);
        connectOptions.setKeepAliveInterval(10);
        connectOptions.setUserName("admin");
        connectOptions.setPassword("admin".toCharArray());
    }

    private void updateUI() {
        runOnUiThread(() -> {
            boolean isConnected = client != null && client.isConnected();
            binding.QueueConnectionStatus.setBackgroundColor(isConnected ? ContextCompat.getColor(getApplicationContext(), R.color.green_primary) : ContextCompat.getColor(getApplicationContext(), R.color.red));
        });
    }

    private MqttCallbackExtended createMqttCallback() {
        return new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.d(TAG, "Connection completed to: " + serverURI + ", reconnect: " + reconnect);
                updateUI();
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.w(TAG, "Connection lost: " + cause.getMessage());
                updateUI();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.d(TAG, "message Arrived with content : " + message.toString());
                userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "Message delivery completed with token: " + token.toString());
            }
        };
    }

    private void disconnectClient() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                updateUI();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        client = new MqttAndroidClient(this, serverURI, uniqueClientId);
        client.setCallback(createMqttCallback());
        try {
            //Veiller bien a utiliser cette methode ci-dessous connect(connectOptions, null, new IMqttActionListener() {..}
            // et surtout pas client.connect(connectOptions, new IMqttActionListener() {..}
            //C'est ce qui fait la difference afin de permettre au client de prendre en compte la variable "connectOptions" et permettre
            //ici la reconnection automatique configurée grace à connectOptions.setAutomaticReconnect(true);
            //Cela m a fait perdre beaucoup de temps et a amener à se poser des questions sur le vrai fonctionnement de MQTT
            client.connect(connectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "mqtt connection onSuccess: " + asyncActionToken.toString());
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable e) {
                    Log.e(TAG, "mqtt connection onFailure: " + e.getMessage());
                    updateUI();
                }
            });
        } catch (MqttException e) {
            Log.e(TAG, "connect - mqtt exception : " + e.getMessage());
            updateUI();
        }
    }

    private void subscribe() {
        try {
            client.subscribe(subscribeTopic, MQTT_QOS_2);
        } catch (MqttException e) {
            Log.e(TAG, "subscribe - mqtt exception : " + e.getMessage());
            updateUI();
        }
    }

    private void publishMessage(String message) {
        MqttMessage msg = new MqttMessage();
        msg.setPayload(message.getBytes());
        try {
            client.publish(publishTopic, msg);
            Log.d(TAG, "publishMessage - publish mqtt on publishTopic : " + publishTopic + " message : " + message);
        } catch (MqttException e) {
            Log.e(TAG, "publishMessage - mqtt exception : " + e.getMessage());
        }
    }

    private void print(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "publishMessage - publish mqtt on publishTopic : " + publishTopic + " message : " + message);
            }
        });
    }

    //------- Suivant & Annuler
    void processWhenNumeroSuivantFileForAppelerNumeroChanged() {
        userViewModel.getNumeroSuivantFileForAppelerNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                System.out.println("---------------------------------------------------------------------> getNumeroSuivantFileForAppelerNumero = " + "Sms envoyé pour le service [" + numeroSuivantFile.getNomServiceDestination() + "] au numero [" + numeroSuivantFile.getTelephoneDemandeur() + "]");

                System.out.println("---------------------------------------------------------------------> getNumeroSuivantFileForAppelerNumero = " + "Sms envoyé pour le service [" + numeroSuivantFile.getNomServiceDestination() + "] au numero [" + numeroSuivantFile.getTelephoneDemandeur() + "]");
                //if (numeroSuivantFile.getTempsAttenteEstime() == 500L) {
                    //if (numeroSuivantFile.getStatut().equalsIgnoreCase(STATUT_APPELE_SECRETAIRE)) {
                    if (numeroSuivantFile.getStatut().equalsIgnoreCase(STATUT_APPELE_SECRETAIRE) && numeroSuivantFile.getCalledByMonitor()==0) {
                        String messageAnnonce =
                                "Service " + numeroSuivantFile.getNomServiceDestination() + "\n" +
                                        "Numero " + Utils.formatNumeroDemandeurForTextToVoice(numeroSuivantFile.getNumeroDansFileAttente()) + "\n" +
                                        "Votre tour est arrivé à l'acceuil pour les formalités administratives";
                        String telephoneDemandeur = numeroSuivantFile.getTelephoneDemandeur();
                        initializedTextToSpeechInstance(messageAnnonce);
                        userViewModel.sendSmsNotification(numeroSuivantFile);
                        numeroSuivantFile.setCalledByMonitor(1);
                    }
                    //Utils.sendTextAsSms(telephoneDemandeur, messageAnnonce);
                    //numeroSuivantFile.setStatutNumSuivantFile(StatutNumSuivantFileEnum.Appele);
                    //
                    //Ici on recoit aussi la notif quand le patient est appele chez le medecin dont lire l invite chez le medecin, etant sur l'ecran de la secretaire
                    //if (numeroSuivantFile.getStatut().equalsIgnoreCase(STATUT_APPELE_MEDECIN)) {
                    if (numeroSuivantFile.getStatut().equalsIgnoreCase(STATUT_APPELE_MEDECIN) && numeroSuivantFile.getCalledByMedecin()==0) {
                        String messageAnnonce =
                                "Service " + numeroSuivantFile.getNomServiceDestination() + "\n" +
                                        "Numero " + Utils.formatNumeroDemandeurForTextToVoice(numeroSuivantFile.getNumeroDansFileAttente()) + "\n" +
                                        "Votre tour est arrivé chez le medecin";
                        initializedTextToSpeechInstance(messageAnnonce);
                        userViewModel.sendSmsNotification(numeroSuivantFile);
                        numeroSuivantFile.setCalledByMedecin(1);
                    }
                    //Ici on recoit aussi la notif quand le patient est appele chez le medecin dont lire l invite chez le medecin
                    //
                    System.out.println("numeroSuivantFile.getStatutNumSuivantFile() ----> " + numeroSuivantFile.getStatut());
                    //Cette ligne ci dessous pour que le bon message soit enoyé cote BACK (meme si en principe c'est deja le cas),
                    //Prevoir le traitement du retour de cet appel à l'afficher pour eventuellement alter sur les pbs d'envois de sms (technique, credit entre autres)
                    //numeroSuivantFile.setStatut("Appele");
                    //userViewModel.sendSmsNotification(numeroSuivantFile);
//                    numeroSuivantFile.setTempsAttenteEstime(0L);
//                }
            }
        });
    }

    void processWhenNumeroSuivantFileForAnnulerAppelNumeroChanged() {
        userViewModel.getNumeroSuivantFileForAnnulerAppelNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
            }
        });
    }

    void initializedTextToSpeechInstance(String textToRenderOverVoice) {
        initializedTextToSpeechInstancefromCallingActivity = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    initializedTextToSpeechInstancefromCallingActivity.setLanguage(Locale.FRANCE);
                    initializedTextToSpeechInstancefromCallingActivity.speak(textToRenderOverVoice, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}