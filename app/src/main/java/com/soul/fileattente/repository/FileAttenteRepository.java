package com.soul.fileattente.repository;

import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Birthday;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileAttenteRepository {

    static FileAttenteRepository instance;

    public static FileAttenteRepository getInstance() {
        if(instance != null) {
            return instance;
        }else{
            return new FileAttenteRepository();
        }
    }

    public void authenticate(Login login) {
        Call<AutheticationResult> call = RetrofitClient.getInstance().getMyApi().authenticate(login);

        call.enqueue(new Callback<AutheticationResult>() {
            @Override
            public void onResponse(Call<AutheticationResult> call, Response<AutheticationResult> response) {
                //LoginResult loginResult = response.body();
//                System.out.println("-------------------------------> " + loginResult);
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body() );
            }

            @Override
            public void onFailure(Call<AutheticationResult> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }

    public void getAllbirthdays(){
        Call<List<Birthday>> call = RetrofitClient.getInstance().getMyApi().getAllbirthdays();

        call.enqueue(new Callback<List<Birthday>>() {
            @Override
            public void onResponse(Call<List<Birthday>> call, Response<List<Birthday>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body() );
            }

            @Override
            public void onFailure(Call<List<Birthday>> call, Throwable t) {

            }
        });
    }


    public void login(Login login) {
        Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().login(login);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                //LoginResult loginResult = response.body();
//                System.out.println("-------------------------------> " + loginResult);
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body() );
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }


    public void demandeNumerosSuivant(DemandeNumeroFile demandeNumeroFile) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().demandeNumerosSuivant(demandeNumeroFile);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
//                NumeroSuivantFile numeroSuivantFile = response.body();
//                System.out.println("-------------------------------> " + numeroSuivantFile);
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body() );
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }

    public void getAllNumerosSuivants() {
        Call<List<NumeroSuivantFile>> call = RetrofitClient.getInstance().getMyApi().getAllNumerosSuivants();

        call.enqueue(new Callback<List<NumeroSuivantFile>>() {
            @Override
            public void onResponse(Call<List<NumeroSuivantFile>> call, Response<List<NumeroSuivantFile>> response) {
                List<NumeroSuivantFile> myheroList = response.body();
                UserViewModel.mListResults.postValue(myheroList);
            }
            @Override
            public void onFailure(Call<List<NumeroSuivantFile>> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }
}
