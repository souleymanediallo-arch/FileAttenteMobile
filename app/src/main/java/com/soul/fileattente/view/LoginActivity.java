package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.model.User;
import com.soul.fileattente.viewmodel.UserViewModel;

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

        //Be prepared to process the changes of the values for var in viewModel
        processWhenDataChanged();

        //Process with initialisation
        userViewModel.init();
    }

    void processTaskWhenloginButtonClicked(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.doAgain();

                //Profiter pour lancer une attente de 9sec avant de
                waitForAWhile(9000L);
            }
        });
    }

    void processWhenDataChanged(){
        userViewModel.getmUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Update UI Here
                binding.edtxtUsername.setText(user.getUserName());
                binding.edtxtPassword.setText(user.getUserPwd());
                Toast.makeText(LoginActivity.this, "Data has Changed", Toast.LENGTH_SHORT).show();
                //just log this
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Name = " + user.getUserName());
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Pwd = " + user.getUserPwd());
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Status = " + user.isUserStatus());
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