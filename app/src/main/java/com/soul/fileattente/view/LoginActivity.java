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


    private void navigateToEcranPrincipalActivityList() {
        Intent intent = new Intent(LoginActivity.this, EcranPrincipalActivityList.class);

        mGlobalSetOfExtra = new GlobalSetOfExtra();
        mGlobalSetOfExtra.mLogin = mLogin;
        mGlobalSetOfExtra.mLoginResult = mLoginResult;
        mGlobalSetOfExtra.mAuthenticationResult = mAuthenticationResult;
        mGlobalSetOfExtra.mListParams = mListParams;
        intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
        LoginActivity.this.startActivity(intent);
    }

    private void navigateToEcranPrincipalMonitoringActivityList() {
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
            }
        });
    }

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
                //navigateToEcranPrincipalMonitoringActivityList();
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

    void adjustViewComponentsStatusBeforeParamSyncCompleted() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);
    }

    void adjustViewComponentsStatusAfterParamSyncCompleted() {
        binding.progressBar.setVisibility(View.GONE);
        binding.btnLogin.setEnabled(true);
    }
}