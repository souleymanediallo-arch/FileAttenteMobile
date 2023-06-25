package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityEcranPrincipalBinding;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.Locale;

public class EcranPrincipalActivity extends AppCompatActivity {

    Button btnGenNumero;
    TextView txtGenNumeroLabel;
    TextView txtGenNumero;
    Button btnSms;
    EditText editTextPhone;
    String strValueGenNumero;

    ActivityEcranPrincipalBinding binding;
    GlobalSetOfExtra mGlobalSetOfExtra;

    private UserViewModel userViewModel;
    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ecran_principal);

        //Using ViewBinding to manage Layout Components
        binding = ActivityEcranPrincipalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting the Business part
        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalActivity.this).get(UserViewModel.class);
        //Getting selected selectedServiceDestination
        Intent intent = getIntent();
        //Getting GlobalSetOfExtra
        mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        //Set Label of view components based on Params
        setLabelsOfTheView();

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        //System.out.println("------------> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;

        //ServiceDestination selectedServiceDestination = (ServiceDestination) intent.getSerializableExtra(Global.SELECTED_SERVICE_DESTINATION_KEY);

        //Gestion Button Generation de numero dans la Fille d'attente
        txtGenNumeroLabel = (TextView) findViewById(R.id.txtGenNumeroLabel);
        //txtGenNumero = (TextView) findViewById(R.id.textView2);
        txtGenNumero = (EditText) findViewById(R.id.textView);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        btnGenNumero = (Button) findViewById(R.id.buttonGenNumero);
        btnSms = (Button) findViewById(R.id.buttonSendSMS);

        //Making relevent compoennt INVISBLE
        makeInvisibleRelevantCompnoent();

        //
        processWhenNumeroSuivantFileForDemandeNumerosSuivantChanged();
        //
        btnGenNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(EcranPrincipalActivity.this, EcranGenrationNumeroSuivantActivity.class);
                //EcranPrincipalActivity.this.startActivity(intent);
//                int valueGenNumero = 0;
//                try {
//                    valueGenNumero = Integer.valueOf(txtGenNumero.getText().toString());
//                }catch (Exception e){
//                    valueGenNumero = 0;
//                }
//                valueGenNumero++;

                //
                getNextNumberForDestinationService();
                //
                int valueGenNumero = ++GlobalSetOfExtra.VALUE_GENERATED_NUMERO;
                strValueGenNumero = "";
                if (valueGenNumero < 10) {
                    strValueGenNumero = "00" + valueGenNumero;
                }
                if (valueGenNumero >= 10 && valueGenNumero < 100) {
                    strValueGenNumero = "0" + valueGenNumero;
                }
                txtGenNumeroLabel.setText("Votre numéro pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] est :");
                txtGenNumero.setText(strValueGenNumero);
                btnGenNumero.setEnabled(false);

                //Making relevent compoennt VISBLE
                makeVisibleRelevantCompnoent();
            }
        });


        //Gestion button Enoi de sms
        //Button btnSms = (Button) findViewById(R.id.button2);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EcranPrincipalActivity.this, EcranGenrationNumeroSuivantActivity.class);
//                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
//                intent.putExtra(Global.SELECTED_SERVICE_DESTINATION_KEY, selectedServiceDestination); //Optional parameters
//                intent.putExtra(Global.PROVIDED_TELEPHONE_NUMBER_KEY, editTextPhone.getText().toString()); //Optional parameters
//                intent.putExtra(Global.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
//                EcranPrincipalActivity.this.startActivity(intent);

                //Text to Voice
                //initializedTextToSpeechInstance("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + editTextPhone.getText().toString() + "]");
                //Text to Voice

                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                intent.putExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY, editTextPhone.getText().toString()); //Optional parameters
                intent.putExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
                EcranPrincipalActivity.this.startActivity(intent);
            }
        });
    }

    //--------------------
    void getNextNumberForDestinationService(){
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        binding.progressBar.setVisibility(View.VISIBLE);
//        userViewModel.demandeNumerosSuivant(new DemandeNumeroFile("Vision Medicale Coumba", "0122455789632111441251", "Pediatrie", "+221766752276", "2023-05-13T10:35:02.678Z" ));

//        {
//            "nomService": "Pediatrie",
//                "serviceDestinationid": 1,
//                "deviceId": "0000frsd00000000",
//                "telephoneDemandeur": "0652178684",
//                "emailDemandeur": "d_souleymane.diallo@yahoo.com"
//        }

//        this.nomService = nomService;
//        this.etablissementid = etablissementid;
//        this.serviceDestinationid = serviceDestinationid;
//        this.deviceId = deviceId;
//        this.telephoneDemandeur = telephoneDemandeur;
//        this.emailDemandeur = emailDemandeur;


        //DemandeNumeroFile demandeNumeroFile = new DemandeNumeroFile("Pediatrie", "1", "1","0000frsd00000000","0652178684", "d_souleymane.diallo@yahoo.com" );

        String nomService = mGlobalSetOfExtra.mServiceDestination.getNomServiceDestination();
        String etablissementid = String.valueOf(mGlobalSetOfExtra.mServiceDestination.getEtablissementid());
        String serviceDestinationid = String.valueOf(mGlobalSetOfExtra.mServiceDestination.getId());
        String deviceId = "000000000000";//Mettre en place une fonction qui va retourner cette information à partir d'un vrai device
        String telephoneDemandeur = editTextPhone.getText().toString();
        String emailDemandeur = "adresse_mail@recuperer.com";

        DemandeNumeroFile demandeNumeroFile = new DemandeNumeroFile(nomService, etablissementid, serviceDestinationid, deviceId, telephoneDemandeur, emailDemandeur);
        System.out.println(demandeNumeroFile.toString());
        userViewModel.demandeNumerosSuivant(demandeNumeroFile);

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    void processWhenNumeroSuivantFileForDemandeNumerosSuivantChanged() {
        userViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                System.out.println("NumeroSuivantFileForDemandeNumerosSuivant Data Changed............................................");
                System.out.println("numeroSuivantFile  --------> " + numeroSuivantFile.toString());
                txtGenNumero.setText(numeroSuivantFile.getNumeroSuivant());
                strValueGenNumero = numeroSuivantFile.getNumeroSuivant();
                mGlobalSetOfExtra.mNumeroSuivantFile = numeroSuivantFile;
                binding.progressBar.setVisibility(View.INVISIBLE);
                //makeGoneRelevantCompnoent();
                //binding.editTextTextMultiLine.setText(numeroSuivantFile.toString());
            }
        });
    }
    //----------------------------------------------

    @Override
    protected void onRestart() {
        super.onRestart();
        makeInvisibleRelevantCompnoent();
    }

    public void makeVisibleRelevantCompnoent() {
        txtGenNumeroLabel.setVisibility(View.VISIBLE);
        txtGenNumero.setVisibility(View.VISIBLE);
        btnSms.setVisibility(View.VISIBLE);
    }

    public void makeInvisibleRelevantCompnoent() {
        txtGenNumeroLabel.setVisibility(View.INVISIBLE);
        txtGenNumero.setVisibility(View.INVISIBLE);
        btnSms.setVisibility(View.INVISIBLE);
//        btnSms.setFocusable(View.FOCUSABLE);
    }

    public void makeGoneRelevantCompnoent() {
//        txtGenNumeroLabel.setVisibility(View.INVISIBLE);
//        txtGenNumero.setVisibility(View.INVISIBLE);
//        btnSms.setVisibility(View.INVISIBLE);
////        btnSms.setFocusable(View.FOCUSABLE);

        binding.progressBar.setVisibility(View.GONE);
        binding.textView5.setVisibility(View.GONE);
        binding.editTextPhone.setVisibility(View.GONE);
        binding.buttonGenNumero.setVisibility(View.GONE);
    }

    public void setLabelsOfTheView(){
//        binding.textView4.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageBienvenue"));
//        binding.textView5.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageinviteSaisieNumeroTel"));
//        binding.textView.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageIndicatifNumeroService"));
    }

    void initializedTextToSpeechInstance(String textToRenderOverVoice){
        initializedTextToSpeechInstancefromCallingActivity = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR){
                    initializedTextToSpeechInstancefromCallingActivity.setLanguage(Locale.FRANCE);
                    initializedTextToSpeechInstancefromCallingActivity.speak(textToRenderOverVoice, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}