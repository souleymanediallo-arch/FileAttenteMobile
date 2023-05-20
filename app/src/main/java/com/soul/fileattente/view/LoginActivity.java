package com.soul.fileattente.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    private Login mLogin;
    private AutheticationResult mAuthenticationResult;
    private LoginResult mLoginResult;
    private List<Param> mListParams;

    private GlobalSetOfExtra mGlobalSetOfExtra;

    TextToSpeech  textToSpeech;

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

        //Text To Speech
        translateTextToSpeech();
        processTaskWhenTextToSpeechButtonClicked();
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
//                navigateToEcranPrincipalActivityList();
                navigateToEcranPrincipalMonitoringActivityList();
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

    //------------------------------------------------------------------------------------
    // create an object textToSpeech and adding features into it
    void translateTextToSpeech(){
        textToSpeech = new TextToSpeech(this.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // if No error is found then only it will run
                if(i!=TextToSpeech.ERROR){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.FRANCE);
                }
            }
        });
    }

    void processTaskWhenTextToSpeechButtonClicked(){
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                //Render text over speech
                String textToRenderOverVoice = "Service Gynecologie, Numero 856 c'est à vous...";
                textToSpeech.speak(textToRenderOverVoice,TextToSpeech.QUEUE_FLUSH,null);
//                //Queing messages and read them one after the other
//                for(int i=511; i<=516; i++) {
//                    textToSpeech.speak("Service Gynecologie, Numero "+i+" c'est à vous...", TextToSpeech.QUEUE_ADD, null);
//                }

//                //Sample Sending SMS Message
//                String phoneNumber = "+221766752276";
//                String smsMessage = "Message to Send to subscriber";
//                sendTextAsSms(phoneNumber, smsMessage);

//                //Sample Sending MAIL Message
//                String[] to = {"gsouleymane.diallo@gmail.com"};
//                String[] cc = {"clinique.coumba@gmail.com"};
//                String subject ="File Attente..";
//                String message ="Service Gyneco Numero 754";
//                sendTextAsEmail(to, cc, subject, message);
            }
        });
    }

    //https://www.tutlane.com/tutorial/android/android-send-sms-with-examples
    void sendTextAsSms(String mobileNumber, String textMessage) {
        try{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("smsto:"));
            i.setType("vnd.android-dir/mms-sms");
            i.putExtra("address", mobileNumber);
            i.putExtra("sms_body",textMessage);
            startActivity(Intent.createChooser(i, "Send sms via:"));
        }
        catch(Exception e){
            Toast.makeText(this, "Echec envoi sms...", Toast.LENGTH_SHORT).show();
        }
    }

    //https://www.tutorialspoint.com/android/android_sending_email.htm
    protected void sendTextAsEmail(String[] to, String[] cc, String  subject, String message) {
    //protected void sendTextAsEmail() {
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Toast.makeText(this, "Finished sending email...", Toast.LENGTH_SHORT).show();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    //https://www.geeksforgeeks.org/how-to-send-message-on-whatsapp-in-android/
    private void sendTextAsWhatsAppMessage(String message)
    {
        // Creating new intent
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");

        // Give your message here
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // Checking whether Whatsapp
        // is installed or not
        if (intent.resolveActivity(getPackageManager()) == null) {
            Toast.makeText(this,"Please install whatsapp first.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Starting Whatsapp
        startActivity(intent);
    }

    //https://www.vogella.com/tutorials/AndroidIntent/article.html
    public void openWhatsApp(View view){
        try {
            String text = "This is a test";// Replace with your message.

            String toNumber = "xxxxxxxxxx"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//    ------------------------------------------------------------------------------------
}