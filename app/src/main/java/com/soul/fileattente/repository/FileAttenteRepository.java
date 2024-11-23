package com.soul.fileattente.repository;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.Etablissement;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.model.SmsMessageRetour;
import com.soul.fileattente.viewmodel.UserViewModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileAttenteRepository {

    static FileAttenteRepository instance;

    public static FileAttenteRepository getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return new FileAttenteRepository();
        }
    }

    public void authenticate(Login login) {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().authenticate(login);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getAutheticationResultForAuthenticate().postValue(response.body());
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void login(Login login) {
        Call<Login> call = RetrofitClient.getInstance().getMyApi().login(login);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getLoginResultForLogin().postValue(response.body());
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void demandeNumerosSuivant(DemandeGeneric demandeGeneric) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().demandeNumerosSuivant(demandeGeneric);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().postValue(response.body());
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                UserViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().postValue(null);
                System.out.println(t.getMessage());
            }
        });
    }


    public void getAllNumerosSuivants() {
        Call<List<NumeroSuivantFile>> call = RetrofitClient.getInstance().getMyApi().getAllNumerosSuivants();

        call.enqueue(new Callback<List<NumeroSuivantFile>>() {
            @Override
            public void onResponse(Call<List<NumeroSuivantFile>> call, Response<List<NumeroSuivantFile>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getListNumeroSuivantFileForGetAllNumerosSuivants().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NumeroSuivantFile>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void demanderEtablissement(DemandeGeneric demandeGeneric){
        Call<Etablissement> call  = RetrofitClient.getInstance().getMyApi().demanderEtablissement(demandeGeneric);

        call.enqueue(new Callback<Etablissement>() {
            @Override
            public void onResponse(Call<Etablissement> call, Response<Etablissement> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getEtablissementFordemanderEtablissement().postValue(response.body());
            }

            @Override
            public void onFailure(Call<Etablissement> call, Throwable t) {
                System.out.println(t.getMessage());
                UserViewModel.getEtablissementFordemanderEtablissement().postValue(null);
            }
        });
    }

    public void demandeAllServicesDestination(DemandeGeneric demandeGeneric) {
        Call<List<ServiceDestination>> call = RetrofitClient.getInstance().getMyApi().demandeAllServicesDestination(demandeGeneric);

        call.enqueue(new Callback<List<ServiceDestination>>() {
            @Override
            public void onResponse(Call<List<ServiceDestination>> call, Response<List<ServiceDestination>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getListServiceDestinationForDemandeAllServicesDestination().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ServiceDestination>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void demandeAggregatAllServicesDestinationNumeroFiles(DemandeGeneric demandeGeneric) {
        Call<List<ServiceAGG>> call = RetrofitClient.getInstance().getMyApi().demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);

        System.out.println("-------------------------------------> Data Changed in demandeAggregatAllServicesDestinationNumeroFiles " );
        call.enqueue(new Callback<List<ServiceAGG>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ServiceAGG>> call, Response<List<ServiceAGG>> response) {
                System.out.println("-------------------------------> response.code() : " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                System.out.println("-------------------------------> response.body().isEmpty() : " + response.body().isEmpty());
                System.out.println("-------------------------------> response.body().size() : " + response.body().size());
                response.body().forEach(s -> System.out.println(s));
                UserViewModel.getListForDemandeAggregatAllServicesDestinationNumeroFiles().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ServiceAGG>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void demandeMedecinAggregatAllServicesDestinationNumeroFiles(DemandeGeneric demandeGeneric) {
        Call<List<ServiceAGG>> call = RetrofitClient.getInstance().getMyApi().demandeMedecinAggregatAllServicesDestinationNumeroFiles(demandeGeneric);

        System.out.println("-------------------------------------> Data Changed in demandeAggregatAllServicesDestinationNumeroFiles " );
        call.enqueue(new Callback<List<ServiceAGG>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<ServiceAGG>> call, Response<List<ServiceAGG>> response) {
                System.out.println("-------------------------------> response.code() : " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                System.out.println("-------------------------------> response.body().isEmpty() : " + response.body().isEmpty());
                System.out.println("-------------------------------> response.body().size() : " + response.body().size());
                response.body().forEach(s -> System.out.println(s));
                UserViewModel.getListForDemandeMedecinAggregatAllServicesDestinationNumeroFiles().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ServiceAGG>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void appelerNumero(DemandeGeneric demandeGeneric) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().appeler_numero_suivant_files(demandeGeneric);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForAppelerNumero().postValue(response.body());
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void appelerMedecinNumero(DemandeGeneric demandeGeneric) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().appeler_medecin_numero_suivant_files(demandeGeneric);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForMedecinAppelerNumero().postValue(response.body());
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    public void annulerAppelNumero(DemandeGeneric demandeGeneric) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().annuler_numero_precedent_files(demandeGeneric);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForAnnulerAppelNumero().postValue(response.body());
            }
            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void annulerMedecinAppelNumero(DemandeGeneric demandeGeneric) {
        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().annuler_medecin_numero_precedent_files(demandeGeneric);

        call.enqueue(new Callback<NumeroSuivantFile>() {
            @Override
            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForMedecinAnnulerAppelNumero().postValue(response.body());
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void demandeAllNumerosSuivants(DemandeGeneric demandeGeneric) {
        Call<List<NumeroSuivantFile>> call = RetrofitClient.getInstance().getMyApi().demandeAllNumerosSuivants(demandeGeneric);

        call.enqueue(new Callback<List<NumeroSuivantFile>>() {
            @Override
            public void onResponse(Call<List<NumeroSuivantFile>> call, Response<List<NumeroSuivantFile>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getListNumeroSuivantFileFordemandeAllNumerosSuivants().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NumeroSuivantFile>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void sendSmsNotification(NumeroSuivantFile numeroSuivantFile) {
        Call<SmsMessageRetour> call = RetrofitClient.getInstance().getMyApi().sendSmsNotification(numeroSuivantFile);

        call.enqueue(new Callback<SmsMessageRetour>() {
            @Override
            public void onResponse(Call<SmsMessageRetour> call, Response<SmsMessageRetour> response) {
                System.out.println("getStrRetourSendSmsNotification-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getStrRetourSendSmsNotification().postValue(response.body());
            }

            @Override
            public void onFailure(Call<SmsMessageRetour> call, Throwable t) {
                System.out.println("getStrRetourSendSmsNotification Error -------------------------------> "+ t.getMessage());
                SmsMessageRetour smsMessageRetour = new SmsMessageRetour("error_sending");
                UserViewModel.getStrRetourSendSmsNotification().postValue(smsMessageRetour);
            }
        });
    }
}
