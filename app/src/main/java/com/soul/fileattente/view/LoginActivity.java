package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceDestination;
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

        //
        adjustViewComponentsStatusBeforeParamSyncCompleted();
        DemandeParam demandeParam = new DemandeParam("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z");
        userViewModel.demandeAllParams(demandeParam);
        //

        //Be prepared to process the changes of the values for var in viewModel: AutheticationResultForAuthenticate
        processWhenAutheticationResultForAuthenticateChanged();

        //Be prepared to process the changes of the values for var in viewModel: User
        processWhenLoginResultForLoginChanged();

        //Be prepared to process the changes of the values for var in viewModel: Results
        processWhenListParamForDemandeAllParamsChanged();

        //Precess Btnlogin Click
        processTaskWhenloginButtonClicked();
    }

//    private void navigateToServiceActivity(){
//        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);
//        //Set Token Id if needed for future use
//        //Transmit Object Login, Params,
//        intent.putExtra(Global.LOGIN, mLogin);
//        intent.putExtra(Global.LOGIN_RESULT, mLoginResult);
//        intent.putExtra(Global.AUTHENTICATION_RESULT, mAuthenticationResult);
//        //intent.putExtra(Global.LISTPARAMS, mListParams);
//        LoginActivity.this.startActivity(intent);
//    }

    private void navigateToEcranPrincipalActivityList(){
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mListParams = mListParams;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        LoginActivity.this.startActivity(intent);
    }

    private void navigateToEcranPrincipalMonitoringActivityList(){
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalMonitoringActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mListParams = mListParams;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        LoginActivity.this.startActivity(intent);
    }


    void processTaskWhenloginButtonClicked() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("-------------------------------------------------------------> I got clicked");

                mLogin = new Login("admin", "admin");
                userViewModel.authenticate(mLogin);

//                userViewModel.login(mLogin);

//                DemandeParam demandeParam = new DemandeParam("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z");
//                userViewModel.demandeAllParams(demandeParam);
            }
        });
    }

//    void processTaskWhenloginButtonClicked() { // Keep this for sample usage
//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("-------------------------------------------------------------> I got clicked");
//                //userViewModel.getAllbirthdays(); //Ajust with necassayr modications for BaseURL and so on...
//                userViewModel.authenticate(new Login("admin", "admin"));
//                userViewModel.login(new Login("docteur", "Passworddocteur"));
//                userViewModel.demandeAllParams(new DemandeParam("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));
//                //userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba","0122455789632111441251","2023-05-13T10:35:02.678Z"));
//                //userViewModel.demandeNumerosSuivant(new DemandeNumeroFile("Vision Medicale Coumba", "0122455789632111441251", "Pediatrie", "+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.getAllNumerosSuivants();
//                //userViewModel.appelerNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.annulerAppelNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
//                //userViewModel.demandeAllNumerosSuivants(new DemandeNumSuiv("Vision Medicale Coumba","Pediatrie","0122455789632111441251","2023-05-13T10:35:02.678Z"));
//            }
//        });
//    }

    void processWhenAutheticationResultForAuthenticateChanged() {
        userViewModel.getAutheticationResultForAuthenticate().observe(this, new Observer<AutheticationResult>() {
            @Override
            public void onChanged(AutheticationResult autheticationResult) {
                System.out.println("AutheticationResultForAuthenticate Data Changed............................................");
                mAuthenticationResult = autheticationResult;
                //Then try to login after completion of Authetication successful
                userViewModel.login(mLogin);
            }
        });
    }

    void processWhenLoginResultForLoginChanged() {
        userViewModel.getLoginResultForLogin().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                System.out.println("LoginResultForLogin Data Changed............................................");
                mLoginResult = loginResult;
                navigateToEcranPrincipalActivityList();
//                navigateToEcranPrincipalMonitoringActivityList();
            }
        });
    }

    void processWhenListParamForDemandeAllParamsChanged() {
        userViewModel.getListParamForDemandeAllParams().observe(this, new Observer<List<Param>>() {
            @Override
            public void onChanged(List<Param> params) {
                System.out.println("ListParamForDemandeAllParams Data Changed............................................");
                mListParams = params;
                adjustViewComponentsStatusAfterParamSyncCompleted();
            }
        });
    }

    void adjustViewComponentsStatusBeforeParamSyncCompleted(){
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);
    }

    void adjustViewComponentsStatusAfterParamSyncCompleted(){
        binding.progressBar.setVisibility(View.GONE);
        binding.btnLogin.setEnabled(true);
    }

    void processWhenListServiceDestinationForDemandeAllServicesDestinationChanged() {
        userViewModel.getListServiceDestinationForDemandeAllServicesDestination().observe(this, new Observer<List<ServiceDestination>>() {
            @Override
            public void onChanged(List<ServiceDestination> serviceDestinations) {
                System.out.println("ListServiceDestinationForDemandeAllServicesDestination Data Changed............................................");
            }
        });
    }

    void processWhenNumeroSuivantFileForDemandeNumerosSuivantChanged() {
        userViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                System.out.println("NumeroSuivantFileForDemandeNumerosSuivant Data Changed............................................");
            }
        });
    }

    void processWhenListNumeroSuivantFileForGetAllNumerosSuivantsChanged() {
        userViewModel.getListNumeroSuivantFileForGetAllNumerosSuivants().observe(this, new Observer<List<NumeroSuivantFile>>() {
            @Override
            public void onChanged(List<NumeroSuivantFile> numeroSuivantFiles) {
                System.out.println("ListNumeroSuivantFileForGetAllNumerosSuivants Data Changed............................................");
            }
        });
    }

    void processWhenNumeroSuivantFileForAppelerNumeroChanged() {
        userViewModel.getNumeroSuivantFileForAppelerNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                System.out.println("NumeroSuivantFileForAppelerNumero Data Changed............................................");
            }
        });
    }

    void processWhenNumeroSuivantFileForAnnulerAppelNumeroChanged() {
        userViewModel.getNumeroSuivantFileForAnnulerAppelNumero().observe(this, new Observer<NumeroSuivantFile>() {
            @Override
            public void onChanged(NumeroSuivantFile numeroSuivantFile) {
                System.out.println("NumeroSuivantFileForAnnulerAppelNumero Data Changed............................................");
            }
        });
    }

    void processWhenListNumeroSuivantFileFordemandeAllNumerosSuivantsChanged() {
        userViewModel.getListNumeroSuivantFileFordemandeAllNumerosSuivants().observe(this, new Observer<List<NumeroSuivantFile>>() {
            @Override
            public void onChanged(List<NumeroSuivantFile> numeroSuivantFiles) {
                System.out.println("NumeroSuivantFileForAnnulerAppelNumero Data Changed............................................");
            }
        });
    }
}