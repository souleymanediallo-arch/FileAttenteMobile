package com.soul.fileattente.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.soul.fileattente.databinding.ActivityServiceDestinationEditBinding;
import com.soul.fileattente.model.ServiceDestination;

public class EcranServiceDestinationEditActivity extends AppCompatActivity {

    private ActivityServiceDestinationEditBinding binding;
    private ServiceDestination service;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDestinationEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get service object passed for edit or create new
        Intent intent = getIntent();
        service = (ServiceDestination) intent.getSerializableExtra("service");
        position = intent.getIntExtra("position", -1);

        if (service != null) {
            fillForm(service);
        } else {
            service = new ServiceDestination();
        }

        binding.btnSaveService.setOnClickListener(v -> {
            if (validateInputs()) {
                updateServiceFromForm();
                Intent result = new Intent();
                result.putExtra("service", service);
                result.putExtra("position", position);
                setResult(RESULT_OK, result);
                finish();
            } else {
                Toast.makeText(this, "SVP, Veuillez remplir tous les champs...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillForm(ServiceDestination s) {
        binding.editNomService.setText(s.getNomServiceDestination());
        binding.editPrefixe.setText(s.getPrefixeServiceAAfficher());
        binding.editLibelle.setText(s.getLibelleServiceDestination());
        binding.switchStatut.setChecked(s.getStatutServiceDestination().trim().equalsIgnoreCase("true"));
        binding.editTempsAttenteMoyen.setText(String.valueOf(s.getTempsAttenteMoyen()));
        binding.editTempsAttenteEstime.setText(String.valueOf(s.getTempsAttenteEstime()));
        binding.editEtablissementAssocie.setText(s.getEtablissementAssocie());
    }

    private boolean validateInputs() {
        return !binding.editNomService.getText().toString().trim().isEmpty() &&
                !binding.editPrefixe.getText().toString().trim().isEmpty() &&
                !binding.editLibelle.getText().toString().trim().isEmpty() &&
                !binding.editTempsAttenteMoyen.getText().toString().trim().isEmpty() &&
                !binding.editTempsAttenteEstime.getText().toString().trim().isEmpty() &&
                !binding.editEtablissementAssocie.getText().toString().trim().isEmpty();
    }

    private void updateServiceFromForm() {
        service.setNomServiceDestination(binding.editNomService.getText().toString());
        service.setPrefixeServiceAAfficher(binding.editPrefixe.getText().toString());
        service.setLibelleServiceDestination(binding.editLibelle.getText().toString());
        service.setStatutServiceDestination(binding.switchStatut.isChecked()?"true":"false");
        service.setTempsAttenteMoyen(Long.parseLong(binding.editTempsAttenteMoyen.getText().toString()));
        service.setTempsAttenteEstime(Long.parseLong(binding.editTempsAttenteEstime.getText().toString()));
        service.setEtablissementAssocie(binding.editEtablissementAssocie.getText().toString());
    }
}
