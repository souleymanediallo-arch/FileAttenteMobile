package com.soul.fileattente.api;

import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Birthday;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

//    String BASE_URL = "https://18c8ff90-b4d1-4f45-be00-9cb07599f97c.mock.pstmn.io/fileattente/";
    String BASE_URL = "http://192.168.1.142:8080/api/";


    @POST("authenticate")
    Call<AutheticationResult> authenticate(@Body Login login);

    @GET("birthdays")
    Call<List<Birthday>> getAllbirthdays();

    @POST("login")
    Call<LoginResult> login(@Body Login login);

    @POST("demandenumerossuivant")
    Call<NumeroSuivantFile> demandeNumerosSuivant(@Body DemandeNumeroFile demandeNumeroFile);

    @GET("allnumerossuivants")
    Call<List<NumeroSuivantFile>> getAllNumerosSuivants();



}