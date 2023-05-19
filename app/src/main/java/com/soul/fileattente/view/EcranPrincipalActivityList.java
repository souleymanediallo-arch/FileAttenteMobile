package com.soul.fileattente.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityEcranPrincipalListBinding;
import com.soul.fileattente.model.DemandeService;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.viewmodel.UserViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
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
        //Getting Instance of the viewModel that will manage the Business of the aapplication
        userViewModel = new ViewModelProvider(EcranPrincipalActivityList.this).get(UserViewModel.class);
        userViewModel.demandeAllServicesDestination(new DemandeService("Vision Medicale Coumba","0122455789632111441251","2023-05-13T10:35:02.678Z"));
        processWhenListServiceDestination();
        //Managing the list of service List
        serviceDestinationListData = new ArrayList<>();
        serviceDestinationListDataAdapter = new ServiceDestinationListDataAdapter(serviceDestinationListData);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(serviceDestinationListDataAdapter);
    }

    void processWhenListServiceDestination(){
        userViewModel.getmListServiceDestination().observe(this, new Observer<List<ServiceDestination>>() {
            @Override
            public void onChanged(List<ServiceDestination> serviceDestinations) {
                for(ServiceDestination serviceDestination: serviceDestinations){
                    serviceDestinationListData.add(new ServiceDestinationListData(serviceDestination, R.drawable.ic_baseline_timer_24));
                }
                serviceDestinationListDataAdapter.notifyDataSetChanged();
            }
        });
    }
}