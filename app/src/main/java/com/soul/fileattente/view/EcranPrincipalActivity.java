package com.soul.fileattente.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    String strValueGenNumero;
    ActivityEcranPrincipalBinding binding;
    GlobalSetOfExtra mGlobalSetOfExtra;
    private UserViewModel userViewModel;
    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;
    static int nbTimesPostDataTriggered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using ViewBinding to manage Layout Components
        binding = ActivityEcranPrincipalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting the Business part - Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalActivity.this).get(UserViewModel.class);

        //Getting selected selectedServiceDestination
        Intent intent = getIntent();
        //Getting GlobalSetOfExtra
        mGlobalSetOfExtra = (GlobalSetOfExtra) getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        //System.out.println("------------> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;
        //
        binding.buttonGenNumero.setTextColor(Color.WHITE);
        binding.buttonGenNumero.setEnabled(false);
        binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));

        //Making relevent compoennt INVISBLE
        makeInvisibleRelevantCompnoent();
        //
        processWhenNumeroSuivantFileForDemandeNumerosSuivantChanged();
        //
        binding.buttonGenNumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNextNumberForDestinationService();

                int valueGenNumero = ++GlobalSetOfExtra.VALUE_GENERATED_NUMERO;
                strValueGenNumero = "";
                if (valueGenNumero < 10) {
                    strValueGenNumero = "00" + valueGenNumero;
                }
                if (valueGenNumero >= 10 && valueGenNumero < 100) {
                    strValueGenNumero = "0" + valueGenNumero;
                }
            }
        });

        binding.editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() == 9) {
                    binding.buttonGenNumero.setEnabled(true);
                    binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                } else {
                    binding.buttonGenNumero.setEnabled(false);
                    binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));
                }
            }
        });

        //Gestion button Enoi de sms
        binding.buttonSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Text to Voice
                //initializedTextToSpeechInstance("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + binding.editTextPhone.getText().toString() + "]");
                initializedTextToSpeechInstance("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + Utils.formatSenegalTelephoneNumberForTextToVoice(binding.editTextPhone.getText().toString()) + "]");
                //Text to Voice
                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                intent.putExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY, binding.editTextPhone.getText().toString()); //Optional parameters
                intent.putExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
                EcranPrincipalActivity.this.startActivity(intent);
                finish();
                finish();
            }
        });

        binding.buttonGenNumero.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_700));
                }
                return false;
            }
        });

        binding.buttonSendSMS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.buttonSendSMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.buttonSendSMS.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_700));
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeInvisibleRelevantCompnoent();
    }

    //--------------------
    void getNextNumberForDestinationService() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        binding.progressBar.setVisibility(View.VISIBLE);

        String nomService = mGlobalSetOfExtra.mServiceDestination.getNomServiceDestination();
        String etablissementid = String.valueOf(mGlobalSetOfExtra.mServiceDestination.getEtablissementid());
        String serviceDestinationid = String.valueOf(mGlobalSetOfExtra.mServiceDestination.getId());
        String deviceId = "000000000000";//Mettre en place une fonction qui va retourner cette information à partir d'un vrai device
        String telephoneDemandeur = binding.editTextPhone.getText().toString();
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

                nbTimesPostDataTriggered++;
                System.out.println("nbTimesPostDataTriggered............................................" + nbTimesPostDataTriggered);

                if (nbTimesPostDataTriggered == 1) {
                    if (numeroSuivantFile != null) {
                        System.out.println("NumeroSuivantFileForDemandeNumerosSuivant Data Changed............................................");
                        System.out.println("numeroSuivantFile  --------> " + numeroSuivantFile.toString());
                        makeVisibleRelevantCompnoent();
                        binding.txtGenNumeroLabel.setText("Votre numéro pour le service [" + numeroSuivantFile.getNomService() + "] est :");
                        binding.txtGenNumero.setText(numeroSuivantFile.getNumeroSuivant());
                        strValueGenNumero = numeroSuivantFile.getNumeroSuivant();
                        mGlobalSetOfExtra.mNumeroSuivantFile = numeroSuivantFile;
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        binding.buttonGenNumero.setEnabled(false);
                        binding.buttonGenNumero.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));
                    } else {
                        makeVisibleRelevantCompnoent();
                        binding.txtGenNumeroLabel.setText("Connection Impossible, Verifiez votre connetivite ou Remontez le probleme...");
                        binding.txtGenNumero.setText("XXX.00");
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        binding.buttonSendSMS.setVisibility(View.INVISIBLE);
                        binding.buttonGenNumero.setEnabled(true);
                        nbTimesPostDataTriggered = 0;
                    }
                }
                //Gestion des effets de bord des LiveData qui poste des valeurs 2 fois
                if (nbTimesPostDataTriggered == 2) {
                    nbTimesPostDataTriggered = 0;
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeInvisibleRelevantCompnoent();
    }

    public void makeVisibleRelevantCompnoent() {

        binding.txtGenNumeroLabel.setVisibility(View.VISIBLE);
        binding.txtGenNumero.setVisibility(View.VISIBLE);
        binding.buttonSendSMS.setVisibility(View.VISIBLE);

        binding.buttonSendSMS.setTextColor(Color.WHITE);
        binding.buttonSendSMS.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
    }

    public void makeInvisibleRelevantCompnoent() {
        binding.txtGenNumeroLabel.setVisibility(View.INVISIBLE);
        binding.txtGenNumero.setVisibility(View.INVISIBLE);
        binding.buttonSendSMS.setVisibility(View.INVISIBLE);
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