package com.soul.fileattente.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.R;
import com.soul.fileattente.model.DemandeGeneric;
import com.soul.fileattente.model.ServiceDestination;
import com.soul.fileattente.utils.GlobalSetOfExtra;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.view.EcranPrincipalActivity;
import com.soul.fileattente.view.EcranPrincipalMonitoringActivityList;
import com.soul.fileattente.view.EcranPrincipalMonitoringNumeroFileActivityList;

import java.util.ArrayList;

public class ServiceAGGMonitoringListDataAdapter extends RecyclerView.Adapter<ServiceAGGMonitoringListDataAdapter.ViewHolder> {

    GlobalSetOfExtra mGlobalSetOfExtra;

    private ArrayList<ServiceAGGListData> listdata;

    public ServiceAGGMonitoringListDataAdapter(ArrayList<ServiceAGGListData> listdata, GlobalSetOfExtra globalSetOfExtra) {
        this.listdata = listdata;
        this.mGlobalSetOfExtra = globalSetOfExtra;
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
        final ServiceAGGListData serviceAGGListData = listdata.get(position);
//        holder.textView.setText(serviceAGGListData.getNomService() + " -- " + serviceAGGListData.getNumeroSuivant() + " -- " + serviceAGGListData.getNbDemandeur());

        holder.txtServiceDestination.setText(Utils.formatStringForView(serviceAGGListData.getNomService()));
        holder.txtNumPatientCourant.setText(serviceAGGListData.getNbDemandeur());
        holder.txtnumPatientSuivant.setText(serviceAGGListData.getNumeroSuivant());
        holder.txtSuivant.setText("Suivant");
        holder.txtAnnuler.setText("Annuler");


        holder.imageView.setImageResource(serviceAGGListData.getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(view.getContext(),"click on item: "+ serviceDestinationListData.getNomService(),Toast.LENGTH_LONG).show();
//                //Intent intent = new Intent(context, EcranPrincipalActivity.class);
//
//                //GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(Global.GLOBALSETOFEXTRA);
//                //view.getContext().inte
//
//                Intent intent = new Intent(view.getContext(), EcranPrincipalActivity.class);
//
//                //mGlobalSetOfExtra.mServiceDestination = serviceDestinationListData.getServiceDestination();
//                //mGlobalSetOfExtra.mServiceDestination = new ServiceDestination();//Pou eviter des erreurs eventuelles
//                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
//                //intent.putExtra(Global.SELECTED_SERVICE_DESTINATION_KEY, serviceDestinationListData.getServiceDestination());
//                view.getContext().startActivity(intent);
//            }
//        });


        holder.txtSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtSuivant.................................................................> Just Clicked ! and my Index position " + position);
                //Toast.makeText(view.getContext(), "Suivant just Clicked ! " + serviceAGGListData.getNumeroSuivantFile(), Toast.LENGTH_SHORT).show();
                EcranPrincipalMonitoringActivityList.userViewModel.appelerNumero(new DemandeGeneric());
                System.out.printf("serviceAGGListData.getNumeroSuivantFile() -----> " + serviceAGGListData.getNumeroSuivantFile());
            }
        });

        holder.txtAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtAnnuler.................................................................> Just Clicked ! and my Index position " + position);
                //Toast.makeText(view.getContext(), "Annuler just Clicked ! " + serviceAGGListData.getNumeroSuivantFile(), Toast.LENGTH_SHORT).show();
                //EcranPrincipalMonitoringNumeroFileActivityList.userViewModel.annulerAppelNumero(new DemandeGeneric());
                EcranPrincipalMonitoringActivityList.userViewModel.annulerAppelNumero(new DemandeGeneric());
                System.out.printf("serviceAGGListData.getNumeroSuivantFile() -----> " + serviceAGGListData.getNumeroSuivantFile());
            }
        });
    }

//    @Override
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView;
//        public TextView textView;
//        public RelativeLayout relativeLayout;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.textView = (TextView) itemView.findViewById(R.id.textView);
//            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
//        }
//    }

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
            this.txtNumPatientCourant = (TextView) itemView.findViewById(R.id.txtNumPatientCourant);
            this.txtnumPatientSuivant = (TextView) itemView.findViewById(R.id.txtnumPatientSuivant);
            this.txtSuivant = (TextView) itemView.findViewById(R.id.txtSuivant);
            this.txtAnnuler = (TextView) itemView.findViewById(R.id.txtAnnuler);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);

        }
    }
}
