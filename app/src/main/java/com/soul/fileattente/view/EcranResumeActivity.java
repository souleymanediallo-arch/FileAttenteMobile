package com.soul.fileattente.view;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.R;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;

import java.util.Locale;

public class EcranResumeActivity extends AppCompatActivity {

    TextToSpeech initializedTextToSpeechInstancefromCallingActivity;
    TextView txtGenNumeroLabel;
    TextView txtResumeLabell;
    //TextView txtMoreResume;
    GlobalSetOfExtra mGlobalSetOfExtra;

    //Sms envoye pour le service gynecologique au numero 76 675 22 77
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_resume);
        txtGenNumeroLabel = (TextView) findViewById(R.id.textView2);
        txtResumeLabell = (TextView) findViewById(R.id.textView5);
        //txtMoreResume = (TextView) findViewById(R.id.textView3);
        //Getting selected service
        Intent intent = getIntent();
        //String service = intent.getStringExtra(Global.SELECTED_SERVICE_KEY);
        //ServiceDestination selectedServiceDestination = (ServiceDestination) intent.getSerializableExtra(Global.SELECTED_SERVICE_DESTINATION_KEY);
//        System.out.printf("---------------------------------------> selectedServiceDestination = " + selectedServiceDestination);
//        Log.d("TAG", "---------------------------------------> selectedServiceDestination = " + selectedServiceDestination);
        //Getting provided telephone
//        String telephone = intent.getStringExtra(Global.PROVIDED_TELEPHONE_NUMBER_KEY);
//        //Getting generated number
//        String numeroPourLeService = intent.getStringExtra(Global.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);


        //
        //Getting GlobalSetOfExtra
        mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mServiceDestination.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        System.out.println("------------> " + GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        System.out.println("------------> " + GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;
        //Getting provided telephone
        String telephone = intent.getStringExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        //Getting generated number
        String numeroPourLeService = intent.getStringExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);
        //

        txtResumeLabell.setText("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + telephone + "]");
        //txtGenNumeroLabel.setText(numeroPourLeService);
        txtGenNumeroLabel.setText(numeroPourLeService);

        //TextToSpeech here
        initializedTextToSpeechInstance("Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + telephone + "]");
        //Utils.translateFromTextToSpeech(initializedTextToSpeechInstancefromCallingActivity, "Sms envoyé pour le service [" + selectedServiceDestination.getNomServiceDestination() + "] au numero [" + telephone + "]");
        //txtMoreResume.setText(mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        //sendTextAsSms(mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur(), mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        System.out.println("mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur() -> " + mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur() + "  --  mGlobalSetOfExtra.mNumeroSuivantFile.toString() -> " + mGlobalSetOfExtra.mNumeroSuivantFile.toString());
        String messToSend =
                "Numero : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNumeroSuivant() + "\n" +
                "Service : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNomService() + "\n" +
                "Nb Pers en Attente : " + mGlobalSetOfExtra.mNumeroSuivantFile.getNbTotalDemandeursEnCours() + "\n" +
                "Temps Attente Moyen : " + mGlobalSetOfExtra.mNumeroSuivantFile.getTempsAttenteMoyen() + "\n" +
                "Temps Attente Estime : " + mGlobalSetOfExtra.mNumeroSuivantFile.getTempsAttenteEstime();

        //Utils.sendTextAsSms("0652178684", messToSend);
        Utils.sendTextAsSms(mGlobalSetOfExtra.mNumeroSuivantFile.getTelephoneDemandeur(), messToSend); //à decommenter à la livraison
        launchAutomaticallyMainScreen();
    }

    void launchAutomaticallyMainScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //initializedTextToSpeechInstance("Sms envoyé pour le service [" + "selectedServiceDestination.getNomServiceDestination()" + "] au numero [" + "GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY)" + "]");
                finish();
                //Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalFilesAllServicesActivity.class);
                Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalActivityList.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                EcranResumeActivity.this.startActivity(intent);
            }
        }, 5000);
    }


    // create an object textToSpeech and adding features into it
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
    //https://www.tutlane.com/tutorial/android/android-send-sms-with-examples -- Demande une intervention manuelle **
//    void sendTextAsSms(String mobileNumber, String textMessage) {
//        try{
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse("smsto:"));
//            i.setType("vnd.android-dir/mms-sms");
//            i.putExtra("address", mobileNumber);
//            i.putExtra("sms_body",textMessage);
//            startActivity(Intent.createChooser(i, "Send sms via:"));
//        }
//        catch(Exception e){
//            Toast.makeText(this, "Echec envoi sms...", Toast.LENGTH_SHORT).show();
//        }
//    }

//    void sendTextAsSms(String mobileNumber, String textMessage){
//
//        ContentValues values = new ContentValues();
//        values.put("address", mobileNumber); // phone number to send
//        values.put("date", System.currentTimeMillis()+"");
//        values.put("read", "1"); // if you want to mark is as unread set to 0
//        values.put("type", "2"); // 2 means sent message
//        values.put("body", textMessage);
//
//        Uri uri = Uri.parse("content://sms/");
//        Uri rowUri = this.getContentResolver().insert(uri,values);
//    }


    //cette version Plante mais devrait etre la bonne version
//    void sendTextAsSms(String mobileNumber, String textMessage){
//
//        SmsManager smsManager = SmsManager.getDefault();
//        if(smsManager != null) {
//            smsManager.sendTextMessage(mobileNumber, null, textMessage, null, null);
//        }
//    }

//    //Getting intent and PendingIntent instance
//    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//    PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
//    //Get the SmsManager instance and call the sendTextMessage method to send message
//    SmsManager sms=SmsManager.getDefault();
//    sms.sendTextMessage("8802177690", null, "hello javatpoint", pi,null);

//    // create an object textToSpeech and adding features into it
//    void translateTextToSpeech(){
//        textToSpeech = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                // if No error is found then only it will run
//                if(i!=TextToSpeech.ERROR){
//                    // To Choose language of speech
//                    textToSpeech.setLanguage(Locale.FRANCE);
//                }
//            }
//        });
//    }

    //https://github.com/AndroidGifts/Send-SMS-Programmatically-with-delivery-report/blob/master/app/src/main/java/com/androidgifts/gift/sendsmsprogrammatically/MainActivity.java
}