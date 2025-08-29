package com.soul.fileattente.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.Etablissement;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.utils.ApplicationConstants;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;
    private Login mLogin;
    private Login mAuthenticationResult;
    private Login mLoginResult;
    private Etablissement mEtablissement;
    private GlobalSetOfExtra mGlobalSetOfExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Using ViewBinding to manage Layout Components
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(LoginActivity.this).get(UserViewModel.class);
        adjustViewComponentsStatusBeforeEtablissementSyncCompleted();
        DemandeGeneric demandeGeneric = new DemandeGeneric();
        demandeGeneric.setIdEtablissement(ApplicationConstants.IdEtablissementForThisMobileAPP); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setPatientDeviceId(Utils.getUniqueId(this.getApplicationContext()));//Infomations à calculer
        userViewModel.demandeEtablissement(demandeGeneric);
        binding.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Refresh Button Click.............................................................................................");
                userViewModel.demandeEtablissement(demandeGeneric);
            }
        });
        binding.btnLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.btnLogin.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.btnLogin.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_700));
                }
                return false;
            }
        });
        binding.btnRefresh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    binding.btnRefresh.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.btnRefresh.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_700));
                }
                return false;
            }
        });
        handleSpinner();
        //Be prepared to process the changes of the values for var in viewModel: AutheticationResultForAuthenticate
        processWhenAutheticationResultForAuthenticateChanged();
        //Be prepared to process the changes of the values for var in viewModel: User
        processWhenLoginResultForLoginChanged();
        //Be prepared to process the changes of the values for var in viewModel: Results
        processWhenEtablissementForDemandeEtablissementChanged();
        //Precess Btnlogin Click
        processTaskWhenloginButtonClicked();
    }

    //<item>Patient</item>
    //<item>Moniteur</item>
    //<item>Docteur</item>
    //<item>Administrateur</item>

    private void handleSpinner() {
        String[] listProfiles = getResources().getStringArray(R.array.profile_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, listProfiles);
        binding.profilSpinnerEdtxt.setAdapter(adapter);
    }

    private void navigateToEcranPrincipalActivityListWithPatientProfil() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mLogin.setProfil("Patient");
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mEtablissement = mEtablissement;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }

    private void navigateToEcranPrincipalMonitoringActivityList() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalMonitoringActivityList.class);

        mLogin.setProfil("Moniteur");
        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mEtablissement = mEtablissement;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }

    private void navigateToEcranPrincipalActivityListWithDoctorProfil() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mLogin.setProfil("Docteur");
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mEtablissement = mEtablissement;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }

    //private void navigateToEcranDocteurActivityList() {
    private void navigateToEcranPrincipalTraitementActivityList() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalTraitementActivityList.class);

        //mLogin.setProfil("Docteur");
        mLogin.setProfil("Administrateur");
        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mEtablissement = mEtablissement;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }


    private void navigateToEcranDocteurActivityList_BeforeImpl() {
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Fonctionnalité non encore mise en oeuvre...");
        binding.progressBar.setVisibility(View.INVISIBLE);
        System.out.println("Fonctionnalité non encore mise en oeuvre...");
    }

    private void navigateToEcranAdministrateurActivityList() {
//        binding.txtInputLayoutEdtErroMessageFND.setVisibility(View.VISIBLE);
//        binding.textErroMessageFND.setText("Fonctionnalité non encore mise en oeuvre...");
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Fonctionnalité non encore mise en oeuvre...");
        binding.progressBar.setVisibility(View.INVISIBLE);
        System.out.println("Fonctionnalité non encore mise en oeuvre...");
    }

    private void handleUsernameAndPasswordError() {

        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Login/Password et ou Profil Incorrect...");
        binding.progressBar.setVisibility(View.INVISIBLE);
        System.out.println("Login/Password et ou Profil Incorrect...");
    }

    void processTaskWhenloginButtonClicked() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chosenProfile = (binding.profilSpinnerEdtxt).getText().toString();
                String userame = binding.textInputEditTextLogin.getText().toString();
                String password = binding.textInputEditPassword.getText().toString();

                System.out.println("-------------------------------------------------------------> I got clicked  chosenProfile --->  " + chosenProfile);
                System.out.println("-------------------------------------------------------------> I got clicked  userame --->  " + userame);
                System.out.println("-------------------------------------------------------------> I got clicked  password --->  " + password);

//                if((userame!=null && userame.equals("patient") && password.equals("patient!") && chosenProfile.equals("Patient")) ||
//                   (userame!=null && userame.equals("moniteur") && password.equals("moniteur!") && chosenProfile.equals("Moniteur"))){
                //if(userame!=null && password.equals(userame+"!") && (chosenProfile.equals("Patient") || chosenProfile.equals("Moniteur"))) {
                    mLogin = new Login();
                    mLogin.setUsername("admin");
                    mLogin.setPassword("admin");
                    userViewModel.authenticate(mLogin);
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.textErroMessage.setVisibility(View.INVISIBLE);
//                }else{
//                    handleUsernameAndPasswordError();
//                }
            }
        });
    }

    void processWhenAutheticationResultForAuthenticateChanged() {
        userViewModel.getAutheticationResultForAuthenticate().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login autheticationResult) {
                System.out.println("AuthenticationResultForAuthenticate Data Changed............................................");
                mAuthenticationResult = autheticationResult;
                //Set new token after authentication or re-authentication so that query can be done...
                //RetrofitClient.gottenTokenAfterLoginOrRefresh = autheticationResult.getId_token();
                //System.out.println("Just set RetrofitClient.gottenTokenAfterLoginOrRefresh to ------------> " + RetrofitClient.gottenTokenAfterLoginOrRefresh );
                //Then try to login after completion of Authetication successful
                //mLogin = new Login("admin", "admin");
                if(mAuthenticationResult != null) {
                    mLogin = new Login();
                    mLogin.setUsername("admin");
                    mLogin.setPassword("admin");
                    userViewModel.login(mLogin);
                }else{
                    //On a deja des problemes pour appeler la methode Autheticate -> Possiblement probleme d'internet
                    binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
                    binding.textErroMessage.setText("Connection Impossible, Verifiez votre connetivite ou Remontez le probleme...");
                    binding.progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    void processWhenLoginResultForLoginChanged() {
        userViewModel.getLoginResultForLogin().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login loginResult) {
                System.out.println("LoginResultForLogin Data Changed............................................");
                System.out.println("loginResult-------------------------------------------> " + loginResult);
                mLoginResult = loginResult;
                String chosenProfile = (binding.profilSpinnerEdtxt).getText().toString();
                System.out.println("chosenProfile--------------------------------------> = " + chosenProfile);
                if(chosenProfile.equalsIgnoreCase("Moniteur"))  {
                    navigateToEcranPrincipalMonitoringActivityList();
                }
                if(chosenProfile.equalsIgnoreCase("Patient"))  {
                    navigateToEcranPrincipalActivityListWithPatientProfil();
                }
                if(chosenProfile.equalsIgnoreCase("Docteur"))  {
                    navigateToEcranPrincipalActivityListWithDoctorProfil();
                }
                if(chosenProfile.equalsIgnoreCase("Administrateur"))  {
                    navigateToEcranAdministrateurActivityList();
                    //navigateToEcranPrincipalTraitementActivityList();
                    //.makeText(getApplicationContext(), "Fonctionnalité non encore mise en oeuvre...", Toast.LENGTH_LONG);
                }
            }
        });
    }


    void processWhenEtablissementForDemandeEtablissementChanged() {
        userViewModel.getEtablissementFordemanderEtablissement().observe(this, new Observer<Etablissement>() {
            @Override
            public void onChanged(Etablissement etablissement) {
                if(etablissement == null){
                    System.out.println(" ERROR EtablissementFordemanderEtablissement Data Changed............................................");
                    binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
                    binding.textErroMessage.setText("Connection Impossible, Verifiez votre connetivite ou Remontez le probleme...");
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    hanldeRefreshButtonWhenNotOK();
                }else {
                    System.out.println("EtablissementFordemanderEtablissement Data Changed............................................");
                    mEtablissement = etablissement;
                    //adjustViewComponentsStatusAfterParamSyncCompleted();
                    adjustViewComponentsStatusAfterEtablissementSyncCompleted();
                    hanldeRefreshButtonWhenOK();
                }
            }
        });
    }


    void adjustViewComponentsStatusBeforeEtablissementSyncCompleted() {
        //https://www.computerhope.com/htmcolor.htm#color-codes
        binding.btnLogin.setText("Loading Applications Params..");
        //int color = Color.parseColor("#FFCCCCCC");
        binding.btnLogin.setTextColor(Color.LTGRAY); //#52595D, 0x52595D, 0xff888888
        //binding.btnLogin.setBackgroundColor(Color.LTGRAY);
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.INVISIBLE);
        binding.btnRefresh.setVisibility(View.INVISIBLE);
    }

    void adjustViewComponentsStatusAfterEtablissementSyncCompleted() {
        binding.btnLogin.setText("Se Connecter");
        //binding.btnLogin.setTextColor(Color.BLACK);
        binding.btnLogin.setTextColor(Color.WHITE);
        binding.btnLogin.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.btnLogin.setEnabled(true);
        //binding.btnLogin.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_refresh));
        //binding.btnLogin.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_refresh),null,null,null);
    }

    void hanldeRefreshButtonWhenNotOK(){
        binding.btnRefresh.setVisibility(View.VISIBLE);
        binding.btnRefresh.setText("Rafrachir...");
        binding.btnRefresh.setTextColor(Color.WHITE);
        binding.btnRefresh.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500));
    }

    void hanldeRefreshButtonWhenOK(){
        binding.btnRefresh.setVisibility(View.GONE);
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.INVISIBLE);
    }
    //
    private void print(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //textResult.setText(textResult.getText().toString() + "n" + message);
                Log.e("WSocket", message);
                System.out.println("WSocket  -----> " + message);
            }
        });
    }
}