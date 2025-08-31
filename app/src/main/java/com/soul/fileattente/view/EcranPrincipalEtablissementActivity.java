package com.soul.fileattente.view;

import static com.soul.fileattente.utils.ApplicationConstants.tempsAttenteAvantRetourListServices;
import static com.soul.fileattente.utils.ApplicationConstants.GLOBAL_PREFERENCE_KEY_ID_ETABLISSEMENT;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.soul.fileattente.databinding.ActivityEtablissementEditBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.Etablissement;
import com.soul.fileattente.utils.ApplicationConstants;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;


public class EcranPrincipalEtablissementActivity extends AppCompatActivity {

    ActivityEtablissementEditBinding binding;
    private UserViewModel userViewModel;
    private Etablissement mEtablissement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Using ViewBinding to manage Layout Components
        binding = ActivityEtablissementEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userViewModel = new ViewModelProvider(EcranPrincipalEtablissementActivity.this).get(UserViewModel.class);
        clearAllFileds();

        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.btnSynchronize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.btnSynchronize.getText().toString().equalsIgnoreCase("Synchroniser")){
                    //binding.btnSynchronize.setText("Continuer ->");
                    //simulateLonOperationWaitOnNetwork();
                    processWhenSynchronizeButtonClick();
                }

                if(binding.btnSynchronize.getText().toString().equalsIgnoreCase("Continuer ->")){
                    Intent intent = new Intent(EcranPrincipalEtablissementActivity.this, EcranServiceDestinationActivityList.class);
                    EcranPrincipalEtablissementActivity.this.startActivity(intent);
                    binding.btnSynchronize.setText("Page Suivante ->");
                }
            }
        });

        processWhenEtablissementForDemandeEtablissementChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.btnSynchronize.setText("Synchroniser");
    }

    private void processWhenSynchronizeButtonClick(){
        String idEtablissement = binding.textInputEditTextIdEtablissement.getText().toString();

        if (!idEtablissement.trim().isEmpty()) {
            //Getting Instance of the viewModel that will manage the Business of the aapplication
            binding.progressBar.setVisibility(View.VISIBLE);
            //userViewModel = new ViewModelProvider(EcranPrincipalEtablissementActivity.this).get(UserViewModel.class);
            //adjustViewComponentsStatusBeforeEtablissementSyncCompleted();
            DemandeGeneric demandeGeneric = new DemandeGeneric();
            demandeGeneric.setIdEtablissement(idEtablissement);
            demandeGeneric.setPatientDeviceId(Utils.getUniqueId(this.getApplicationContext()));//Infomations à calculer
            userViewModel.demandeEtablissement(demandeGeneric);
            //Getting Instance of the viewModel that will manage the Business of the aapplication
        }else{
            binding.textInputLayoutErrorMessage.setVisibility(View.VISIBLE);
            binding.textInputEditErrorMessage.setText("Vous devez renseigner la valeur du champ \"Identifiant Etablissement\"");
        }
    }
//    private void simulateLonOperationWaitOnNetwork(){
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//
//                if (validateInputs()) {
//                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                    prefs.edit().putString(GLOBAL_PREFERENCE_KEY_ID_ETABLISSEMENT, binding.textInputEditTextIdEtablissement.getText().toString()).apply();
//                    binding.btnSynchronize.setText("Continuer ->");
//                }else{
//                    binding.textInputLayoutErrorMessage.setVisibility(View.VISIBLE);
//                    binding.textInputEditErrorMessage.setText("Vous devez renseigner la valeur du champ \"Identifiant Etablissement\"");
//                }
//
//            }
//        }, tempsAttenteAvantRetourListServices);
//    }


    private boolean validateInputs() {
        return !binding.textInputEditTextIdEtablissement.getText().toString().trim().isEmpty();
    }

    void processWhenEtablissementForDemandeEtablissementChanged() {
        userViewModel.getEtablissementFordemanderEtablissement().observe(this, new Observer<Etablissement>() {
            @Override
            public void onChanged(Etablissement etablissement) {
                if(etablissement == null){
                    System.out.println(" ERROR EtablissementFordemanderEtablissement Data Changed............................................");
                    binding.textInputLayoutErrorMessage.setVisibility(View.VISIBLE);
                    binding.textInputEditErrorMessage.setText("Verifier votre connectivite ou l'id d'etablissement saisi..");
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    //hanldeRefreshButtonWhenNotOK();
                }else {
                    //Fill the all the other fields
                    System.out.println("EtablissementFordemanderEtablissement Data Changed............................................");
                    mEtablissement = etablissement;
                    binding.textInputEditTextNomEtablissement.setText(mEtablissement.getNomEtablissement());
                    binding.textInputEditTextAdresse.setText(mEtablissement.getAdresse());
                    binding.textInputEditTelephone.setText(mEtablissement.getTelephone());
                    binding.textInputEditEmail.setText(mEtablissement.getEmail());
                    //Save in preferences so that it will be accessible from everywhere
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putString(GLOBAL_PREFERENCE_KEY_ID_ETABLISSEMENT,mEtablissement.getIdEtablissement()).apply();
                    binding.btnSynchronize.setText("Continuer ->");
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    binding.textInputLayoutErrorMessage.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void clearAllFileds(){
        binding.textInputEditTextNomEtablissement.setText("");
        binding.textInputEditTextAdresse.setText("");
        binding.textInputEditTelephone.setText("");
        binding.textInputEditEmail.setText("");
    }
}