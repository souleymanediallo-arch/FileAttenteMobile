package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityEcranPrincipalBinding;
import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.Utils;

public class EcranPrincipalActivity extends AppCompatActivity {

    Button btnGenNumero;
    TextView txtGenNumeroLabel;
    TextView txtGenNumero;
    Button btnSms;
    EditText editTextPhone;
    String strValueGenNumero;

    ActivityEcranPrincipalBinding binding;
    GlobalSetOfExtra mGlobalSetOfExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ecran_principal);

        //Using ViewBinding to manage Layout Components
        binding = ActivityEcranPrincipalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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

        ServiceDestination selectedServiceDestination = mGlobalSetOfExtra.mServiceDestination;

        //ServiceDestination selectedServiceDestination = (ServiceDestination) intent.getSerializableExtra(Global.SELECTED_SERVICE_DESTINATION_KEY);

        //Gestion Button Generation de numero dans la Fille d'attente
        txtGenNumeroLabel = (TextView) findViewById(R.id.textView);
        txtGenNumero = (TextView) findViewById(R.id.textView2);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        btnGenNumero = (Button) findViewById(R.id.button);
        btnSms = (Button) findViewById(R.id.button2);

        //Making relevent compoennt INVISBLE
        makeInvisibleRelevantCompnoent();

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
                int valueGenNumero = ++GlobalSetOfExtra.VALUE_GENERATED_NUMERO;
                strValueGenNumero = "";
                if (valueGenNumero < 10) {
                    strValueGenNumero = "00" + valueGenNumero;
                }
                if (valueGenNumero >= 10 && valueGenNumero < 100) {
                    strValueGenNumero = "0" + valueGenNumero;
                }
                txtGenNumeroLabel.setText("Votre numéro pour le service [" + selectedServiceDestination.getLibelleService() + "] est :");
                txtGenNumero.setText(strValueGenNumero);
                btnGenNumero.setEnabled(false);

                //Making relevent compoennt VISBLE
                makeVisibleRelevantCompnoent();
            }
        });


        //Gestion button Enoi de sms
        Button btnSms = (Button) findViewById(R.id.button2);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EcranPrincipalActivity.this, EcranGenrationNumeroSuivantActivity.class);
//                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
//                intent.putExtra(Global.SELECTED_SERVICE_DESTINATION_KEY, selectedServiceDestination); //Optional parameters
//                intent.putExtra(Global.PROVIDED_TELEPHONE_NUMBER_KEY, editTextPhone.getText().toString()); //Optional parameters
//                intent.putExtra(Global.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
//                EcranPrincipalActivity.this.startActivity(intent);

                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                intent.putExtra(GlobalSetOfExtra.PROVIDED_TELEPHONE_NUMBER_KEY, editTextPhone.getText().toString()); //Optional parameters
                intent.putExtra(GlobalSetOfExtra.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
                EcranPrincipalActivity.this.startActivity(intent);
            }
        });
    }

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

    public void setLabelsOfTheView(){
        binding.textView4.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageBienvenue"));
        binding.textView5.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageinviteSaisieNumeroTel"));
        binding.textView.setText(Utils.getValueForKey(mGlobalSetOfExtra.mListParams, "messageIndicatifNumeroService"));
    }
}