package com.soul.fileattente.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.soul.fileattente.R;
import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    private Login mLogin;
    private AutheticationResult mAuthenticationResult;
    private LoginResult mLoginResult;
    private List<Param> mListParams;

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

        adjustViewComponentsStatusBeforeParamSyncCompleted();
        //DemandeParam demandeParam = new DemandeParam("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z");

        DemandeGeneric demandeGeneric = new DemandeGeneric();
        demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setDeviceId("000000000000");//Infomations à calculer

        userViewModel.demandeAllParams(demandeGeneric);

        binding.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Refresh Button Click.............................................................................................");
                userViewModel.demandeAllParams(demandeGeneric);
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
        processWhenListParamForDemandeAllParamsChanged();

        //Precess Btnlogin Click
        processTaskWhenloginButtonClicked();
    }

        void handleSpinner() {
            //String[] listProfiles = {"Patient", "Moniteur", "Docteur", "Administrateur"};
            String[] listProfiles = getResources().getStringArray(R.array.profile_array);;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_item, listProfiles);
            binding.profilSpinnerEdtxt.setAdapter(adapter);
        }

    private void navigateToEcranPrincipalActivityList() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        //mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mLoginResult = new LoginResult("ok");
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mListParams = mListParams;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }

    private void navigateToEcranPrincipalMonitoringActivityList() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalMonitoringActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        //mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mLoginResult = new LoginResult("ok");
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mListParams = mListParams;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        binding.progressBar.setVisibility(View.INVISIBLE);
        LoginActivity.this.startActivity(intent);
    }


    private void navigateToEcranDocteurActivityList() {
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Fonctionnalité non encore mise en oeuvre...");
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void navigateToEcranAdministrateurActivityList() {
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Fonctionnalité non encore mise en oeuvre...");
        binding.progressBar.setVisibility(View.INVISIBLE);
    }

    private void handleUsernameAndPasswordError() {
        binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
        binding.textErroMessage.setText("Login/Password et ou Profil Incorrect...");
        binding.progressBar.setVisibility(View.INVISIBLE);
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

                if((userame!=null && userame.equals("patient") && password.equals("patient!") && chosenProfile.equals("Patient")) ||
                   (userame!=null && userame.equals("moniteur") && password.equals("moniteur!") && chosenProfile.equals("Moniteur"))){
                //if(userame!=null && password.equals(userame+"!") && (chosenProfile.equals("Patient") || chosenProfile.equals("Moniteur"))) {
                    mLogin = new Login("admin", "admin");
                    userViewModel.authenticate(mLogin);
                    binding.progressBar.setVisibility(View.VISIBLE);
                    binding.textErroMessage.setVisibility(View.INVISIBLE);
                }else{
                    handleUsernameAndPasswordError();
                }
            }
        });
    }

    void processWhenAutheticationResultForAuthenticateChanged() {
        userViewModel.getAutheticationResultForAuthenticate().observe(this, new Observer<AutheticationResult>() {
            @Override
            public void onChanged(AutheticationResult autheticationResult) {
                System.out.println("AutheticationResultForAuthenticate Data Changed............................................");
                mAuthenticationResult = autheticationResult;
                //Set new token after authentication or re-authentication so that query can be done...
                //RetrofitClient.gottenTokenAfterLoginOrRefresh = autheticationResult.getId_token();
                //System.out.println("Just set RetrofitClient.gottenTokenAfterLoginOrRefresh to ------------> " + RetrofitClient.gottenTokenAfterLoginOrRefresh );
                //Then try to login after completion of Authetication successful
                mLogin = new Login("admin", "admin");
                userViewModel.login(mLogin);
            }
        });
    }

    void processWhenLoginResultForLoginChanged() {
        userViewModel.getLoginResultForLogin().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                System.out.println("LoginResultForLogin Data Changed............................................");
                System.out.println("loginResult-------------------------------------------> " + loginResult);
                mLoginResult = loginResult;

                String chosenProfile = (binding.profilSpinnerEdtxt).getText().toString();
                System.out.println("chosenProfile--------------------------------------> = " + chosenProfile);

                if(chosenProfile.equalsIgnoreCase("Moniteur"))  {
                    navigateToEcranPrincipalMonitoringActivityList();
                }
                if(chosenProfile.equalsIgnoreCase("Patient"))  {
                    navigateToEcranPrincipalActivityList();
                }
                if(chosenProfile.equalsIgnoreCase("Docteur"))  {
                    navigateToEcranDocteurActivityList();
                }
                if(chosenProfile.equalsIgnoreCase("Administrateur"))  {
                    navigateToEcranAdministrateurActivityList();
                }
            }
        });
    }

    void processWhenListParamForDemandeAllParamsChanged() {
        userViewModel.getListParamForDemandeAllParams().observe(this, new Observer<List<Param>>() {
            @Override
            public void onChanged(List<Param> params) {
                if(params == null){
                    System.out.println(" ERROR ListParamForDemandeAllParams Data Changed............................................");
                    binding.txtInputLayoutEdtErroMessage.setVisibility(View.VISIBLE);
                    binding.textErroMessage.setText("Connection Impossible, Verifiez votre connetivite ou Remontez le probleme...");
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    hanldeRefreshButtonWhenNotOK();
                }else {
                    System.out.println("ListParamForDemandeAllParams Data Changed............................................");
                    mListParams = params;
                    adjustViewComponentsStatusAfterParamSyncCompleted();
                    hanldeRefreshButtonWhenOK();
                }
            }
        });
    }

    void adjustViewComponentsStatusBeforeParamSyncCompleted() {
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

    void adjustViewComponentsStatusAfterParamSyncCompleted() {
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