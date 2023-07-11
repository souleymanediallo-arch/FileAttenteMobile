package com.soul.fileattente.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.adapters.ServiceDestinationListData;
import com.soul.fileattente.adapters.ServiceDestinationListDataAdapter;
import com.soul.fileattente.databinding.ActivityEcranPrincipalListBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class EcranPrincipalActivityList extends AppCompatActivity {

    private UserViewModel userViewModel;
    private ActivityEcranPrincipalListBinding binding;
    private ServiceDestinationListDataAdapter serviceDestinationListDataAdapter;
    private ArrayList<ServiceDestinationListData> serviceDestinationListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEcranPrincipalListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting GlobalSetOfExtra
        GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra) getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());

        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalActivityList.this).get(UserViewModel.class);

        DemandeGeneric demandeGeneric = new DemandeGeneric();
        demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer
        demandeGeneric.setDeviceId("000000000000");//Infomations à calculer

        userViewModel.demandeAllServicesDestination(demandeGeneric);
        binding.progressBar.setVisibility(View.VISIBLE);

        //Process whenever there is a change
        processWhenListServiceDestinationForDemandeAllServicesDestinationChanged();

        //binding.recyclerView. - Managing the list of service List
        serviceDestinationListData = new ArrayList<>();
        serviceDestinationListDataAdapter = new ServiceDestinationListDataAdapter(serviceDestinationListData, mGlobalSetOfExtra);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceDestinationListDataAdapter);
    }

    void processWhenListServiceDestinationForDemandeAllServicesDestinationChanged() {
        userViewModel.getListServiceDestinationForDemandeAllServicesDestination().observe(this, new Observer<List<ServiceDestination>>() {
            @Override
            public void onChanged(List<ServiceDestination> serviceDestinations) {
                System.out.println("ListServiceDestinationForDemandeAllServicesDestination Data Changed............................................" + serviceDestinations + "---------");
                serviceDestinationListData.clear();
                for (ServiceDestination serviceDestination : serviceDestinations) {
                    int imageId = Utils.getRihtImageIdGivenServiceName(serviceDestination.getNomServiceDestination());
                    serviceDestinationListData.add(new ServiceDestinationListData(serviceDestination, imageId));
                }
                serviceDestinationListDataAdapter.notifyDataSetChanged();
                binding.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}