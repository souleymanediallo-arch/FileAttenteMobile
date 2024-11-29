package com.soul.fileattente.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ActivityLoginBinding;
import com.soul.fileattente.databinding.NumeroSuivantFilesMonitorListItemBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.view.EcranPrincipalMonitoringActivityList;
import com.soul.fileattente.view.EcranPrincipalMonitoringNumeroFileActivityList;
import com.soul.fileattente.view.EcranPrincipalTraitementActivityList;
import com.soul.fileattente.view.EcranPrincipalTraitementNumeroFileActivityList;

import java.util.ArrayList;

public class NumeroSuivantFileMonitoringListDataAdapter extends RecyclerView.Adapter<NumeroSuivantFileMonitoringListDataAdapter.ViewHolder> {

    private GlobalSetOfExtra mGlobalSetOfExtra;
    private ArrayList<NumeroSuivantFileListData> mlistdata;
    private NumeroSuivantFilesMonitorListItemBinding binding;
    private String mDisplayScreen;

    public NumeroSuivantFileMonitoringListDataAdapter(ArrayList<NumeroSuivantFileListData> listdata, GlobalSetOfExtra globalSetOfExtra, String displayScreen) {
        this.mlistdata = listdata;
        this.mGlobalSetOfExtra = globalSetOfExtra;
        this.mDisplayScreen = displayScreen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.numero_suivant_files_monitor_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final NumeroSuivantFileListData numeroSuivantFileListData = mlistdata.get(position);
        holder.txtServiceDestination.setText(Utils.formatStringForView(numeroSuivantFileListData.getNomServiceDestination()));
        holder.txtNumPatientCourant.setText(numeroSuivantFileListData.getNumeroDansFileAttente());
        holder.txtNumPatientSuivant.setText(numeroSuivantFileListData.getNumeroDansFileAttente());
        holder.txtSuivant.setText("Suivant");
        holder.txtAnnuler.setText("Annuler");
        holder.imageView.setImageResource(numeroSuivantFileListData.getImgId());

        holder.txtSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtSuivant.................................................................> Just Clicked ! and my Index position " + position);
                Toast.makeText(view.getContext(), "Suivant just Clicked ! ", Toast.LENGTH_SHORT).show();
                DemandeGeneric demandeGeneric = new DemandeGeneric();
                demandeGeneric.setIdEtablissement("672f994ae434e738150a1cc1"); //TODO C'est l"objet qu'il faudra recuperer
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MONITOR)) {
                    EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.appelerNumero(demandeGeneric);
                }
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MEDECIN)) {
                    EcranPrincipalTraitementNumeroFileActivityList.userViewModel.appelerMedecinNumero(demandeGeneric);
                }
                //Rafraichissement MQTT (a optimisert ou a faire passer par la Queue..)
                //A defaut faire +1 pour Suivant et -1 pour Annuler ey on Success ou meme cote Back en testant sur le meme ecran
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.appelerNumero(); //Automatique à implementer
            }
        });

        holder.txtAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtAnnuler.................................................................> Just Clicked ! and my Index position " + position);
                Toast.makeText(view.getContext(), "Annuler just Clicked ! ", Toast.LENGTH_SHORT).show();
                DemandeGeneric demandeGeneric = new DemandeGeneric();
                demandeGeneric.setIdEtablissement("672f994ae434e738150a1cc1"); //TODO C'est l"objet qu'il faudra recuperer
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(demandeGeneric);
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MONITOR)) {
                    EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(demandeGeneric);
                }
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MEDECIN)) {
                    EcranPrincipalTraitementNumeroFileActivityList.userViewModel.annulerAppelMedecinNumero(demandeGeneric);
                }
                //Rafraichissement MQTT (a optimisert ou a faire passer par la Queue..)
                //A defaut faire +1 pour Suivant et -1 pour Annuler ey on Success ou cote Back en testant sur le meme ecran
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(); //Automatique à implementer
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlistdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtServiceDestination;
        public TextView txtNumPatientCourant;
        public TextView txtNumPatientSuivant;
        public TextView txtSuivant;
        public TextView txtAnnuler;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            //You could try to use Activty Binding
            //NumeroSuivantFilesMonitorListItemBinding numeroSuivantFilesMonitorListItemBinding;
            //numeroSuivantFilesMonitorListItemBinding = NumeroSuivantFilesMonitorListItemBinding.inflate(ViewHolder.getLayoutInflater());
            //View view = binding.getRoot();
            //setContentView(view);
            //numeroSuivantFilesMonitorListItemBinding.imageView
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.txtServiceDestination = (TextView) itemView.findViewById(R.id.txtServiceDestination);
            this.txtNumPatientCourant = (TextView) itemView.findViewById(R.id.txtNbPatientServiceCourant);
            this.txtNumPatientSuivant = (TextView) itemView.findViewById(R.id.txtnumPatientSuivant);
            this.txtSuivant = (TextView) itemView.findViewById(R.id.txtSuivant);
            this.txtAnnuler = (TextView) itemView.findViewById(R.id.txtAnnuler);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
