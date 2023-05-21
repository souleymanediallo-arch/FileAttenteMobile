package com.soul.fileattente.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.R;
import com.soul.fileattente.adapters.ServiceDestinationListData;
import com.soul.fileattente.adapters.ServiceDestinationMonitoringListDataAdapter;
import com.soul.fileattente.databinding.ActivityEcranPrincipalMonitoringListBinding;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class EcranPrincipalMonitoringActivityList extends AppCompatActivity {

    private UserViewModel userViewModel;
    private ActivityEcranPrincipalMonitoringListBinding binding;
    private ServiceDestinationMonitoringListDataAdapter serviceDestinationMonitoringListDataAdapter;
    private ArrayList<ServiceDestinationListData> serviceDestinationListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEcranPrincipalMonitoringListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Getting GlobalSetOfExtra
        GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA);

        System.out.println("------------> " + mGlobalSetOfExtra.mLogin.toString());

        System.out.println("------------> " + mGlobalSetOfExtra.mAuthenticationResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mLoginResult.toString());
        System.out.println("------------> " + mGlobalSetOfExtra.mListParams.toString());

        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalMonitoringActivityList.this).get(UserViewModel.class);
        userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba", "0122455789632111441251", "2023-05-13T10:35:02.678Z"));

        //Process whenever there is a change
        processWhenListServiceDestinationForDemandeAllServicesDestinationChanged();

        //binding.recyclerView.
        //Managing the list of service List
        serviceDestinationListData = new ArrayList<>();
        serviceDestinationMonitoringListDataAdapter = new ServiceDestinationMonitoringListDataAdapter(serviceDestinationListData, mGlobalSetOfExtra);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceDestinationMonitoringListDataAdapter);
    }

    void processWhenListServiceDestinationForDemandeAllServicesDestinationChanged() {
        userViewModel.getListServiceDestinationForDemandeAllServicesDestination().observe(this, new Observer<List<ServiceDestination>>() {
            @Override
            public void onChanged(List<ServiceDestination> serviceDestinations) {
                System.out.println("ListServiceDestinationForDemandeAllServicesDestination Data Changed............................................");
                serviceDestinationListData.clear();
                for (ServiceDestination serviceDestination : serviceDestinations) {
                    serviceDestinationListData.add(new ServiceDestinationListData(serviceDestination, R.drawable.ic_baseline_timer_24));
                }
                serviceDestinationMonitoringListDataAdapter.notifyDataSetChanged();
            }
        });
    }
}