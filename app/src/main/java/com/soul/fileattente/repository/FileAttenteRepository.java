package com.soul.fileattente.repository;

import com.soul.fileattente.api.RetrofitClient;
import com.soul.fileattente.model.AutheticationResult;
import com.soul.fileattente.model.Birthday;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeNumeroFile;
import com.soul.fileattente.model.DemandeParam;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.Login;
import com.soul.fileattente.model.LoginResult;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.Param;
import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class FileAttenteRepository {

    static FileAttenteRepository instance;

    public static FileAttenteRepository getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return new FileAttenteRepository();
        }
    }

    public void getAllbirthdays() {
        Call<List<Birthday>> call = RetrofitClient.getInstance().getMyApi().getAllbirthdays();

        call.enqueue(new Callback<List<Birthday>>() {
            @Override
            public void onResponse(Call<List<Birthday>> call, Response<List<Birthday>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
            }

            @Override
            public void onFailure(Call<List<Birthday>> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }

    public void authenticate(Login login) {
        Call<AutheticationResult> call = RetrofitClient.getInstance().getMyApi().authenticate(login);

        call.enqueue(new Callback<AutheticationResult>() {
            @Override
            public void onResponse(Call<AutheticationResult> call, Response<AutheticationResult> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getAutheticationResultForAuthenticate().postValue(response.body());
            }

            @Override
            public void onFailure(Call<AutheticationResult> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }

    public void login(Login login) {
        Call<LoginResult> call = RetrofitClient.getInstance().getMyApi().login(login);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getLoginResultForLogin().postValue(response.body());
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
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().postValue(response.body());
            }

            @Override
            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
                UserViewModel.getNumeroSuivantFileForDemandeNumerosSuivant().postValue(null);
                System.out.printf(t.getMessage());
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
                System.out.printf(t.getMessage());
            }
        });
    }

    //https://stackoverflow.com/questions/24562716/how-to-retry-http-requests-with-okhttp-retrofit
    //https://resilience4j.readme.io/v1.7.0/docs/retrofit#retry
    //https://itecnote.com/tecnote/android-how-to-retry-http-requests-with-okhttp-retrofit/
    public void demandeAllParams(DemandeGeneric demandeGeneric) {
        Call<List<Param>> call = RetrofitClient.getInstance().getMyApi().demandeAllParams(demandeGeneric);

        call.enqueue(new Callback<List<Param>>() {
            @Override
            public void onResponse(Call<List<Param>> call, Response<List<Param>> response) {
                System.out.println("-------------------------------> " + response.code() + "  >--<  \n" + response.toString() + "  --  \n" + response.body());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                UserViewModel.getListParamForDemandeAllParams().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Param>> call, Throwable t) {
                System.out.printf("-------------------------------> " + t.getMessage());
                //UserViewModel.getListParamForDemandeAllParams().postValue(response.body());
                UserViewModel.getListParamForDemandeAllParams().postValue(null);
                //call.
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
                System.out.printf(t.getMessage());
            }
        });
    }

    public void demandeAggregatAllServicesDestinationNumeroFiles(DemandeGeneric demandeGeneric) {
        Call<List<ServiceAGG>> call = RetrofitClient.getInstance().getMyApi().demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);

        call.enqueue(new Callback<List<ServiceAGG>>() {
            @Override
            public void onResponse(Call<List<ServiceAGG>> call, Response<List<ServiceAGG>> response) {
                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
                UserViewModel.getListForDemandeAggregatAllServicesDestinationNumeroFiles().postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ServiceAGG>> call, Throwable t) {
                System.out.printf(t.getMessage());
            }
        });
    }
//    public void appelerNumero(NumeroSuivantFile numeroSuivantFile) {
//        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().appelerNumero(numeroSuivantFile);
//
//        call.enqueue(new Callback<NumeroSuivantFile>() {
//            @Override
//            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
//                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
//                UserViewModel.getNumeroSuivantFileForAppelerNumero().postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
//                System.out.printf(t.getMessage());
//            }
//        });
//    }

    //public void appelerNumero(NumeroSuivantFile numeroSuivantFile) {
//    public void appelerNumero(Long idNumeroSuivantFile) {
//        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().appelerNumero(idNumeroSuivantFile);
//
//        call.enqueue(new Callback<NumeroSuivantFile>() {
//            @Override
//            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
//                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
//                UserViewModel.getNumeroSuivantFileForAppelerNumero().postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
//                System.out.printf(t.getMessage());
//            }
//        });
//    }
//
//    public void annulerAppelNumero(NumeroSuivantFile numeroSuivantFile) {
//        Call<NumeroSuivantFile> call = RetrofitClient.getInstance().getMyApi().annulerAppelNumero(numeroSuivantFile);
//
//        call.enqueue(new Callback<NumeroSuivantFile>() {
//            @Override
//            public void onResponse(Call<NumeroSuivantFile> call, Response<NumeroSuivantFile> response) {
//                System.out.println("-------------------------------> " + response.code() + "  --  \n" + response.toString() + "  --  \n" + response.body());
//                UserViewModel.getNumeroSuivantFileForAnnulerAppelNumero().postValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<NumeroSuivantFile> call, Throwable t) {
//                System.out.printf(t.getMessage());
//            }
//        });
//    }

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
                System.out.printf(t.getMessage());
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
                System.out.printf(t.getMessage());
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
                System.out.printf(t.getMessage());
            }
        });
    }
}
