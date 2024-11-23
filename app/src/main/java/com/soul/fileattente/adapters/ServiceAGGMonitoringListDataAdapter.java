package com.soul.fileattente.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.R;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.view.EcranPrincipalMonitoringActivityList;
import com.soul.fileattente.view.EcranPrincipalTraitementActivityList;

import java.util.ArrayList;

public class ServiceAGGMonitoringListDataAdapter extends RecyclerView.Adapter<ServiceAGGMonitoringListDataAdapter.ViewHolder> {

    private GlobalSetOfExtra mGlobalSetOfExtra;
    private ArrayList<ServiceAGGListData> mListdata;
    private String mDisplayScreen;

    public ServiceAGGMonitoringListDataAdapter(ArrayList<ServiceAGGListData> listdata, GlobalSetOfExtra globalSetOfExtra, String displayScreen) {
        this.mListdata = listdata;
        this.mGlobalSetOfExtra = globalSetOfExtra;
        this.mDisplayScreen = displayScreen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.service_monitor_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  @SuppressLint("RecyclerView") int position) {
        final ServiceAGGListData serviceAGGListData = mListdata.get(position);
        //holder.txtServiceDestination.setText(Utils.formatStringForView(serviceAGGListData.getNomService()));
        holder.txtServiceDestination.setText(serviceAGGListData.getNomServiceDestination());
        holder.txtNbPatientServiceCourant.setText(serviceAGGListData.getNumberOfElementInQueue());
        holder.txtnumPatientSuivant.setText(serviceAGGListData.getNumeroSuivant());
        holder.txtSuivant.setText("Suivant");
        holder.txtAnnuler.setText("Annuler");

        if(position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(Color.LTGRAY);
            //holder.linearLayout.setBackgroundColor(Color.BLUE);
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(holder.imageView.getContext(), R.color.lgray));
        }
        holder.imageView.setImageResource(serviceAGGListData.getImgId());
        holder.txtSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtSuivant.................................................................> Just Clicked ! and my Index position " + position);
                DemandeGeneric demandeGeneric = new DemandeGeneric();
                demandeGeneric.setIdService(serviceAGGListData.getServicesChoisi());
                demandeGeneric.setServicesChoisi(serviceAGGListData.getServicesChoisi());
                demandeGeneric.setNomService(serviceAGGListData.getNomServiceDestination());
                System.out.println("------------------------------------------> : 672f9b05e434e738150a1cc2");
                System.out.println("------------------------------------------> serviceAGGListData.getServicesChoisi() : " + serviceAGGListData.getServicesChoisi());
                System.out.println("------------------------------------------> serviceAGGListData.getNumeroSuivantFile().getServicesChoisi() : " + serviceAGGListData.getNumeroSuivantFile().getServicesChoisi());

                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MONITOR)) {
                    EcranPrincipalMonitoringActivityList.userViewModel.appelerNumero(demandeGeneric);
                }
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MEDECIN)) {
                    EcranPrincipalTraitementActivityList.userViewModel.appelerMedecinNumero(demandeGeneric);
                }
                System.out.printf("serviceAGGListData.getNumeroSuivantFile() -----> " + serviceAGGListData.getNumeroSuivantFile());
            }
        });

        holder.txtAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtAnnuler.................................................................> Just Clicked ! and my Index position " + position);
                DemandeGeneric demandeGeneric = new DemandeGeneric();
                demandeGeneric.setIdService(serviceAGGListData.getServicesChoisi());
                demandeGeneric.setServicesChoisi(serviceAGGListData.getServicesChoisi());
                demandeGeneric.setNomService(serviceAGGListData.getNomServiceDestination());

                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MONITOR)) {
                    EcranPrincipalMonitoringActivityList.userViewModel.annulerAppelNumero(demandeGeneric);
                }
                if(mDisplayScreen.trim().equalsIgnoreCase(Utils.SCREEN_MEDECIN)) {
                    EcranPrincipalTraitementActivityList.userViewModel.annulerAppelMedecinNumero(demandeGeneric);
                }
                System.out.printf("serviceAGGListData.getNumeroSuivantFile() -----> " + serviceAGGListData.getNumeroSuivantFile());
            }
        });
    }

    public int getItemCount() {
        return mListdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtServiceDestination;
        public TextView txtNbPatientServiceCourant;
        public TextView txtnumPatientSuivant;
        public TextView txtSuivant;
        public TextView txtAnnuler;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.txtServiceDestination = (TextView) itemView.findViewById(R.id.txtServiceDestination);
            this.txtNbPatientServiceCourant = (TextView) itemView.findViewById(R.id.txtNbPatientServiceCourant);
            this.txtnumPatientSuivant = (TextView) itemView.findViewById(R.id.txtnumPatientSuivant);
            this.txtSuivant = (TextView) itemView.findViewById(R.id.txtSuivant);
            this.txtAnnuler = (TextView) itemView.findViewById(R.id.txtAnnuler);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
