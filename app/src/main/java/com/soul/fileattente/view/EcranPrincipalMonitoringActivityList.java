package com.soul.fileattente.view;

import static com.soul.fileattente.utils.ApplicationConstants.clientId;
import static com.soul.fileattente.utils.ApplicationConstants.publishTopic;
import static com.soul.fileattente.utils.ApplicationConstants.serverURI;
import static com.soul.fileattente.utils.ApplicationConstants.subscribeTopic;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.adapters.ServiceAGGListData;
import com.soul.fileattente.adapters.ServiceAGGMonitoringListDataAdapter;
import com.soul.fileattente.databinding.ActivityEcranPrincipalMonitoringListBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
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

    MqttAndroidClient client;
    DemandeGeneric demandeGeneric;

    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;

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
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());

        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalMonitoringActivityList.this).get(UserViewModel.class);

        demandeGeneric = new DemandeGeneric();
        demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setDeviceId("000000000000");//Infomations à calculer
        userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);

        //Process whenever there is a change
        processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged();

        //binding.recyclerView. - Managing the list of service List
        serviceAGGListData = new ArrayList<>();
        serviceAGGMonitoringListDataAdapter = new ServiceAGGMonitoringListDataAdapter(serviceAGGListData, mGlobalSetOfExtra);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceAGGMonitoringListDataAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);

        System.out.println("ActiveMQ-------------------------------------------------------------------------------------------------------------->");
        connect(); // it will connext and subscribe if connextion is uccessfuk..

        processWhenNumeroSuivantFileForAppelerNumeroChanged();
        processWhenNumeroSuivantFileForAnnulerAppelNumeroChanged();
    }

    void processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged() {
        userViewModel.getListForDemandeAggregatAllServicesDestinationNumeroFiles().observe(this, new Observer<List<ServiceAGG>>() {
            @Override
            public void onChanged(List<ServiceAGG> serviceAGGs) {
                System.out.println("processWhenListForDemandeAggregatAllServicesDestinationNumeroFilesChanged Data Changed............................................" + serviceAGGs + "******");

                if (serviceAGGs != null) {
                    serviceAGGListData.clear();
                    for (ServiceAGG serviceAGG : serviceAGGs) {
                        int imageId = Utils.getRihtImageIdGivenServiceName(serviceAGG.getNomService());
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

    //Implementing The activeMQ Part
    private void connect() {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);

        client = new MqttAndroidClient(this, serverURI, clientId);
        try {
            client.connect(connectOptions, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
//                    System.out.println("client ------------------------------------------------> " + client.toString());
//                    if(client == null){
//                        System.out.println("client null------------------------------------------------> ");
//                    }else{
//                        System.out.println("client NOT null------------------------------------------------> ");
//                        //int count = client.getBufferedMessage(0);
//                        MqttMessage current_message = client.getBufferedMessage(0);
//                        System.out.println(current_message.toString());
//                    }
//                    int count = client.getBufferedMessageCount();
//                    System.out.println("client.getBufferedMessageCount() ------------------> " + count);
//                    MqttMessage current_message;
//                    for(int i=0; i<count; i++){
//                        current_message = client.getBufferedMessage(i);
//                        System.out.println("client.getBufferedMessage("+i+")------------------> " + current_message.toString());
//                    }
                    //----
                    //buttonSend.setEnabled(true);
                    subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable e) {
                    e.printStackTrace();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe() {
        try {

            client.subscribe(subscribeTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(final String topic, final MqttMessage message) throws Exception {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(EcranPrincipalMonitoringActivityList.this, message.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println("subscribe Incoming Message --------------------------------------------------------------------->" + message.toString());
                            //print(message.toString());
//                            DemandeGeneric demandeGeneric = new DemandeGeneric();
//                            demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer
//                            demandeGeneric.setDeviceId("000000000000");//Infomations à calculer
                            userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                        }
                    });
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

//        int count = client.getBufferedMessageCount();
//        System.out.println("client.getBufferedMessageCount() ------------------> " + count);
//        MqttMessage current_message;
//        for(int i=0; i<count; i++){
//            current_message = client.getBufferedMessage(i);
//            System.out.println("client.getBufferedMessage("+i+")------------------> " + current_message.toString());
//        }
    }

    private void publishMessage(String message) {
        MqttMessage msg = new MqttMessage();
        msg.setPayload(message.getBytes());
        try {
            client.publish(publishTopic, msg);
            System.out.println("publishMessage Outgoing Message --------------------------------------------------------------------->" + message);
            print(message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void print(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //textResult.setText(textResult.getText().toString() + "\n" + message);
            }
        });
    }

    //------- Suivant & Annuler
    void processWhenNumeroSuivantFileForAppelerNumeroChanged() {
        userViewModel.getNumeroSuivantFileForAppelerNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                System.out.println("---------------------------------------------------------------------> getNumeroSuivantFileForAppelerNumero = " + "Sms envoyé pour le service [" + numeroSuivantFile.getNomService() + "] au numero [" + numeroSuivantFile.getTelephoneDemandeur() + "]");
                String messageAnnonce =
                        "Service " + numeroSuivantFile.getNomService() + "\n" +
                                "Numero " + Utils.formatNumeroDemandeurForTextToVoice(numeroSuivantFile.getNumeroSuivant()) + "\n" +
                                "Votre tour est arrivé";
                String telephoneDemandeur = numeroSuivantFile.getTelephoneDemandeur();
                initializedTextToSpeechInstance(messageAnnonce);
                Utils.sendTextAsSms(telephoneDemandeur, messageAnnonce);
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