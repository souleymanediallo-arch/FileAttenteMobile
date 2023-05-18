package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.Demande;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.User;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using ViewBinding to manage Layout Components
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Precess Btnlogin Click
        processTaskWhenloginButtonClicked();

        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(LoginActivity.this).get(UserViewModel.class);

        //Be prepared to process the changes of the values for var in viewModel: User
        processWhenDataChanged();

        //Be prepared to process the changes of the values for var in viewModel: Results
        processWhenListHerosChanged();

        //Process with initialisation
        userViewModel.initFromViewModel();
    }

    void processTaskWhenloginButtonClicked(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //userViewModel.doAgain();
                //Profiter pour lancer une attente de 9sec avant de
                //waitForAWhile(9000L);
                System.out.println("-------------------------------------------------------------> I got clicked");
                //userViewModel.getAllNumerosSuivantsFromViewModel();
                //userViewModel.login(new Login("docteur", "Passworddocteur"));
                //userViewModel.demandeNumerosSuivant(new DemandeNumeroFile("Vision Medicale Coumba", "0122455789632111441251", "Pediatrie", "+221766752276", "2023-05-13T10:35:02.678Z" ));
                //userViewModel.authenticate(new Login("admin", "admin"));
                //userViewModel.getAllbirthdays(); //Ajust with necassayr modications for BaseURL and so on...
                //userViewModel.demandeAllParams(new DemandeParam("Vision Medicale Coumba","0122455789632111441251","2023-05-13T10:35:02.678Z"));
                //userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba","0122455789632111441251","2023-05-13T10:35:02.678Z"));
                //userViewModel.appelerNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
                //userViewModel.annulerAppelNumero(new NumeroSuivantFile("0001","2023-05-13T10:36:02.678Z","100", "300", "250", "Vision Medicale Coumba", "0122455789632111441251","Pediatrie","+221766752276", "2023-05-13T10:35:02.678Z" ));
                //userViewModel.demandeAllNumerosSuivants(new DemandeNumSuiv("Vision Medicale Coumba","Pediatrie","0122455789632111441251","2023-05-13T10:35:02.678Z"));
            }
        });
    }

//    {
//        "clinique": "Vision Medicale Coumba",
//            "deviceId": "0122455789632111441251",
//            "service": "Pediatrie",
//            "telephonePatient": "+221766752276",
//            "dateHeureDemande": "2023-05-13T10:35:02.678Z"
//    }
    void processWhenDataChanged(){
        userViewModel.getmUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Update UI Here
                binding.edtxtUsername.setText(user.getUsername());
                binding.edtxtPassword.setText(user.getPassword());
//                Toast.makeText(LoginActivity.this, "Data has Changed", Toast.LENGTH_SHORT).show();
                //just log this
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Name = " + user.getUserName());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Pwd = " + user.getUserPwd());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Status = " + user.isUserStatus());
            }
        });
    }

    void processWhenListHerosChanged(){
        userViewModel.getmListResults().observe(this, new Observer<List<NumeroSuivantFile>>() {
            @Override
            public void onChanged(List<NumeroSuivantFile> results) {
                Log.e("", "I got changed and my current value is Name = ");
                binding.edtxtUsername.setText(String.valueOf(results.size()));
                for(NumeroSuivantFile aResults: results){
                    System.out.printf("------------------------> "  + aResults.getNumeroSuivant());
                    Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> " + aResults.getNumeroSuivant());
                }
            }
        });
    }

    private void waitForAWhile(long delay){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("............................. I am done with the delay.......................");
                userViewModel.getmUser().postValue(new User("BA", "Dieynaba", true));
            }
        }, delay);
    }
}