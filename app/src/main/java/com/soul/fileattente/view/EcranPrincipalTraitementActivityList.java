package com.soul.fileattente.view;

import static com.soul.fileattente.utils.ApplicationConstants.clientId;
import static com.soul.fileattente.utils.ApplicationConstants.publishTopic;
import static com.soul.fileattente.utils.ApplicationConstants.serverURI;
import static com.soul.fileattente.utils.ApplicationConstants.subscribeTopic;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import java.util.stream.Collectors;

public class EcranPrincipalTraitementActivityList extends AppCompatActivity {

    public static UserViewModel userViewModel;
    private ActivityEcranPrincipalMonitoringListBinding binding;
    private ServiceAGGMonitoringListDataAdapter serviceAGGMonitoringListDataAdapter;
    private ArrayList<ServiceAGGListData> serviceAGGListData;

    MqttAndroidClient client;
    DemandeGeneric demandeGeneric;

    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;
    String nomServiceDestinationChoisi=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEcranPrincipalMonitoringListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting GlobalSetOfExtra
        System.out.println("---------------------------->EcranPrincipalTraitementActivityList<----------------------------");
        GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra) getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);
        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.getProfil());
        if(mGlobalSetOfExtra.mServiceDestination != null) {
            nomServiceDestinationChoisi = mGlobalSetOfExtra.mServiceDestination.getNomServiceDestination();
        }
        System.out.println("------------> " + nomServiceDestinationChoisi);
        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mEtablissement.toString());
        System.out.println("---------------------------->EcranPrincipalTraitementActivityList<----------------------------");
        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalTraitementActivityList.this).get(UserViewModel.class);
        demandeGeneric = new DemandeGeneric();
        demandeGeneric.setIdEtablissement("672f994ae434e738150a1cc1"); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setMedecinDeviceId(Utils.getUniqueId(this.getApplicationContext()));//Infomations à calculer
        userViewModel.demandeMedecinAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
        //Process whenever there is a change
        processWhenListForDemandeMedecinAggregatAllServicesDestinationNumeroFilesChanged();
        //binding.recyclerView. - Managing the list of service List
        serviceAGGListData = new ArrayList<>();
        serviceAGGMonitoringListDataAdapter = new ServiceAGGMonitoringListDataAdapter(serviceAGGListData, mGlobalSetOfExtra, Utils.SCREEN_MEDECIN);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceAGGMonitoringListDataAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);
        System.out.println("ActiveMQ-------------------------------------------------------------------------------------------------------------->");
        connect(); // it will connect and subscribe if connextion is successuful..
        processWhenNumeroSuivantFileForMedecinAppelerNumeroChanged ();
        processWhenNumeroSuivantFileForMedecinAnnulerAppelNumeroChanged();
    }

    void processWhenListForDemandeMedecinAggregatAllServicesDestinationNumeroFilesChanged() {
        userViewModel.getListForDemandeMedecinAggregatAllServicesDestinationNumeroFiles().observe(this, new Observer<List<ServiceAGG>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<ServiceAGG> serviceAGGs) {
                System.out.println("processWhenListForDemandeMedecinAggregatAllServicesDestinationNumeroFilesChanged Data Changed............................................" + serviceAGGs + "******");
                if (serviceAGGs != null) {
                    serviceAGGListData.clear();
                    if(nomServiceDestinationChoisi != null) {
                        serviceAGGs = serviceAGGs.stream().filter(s -> s.getNomServiceDestination().equalsIgnoreCase(nomServiceDestinationChoisi)).collect(Collectors.toList());
                    }
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

    private void connect() {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);
        System.out.println("Utils.getUniqueId ----------------------------------> " + clientId + Utils.getUniqueId(this.getApplicationContext()));
        client = new MqttAndroidClient(this, serverURI, clientId + Utils.getUniqueId(this.getApplicationContext()));
        try {
            client.connect(connectOptions, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
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
                            Toast.makeText(EcranPrincipalTraitementActivityList.this, message.toString(), Toast.LENGTH_SHORT).show();
                            System.out.println("subscribe Incoming Message EcranPrincipalTraitementActivityList --------------------------------------------------------------------->" + message.toString());
                            //print(message.toString());
                            //userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                            userViewModel.demandeMedecinAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                        }
                    });
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void publishMessage(String message) {
        MqttMessage msg = new MqttMessage();
        msg.setPayload(message.getBytes());
        try {
            client.publish(publishTopic, msg);
            System.out.println("publishMessage Outgoing Message --------------------------------------------------------------------->" + message);
            //print(message);
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
    void processWhenNumeroSuivantFileForMedecinAppelerNumeroChanged() {
        userViewModel.getNumeroSuivantFileForMedecinAppelerNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                userViewModel.demandeMedecinAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                System.out.println("---------------------------------------------------------------------> getNumeroSuivantFileForAppelerNumero = " + "Sms envoyé pour le service [" + numeroSuivantFile.getNomServiceDestination() + "] au numero [" + numeroSuivantFile.getTelephoneDemandeur() + "]");
                String messageAnnonce =
                        "Service " + numeroSuivantFile.getNomServiceDestination() + "\n" +
                        "Numero " + Utils.formatNumeroDemandeurForTextToVoice(numeroSuivantFile.getNumeroDansFileAttente()) + "\n" +
                        "Votre tour est arrivé";
                String telephoneDemandeur = numeroSuivantFile.getTelephoneDemandeur();
                initializedTextToSpeechInstance(messageAnnonce);
                //Utils.sendTextAsSms(telephoneDemandeur, messageAnnonce);
                //numeroSuivantFile.setStatutNumSuivantFile(StatutNumSuivantFileEnum.Appele);
                System.out.println("numeroSuivantFile.getStatutNumSuivantFile() ----> " + numeroSuivantFile.getStatut());
                //Cette ligne ci dessous pour que le bon message soit enoyé cote BACK (meme si en principe c'est deja le cas),
                //Prevoir le traitement du retour de cet appel à l'afficher pour eventuellement alter sur les pbs d'envois de sms (technique, credit entre autres)
                //numeroSuivantFile.setStatut("Appele");
                userViewModel.sendSmsNotification(numeroSuivantFile);
            }
        });
    }

    void processWhenNumeroSuivantFileForMedecinAnnulerAppelNumeroChanged() {
        userViewModel.getNumeroSuivantFileForMedecinAnnulerAppelNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                userViewModel.demandeMedecinAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
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