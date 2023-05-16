package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
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
    //private Observer<User> userObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //binding.setLifecycleOwner(this);

        //Precess Btnlogin Click
        processTaskWhenloginButtonClicked();

        //Getting Instance of the viewModel
        userViewModel = new ViewModelProvider(LoginActivity.this).get(UserViewModel.class);
        userViewModel.init();
        //userViewModel.doAgain();

        final Observer<User> userObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Update UI Here
                binding.edtxtUsername.setText(user.getUserName());
                binding.edtxtPassword.setText(user.getUserPwd());
                Toast.makeText(LoginActivity.this, "Data has Changed --> " + user.toString(), Toast.LENGTH_LONG).show();
                //just log this
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Name = " + user.getUserName());
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Pwd = " + user.getUserPwd());
                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Status = " + user.isUserStatus());
            }
        };
        userViewModel.getmUser().observe(LoginActivity.this, userObserver);
        //Be prepared to process the changes of the values for var in viewModel
        //processWhenDataChanged();


    }

    void processTaskWhenloginButtonClicked(){
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.doAgain();
//                //System.out.printf("------------------------------------> ");
//                Toast.makeText(LoginActivity.this, "Just Clicked", Toast.LENGTH_SHORT).show();
//                //binding.btnLogin.setText("Just clicked ! ");
//                User sampleUser = new User();
//                sampleUser.setUserName("ZAHRA");
//                sampleUser.setUserPwd("Aminata");
//                sampleUser.setUserStatus(true);
//                userViewModel.getmUser().setValue(sampleUser);
            }
        });
    }

    void processWhenDataChanged(){

//        userObserver = new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                //Update UI Here
//                binding.edtxtUsername.setText(user.getUserName());
//                binding.edtxtPassword.setText(user.getUserPwd());
//                Toast.makeText(LoginActivity.this, "Data has Changed", Toast.LENGTH_SHORT).show();
//                //just log this
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Name = " + user.getUserName());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Pwd = " + user.getUserPwd());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Status = " + user.isUserStatus());
//            }
//        };
//        userViewModel.getmUser().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                //Update UI Here
//                binding.edtxtUsername.setText(user.getUserName());
//                binding.edtxtPassword.setText(user.getUserPwd());
//                Toast.makeText(LoginActivity.this, "Data has Changed", Toast.LENGTH_SHORT).show();
//                //just log this
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Name = " + user.getUserName());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Pwd = " + user.getUserPwd());
//                Logger.getLogger(getLocalClassName()).log(Level.INFO,"------------------------------------> I got changed and my current value is Status = " + user.isUserStatus());
//            }
//        });
    }
}