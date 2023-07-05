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
import com.soul.fileattente.databinding.NumeroSuivantFilesMonitorListItemBinding;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.view.EcranPrincipalMonitoringNumeroFileActivityList;

import java.util.ArrayList;

public class NumeroSuivantFileMonitoringListDataAdapter extends RecyclerView.Adapter<NumeroSuivantFileMonitoringListDataAdapter.ViewHolder> {

    GlobalSetOfExtra mGlobalSetOfExtra;

    private ArrayList<NumeroSuivantFileListData> listdata;
//    LayoutInflater inflater;
//    ServiceMonitorListItemBinding binding;
    NumeroSuivantFilesMonitorListItemBinding binding;

    public NumeroSuivantFileMonitoringListDataAdapter(ArrayList<NumeroSuivantFileListData> listdata, GlobalSetOfExtra globalSetOfExtra) {
        this.listdata = listdata;
        this.mGlobalSetOfExtra = globalSetOfExtra;
    }

//    public ServiceDestinationMonitoringListDataAdapter(Context context, ArrayList<ServiceDestinationListData> listdata, GlobalSetOfExtra globalSetOfExtra) {
//        this.listdata = listdata;
//        this.mGlobalSetOfExtra = globalSetOfExtra;
//        inflater = LayoutInflater.from(context);
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.numero_suivant_files_monitor_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        binding = ServiceMonitorListItemBinding.inflate(inflater);
//        View listItem = binding.getRoot();
//        ViewHolder viewHolder = new ViewHolder(listItem);
//        return viewHolder;
//    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final NumeroSuivantFileListData numeroSuivantFileListData = listdata.get(position);

//        holder.txtServiceDestination.setText(serviceDestinationListData.getNomService() + " -- " + serviceDestinationListData.getStatutService());
        holder.txtServiceDestination.setText(Utils.formatStringForView(numeroSuivantFileListData.getNomService()));
        holder.txtNumPatientCourant.setText(numeroSuivantFileListData.getNumeroSuivant());
        holder.txtnumPatientSuivant.setText(numeroSuivantFileListData.getNumeroSuivant());
        holder.txtSuivant.setText("Suivant");
        holder.txtAnnuler.setText("Annuler");

        holder.imageView.setImageResource(numeroSuivantFileListData.getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EcranPrincipalActivity.class);
//
//                mGlobalSetOfExtra.mServiceDestination = serviceDestinationListData.getServiceDestination();
//                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
//
//                view.getContext().startActivity(intent);
//            }
//        });

        holder.txtSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtSuivant.................................................................> Just Clicked ! and my Index position " + position);
                Toast.makeText(view.getContext(), "Suivant just Clicked ! ", Toast.LENGTH_SHORT).show();

                DemandeGeneric demandeGeneric = new DemandeGeneric();
                //demandeGeneric.setId(1L);
                //demandeGeneric.setNomServiceDestination("nomServiceDestination");
                demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer

                EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.appelerNumero(demandeGeneric);
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
                //demandeGeneric.setId(1L);
                //demandeGeneric.setNomServiceDestination("nomServiceDestination");
                demandeGeneric.setEtablissementid("1"); //TODO C'est l"objet qu'il faudra recuperer

                EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(demandeGeneric);
                //Rafraichissement MQTT (a optimisert ou a faire passer par la Queue..)
                //A defaut faire +1 pour Suivant et -1 pour Annuler ey on Success ou cote Back en testant sur le meme ecran
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.demandeAggregatAllServicesDestinationNumeroFiles(demandeGeneric);

                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(); //Automatique à implementer
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtServiceDestination;
        public TextView txtNumPatientCourant;
        public TextView txtnumPatientSuivant;
        public TextView txtSuivant;
        public TextView txtAnnuler;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.txtServiceDestination = (TextView) itemView.findViewById(R.id.txtServiceDestination);
            this.txtNumPatientCourant = (TextView) itemView.findViewById(R.id.txtNbPatientServiceCourant);
            this.txtnumPatientSuivant = (TextView) itemView.findViewById(R.id.txtnumPatientSuivant);
            this.txtSuivant = (TextView) itemView.findViewById(R.id.txtSuivant);
            this.txtAnnuler = (TextView) itemView.findViewById(R.id.txtAnnuler);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.linearLayout);

        }
    }
}
