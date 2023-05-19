package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.soul.fileattente.R;

public class EcranPrincipalActivity extends AppCompatActivity {

    Button btnGenNumero;
    TextView txtGenNumeroLabel;
    TextView txtGenNumero;
    Button btnSms;
    EditText editTextPhone;
    String strValueGenNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_principal);

        //Getting selected service
        Intent intent = getIntent();
        String service = intent.getStringExtra("service"); //if it's a string you stored.

        //Gestion Button Generation de numero dans la Fille d'attente
        txtGenNumeroLabel =  (TextView)findViewById(R.id.textView);
        txtGenNumero =  (TextView)findViewById(R.id.textView2);
        editTextPhone =  (EditText) findViewById(R.id.editTextPhone);
        btnGenNumero = (Button)findViewById(R.id.button);
        btnSms = (Button)findViewById(R.id.button2);

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
                int valueGenNumero = ++Global.VALUE_GENERATED_NUMERO;
                strValueGenNumero = "";
                if(valueGenNumero <10){
                    strValueGenNumero = "00"+valueGenNumero;
                }
                if(valueGenNumero >= 10 && valueGenNumero < 100){
                    strValueGenNumero = "0"+valueGenNumero;
                }
                txtGenNumeroLabel.setText("Votre numéro pour le service [" + service + "] est :");
                txtGenNumero.setText(strValueGenNumero);
                btnGenNumero.setEnabled(false);

                //Making relevent compoennt VISBLE
                makeVisibleRelevantCompnoent();
            }
        });


        //Gestion button Enoi de sms
        Button btnSms = (Button)findViewById(R.id.button2);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EcranPrincipalActivity.this, EcranGenrationNumeroSuivantActivity.class);
                Intent intent = new Intent(EcranPrincipalActivity.this, EcranResumeActivity.class);
                intent.putExtra(Global.SELECTED_SERVICE_KEY, service); //Optional parameters
                intent.putExtra(Global.PROVIDED_TELEPHONE_NUMBER_KEY, editTextPhone.getText().toString()); //Optional parameters
                intent.putExtra(Global.GENERATED_NUMNER_FOR_CURRENT_SERVICE_KEY, String.valueOf(strValueGenNumero)); //Optional parameters
                EcranPrincipalActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        makeInvisibleRelevantCompnoent();
    }

    public void makeVisibleRelevantCompnoent(){
        txtGenNumeroLabel.setVisibility(View.VISIBLE);
        txtGenNumero.setVisibility(View.VISIBLE);
        btnSms.setVisibility(View.VISIBLE);
    }

    public void makeInvisibleRelevantCompnoent(){
        txtGenNumeroLabel.setVisibility(View.INVISIBLE);
        txtGenNumero.setVisibility(View.INVISIBLE);
        btnSms.setVisibility(View.INVISIBLE);
//        btnSms.setFocusable(View.FOCUSABLE);
    }
}