package com.soul.fileattente.view;

//public class EcranServiceDestinationActivityList {
//}

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.soul.fileattente.adapters.ServiceDestinationSyncListDataAdapter;
import com.soul.fileattente.databinding.ActivityServiceDestinationBinding;
import com.soul.fileattente.model.ServiceDestination;

import java.util.ArrayList;
import java.util.List;

public class EcranServiceDestinationActivityList extends AppCompatActivity {

    private ActivityServiceDestinationBinding binding;
    private List<ServiceDestination> services = new ArrayList<>();
    private ServiceDestinationSyncListDataAdapter adapter;

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDestinationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ServiceDestinationSyncListDataAdapter(services, new ServiceDestinationSyncListDataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ServiceDestination service, int position) {
                Intent intent = new Intent(EcranServiceDestinationActivityList.this, EcranServiceDestinationEditActivity.class);
                intent.putExtra("service", service);
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_EDIT);
            }
        });

        binding.recyclerViewServices.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewServices.setAdapter(adapter);

        binding.fabAddService.setOnClickListener(v -> {
            Intent intent = new Intent(EcranServiceDestinationActivityList.this, EcranServiceDestinationEditActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        // Load from data source (DB, server, etc)
        loadData();
    }

    private void loadData() {
        // TODO load actual data - sample dummy data
        ServiceDestination serviceDestination = new ServiceDestination();
        serviceDestination.setIdService("1");
        serviceDestination.setNomServiceDestination("Medecine");
        serviceDestination.setLibelleServiceDestination("Medecine General");
        serviceDestination.setStatutServiceDestination("true");
        serviceDestination.setTempsAttenteEstime(50L);
        serviceDestination.setTempsAttenteMoyen(50L);
        serviceDestination.setPrefixeServiceAAfficher("MED");
        serviceDestination.setEtablissementAssocie("674999cad5d72103924e0d46");
        //services.add(new ServiceDestination("1", "Medecine", "MED", "Medecine General", true, 500, 500, "674999cad5d72103924e0d46"));
        services.add(serviceDestination);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ServiceDestination service = (ServiceDestination) data.getSerializableExtra("service");
            int position = data.getIntExtra("position", -1);
            if (requestCode == REQUEST_ADD) {
                services.add(service);
                adapter.notifyItemInserted(services.size() - 1);
            } else if (requestCode == REQUEST_EDIT && position != -1) {
                services.set(position, service);
                adapter.notifyItemChanged(position);
            }
        }
    }
}
