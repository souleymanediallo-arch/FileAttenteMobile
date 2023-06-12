package com.soul.fileattente.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.R;
import com.soul.fileattente.adapters.NumeroSuivantFileListData;
import com.soul.fileattente.adapters.NumeroSuivantFileMonitoringListDataAdapter;
import com.soul.fileattente.adapters.ServiceDestinationListData;
import com.soul.fileattente.adapters.ServiceDestinationMonitoringListDataAdapter;
import com.soul.fileattente.databinding.ActivityEcranNumeroSuivantFilesMonitorListBinding;
import com.soul.fileattente.databinding.ActivityEcranPrincipalMonitoringListBinding;
import com.soul.fileattente.model.DemandeNumSuiv;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.NumeroSuivantFile;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class EcranPrincipalMonitoringNumeroFileActivityList extends AppCompatActivity {

    public static UserViewModel userViewModel;
    private ActivityEcranNumeroSuivantFilesMonitorListBinding binding;
    private NumeroSuivantFileMonitoringListDataAdapter numeroSuivantFileMonitoringListDataAdapter;
    private ArrayList<NumeroSuivantFileListData> numeroSuivantFileListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEcranNumeroSuivantFilesMonitorListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting GlobalSetOfExtra
        GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());

        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());

        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalMonitoringNumeroFileActivityList.this).get(UserViewModel.class);
        //userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));
        //userViewModel.getAllNumerosSuivants(new DemandeService("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));
        //userViewModel.getAllNumerosSuivants();
        userViewModel.demandeAllNumerosSuivants(new DemandeNumSuiv("Vision Medicale Coumba","ServiceNotToBeCpnsidered", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));


        //Process whenever there is a change
        //processWhenListServiceDestinationForDemandeAllServicesDestinationChanged();
        processWhenListNumerosFileForDemandeAllNumerosFileChanged();

        //binding.recyclerView.
        //Managing the list of service List
        numeroSuivantFileListData = new ArrayList<>();
        numeroSuivantFileMonitoringListDataAdapter = new NumeroSuivantFileMonitoringListDataAdapter(numeroSuivantFileListData, mGlobalSetOfExtra);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(numeroSuivantFileMonitoringListDataAdapter);
    }

//    void processWhenListServiceDestinationForDemandeAllServicesDestinationChanged() {
//        userViewModel.getListServiceDestinationForDemandeAllServicesDestination().observe(this, new Observer<List<ServiceDestination>>() {
//            @Override
//            public void onChanged(List<ServiceDestination> serviceDestinations) {
//                System.out.println("ListServiceDestinationForDemandeAllServicesDestination Data Changed............................................" + serviceDestinations + "******");
//                numeroSuivantFileListData.clear();
//                for (ServiceDestination serviceDestination : serviceDestinations) {
//                    numeroSuivantFileListData.add(new NumeroSuivantFileListData(serviceDestination, R.drawable.ic_baseline_timer_24));
//                }
//                numeroSuivantFileMonitoringListDataAdapter.notifyDataSetChanged();
//            }
//        });
//    }

    void processWhenListNumerosFileForDemandeAllNumerosFileChanged(){
        userViewModel.getListNumeroSuivantFileFordemandeAllNumerosSuivants().observe(this, new Observer<List<NumeroSuivantFile>>() {
            @Override
            public void onChanged(List<NumeroSuivantFile> numeroSuivantFiles) {
                System.out.println("ListServiceDestinationForDemandeAllServicesDestination Data Changed............................................" + numeroSuivantFiles + "******");
                numeroSuivantFileListData.clear();
                for (NumeroSuivantFile numeroSuivantFile : numeroSuivantFiles) {
                    numeroSuivantFileListData.add(new NumeroSuivantFileListData(numeroSuivantFile, R.drawable.ic_baseline_timer_24));
                }
                numeroSuivantFileMonitoringListDataAdapter.notifyDataSetChanged();
            }
        });
    }
}