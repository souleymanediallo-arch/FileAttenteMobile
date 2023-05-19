package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.soul.fileattente.R;

public class EcranResumeActivity extends AppCompatActivity {

    TextView txtGenNumeroLabel;
    TextView txtResumeLabell;

    //Sms envoye pour le service gynecologique au numero 76 675 22 77
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_resume);

        txtGenNumeroLabel =  (TextView)findViewById(R.id.textView2);
        txtResumeLabell =  (TextView)findViewById(R.id.textView5);
        //Getting selected service
        Intent intent = getIntent();
        String service = intent.getStringExtra(Global.SELECTED_SERVICE_KEY);
        //Getting provided telephone
        String telephone = intent.getStringExtra(Global.PROVIDED_TELEPHONE_NUMBER_KEY);
        //Getting generated number
        String numeroPourLeService = intent.getStringExtra(Global.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY);

        txtResumeLabell.setText("Sms envoyé pour le service [" + service + "] au numero [" + telephone + "]");
        txtGenNumeroLabel.setText(numeroPourLeService);
        launchAutomaticallyMainScreen();

    }

    void launchAutomaticallyMainScreen(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                //Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalFilesAllServicesActivity.class);
                Intent intent = new Intent(EcranResumeActivity.this, EcranPrincipalActivity.class);
                EcranResumeActivity.this.startActivity(intent);
            }
        }, 50000);
    }
}