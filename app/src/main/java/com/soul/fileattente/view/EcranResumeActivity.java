package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.R;
import com.soul.fileattente.model.ServiceDestination;

public class EcranResumeActivity extends AppCompatActivity {

    TextView txtGenNumeroLabel;
    TextView txtResumeLabell;
    GlobalSetOfExtra mGlobalSetOfExtra;

    //Sms envoye pour le service gynecologique au numero 76 675 22 77
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_resume);

        txtGenNumeroLabel = (TextView) findViewById(R.id.textView2);
        txtResumeLabell = (TextView) findViewById(R.id.textView5);
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
        System.out.println("------------> " + GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        System.out.println("------------> " + GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;
        //Getting provided telephone
        String telephone = intent.getStringExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY);
        //Getting generated number
        String numeroPourLeService = intent.getStringExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);
        //

        txtResumeLabell.setText("Sms envoyé pour le service [" + selectedServiceDestination.getLibelleService() + "] au numero [" + telephone + "]");
        txtGenNumeroLabel.setText(numeroPourLeService);
        launchAutomaticallyMainScreen();

    }

    void launchAutomaticallyMainScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                //Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalFilesAllServicesActivity.class);
                Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalActivityList.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                EcranResumeActivity.this.startActivity(intent);
            }
        }, 2000);
    }
}