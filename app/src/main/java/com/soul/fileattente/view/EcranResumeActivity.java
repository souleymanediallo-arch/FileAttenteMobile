package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.databinding.ActivityEcranResumeBinding;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;

import java.util.Locale;

public class EcranResumeActivity extends AppCompatActivity {

    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;
    GlobalSetOfExtra mGlobalSetOfExtra;

    ActivityEcranResumeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using ViewBinding to manage Layout Components
        binding = ActivityEcranResumeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        //Getting GlobalSetOfExtra
        mGlobalSetOfExtra = (GlobalSetOfExtra) getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        System.out.println("------------> " + GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        System.out.println("------------> " + GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;
        String telephone = intent.getStringExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        String numeroPourLeService = intent.getStringExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);

        binding.txtResumeLabel.setText("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + telephone + "]");
        binding.txtGenNumeroLabel.setText(numeroPourLeService);

        //TextToSpeech here
        initializedTextToSpeechInstance("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + telephone + "]");
        System.out.println("mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur() -> " + mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur() + "  --  mGlobalSetOfExtra.mNumeroSuivantFile.toString() -> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        String messToSend =
                "Numero : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNumeroSuivant() + "\n" +
                        "Service : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNomService() + "\n" +
                        "Nb Pers en Attente : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNbTotalDemandeursEnCours() + "\n" +
                        "Temps Attente Moyen : " + mGlobalSetOfExtra.mNumeroSuivantFile.getTempsAttenteMoyen() + "\n" +
                        "Temps Attente Estime : " + mGlobalSetOfExtra.mNumeroSuivantFile.getTempsAttenteEstime();

        Utils.sendTextAsSms(mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur(), messToSend); //à decommenter à la livraison
        launchAutomaticallyMainScreen();
    }

    void launchAutomaticallyMainScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalActivityList.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                EcranResumeActivity.this.startActivity(intent);
            }
        }, 5000);
    }

    // create an object textToSpeech and adding features into it
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