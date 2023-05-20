package com.soul.fileattente.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ServiceMonitorListItemBinding;

import java.util.ArrayList;

public class ServiceDestinationMonitoringListDataAdapter extends RecyclerView.Adapter<ServiceDestinationMonitoringListDataAdapter.ViewHolder> {

    GlobalSetOfExtra mGlobalSetOfExtra;

    private ArrayList<ServiceDestinationListData> listdata;
//    LayoutInflater inflater;
    ServiceMonitorListItemBinding binding;

    public ServiceDestinationMonitoringListDataAdapter(ArrayList<ServiceDestinationListData> listdata, GlobalSetOfExtra globalSetOfExtra) {
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
        View listItem = layoutInflater.inflate(R.layout.service_monitor_list_item, parent, false);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceDestinationListData serviceDestinationListData = listdata.get(position);
        holder.textView.setText(serviceDestinationListData.getNomService() + " -- " + serviceDestinationListData.getStatutService());
        holder.imageView.setImageResource(serviceDestinationListData.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EcranPrincipalActivity.class);

                mGlobalSetOfExtra.mServiceDestination = serviceDestinationListData.getServiceDestination();
                intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.txtServiceDestination);
            this.textView = (TextView) itemView.findViewById(R.id.txtNumPatientCourant);
            this.textView = (TextView) itemView.findViewById(R.id.txtnumPatientSuivant);
            this.textView = (TextView) itemView.findViewById(R.id.txtSuivant);
            this.textView = (TextView) itemView.findViewById(R.id.txtAnnuler);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
