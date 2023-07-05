package com.soul.fileattente.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.R;
import com.soul.fileattente.view.EcranPrincipalActivity;
import com.soul.fileattente.utils.GlobalSetOfExtra;

import java.util.ArrayList;

public class ServiceDestinationListDataAdapter extends RecyclerView.Adapter<ServiceDestinationListDataAdapter.ViewHolder> {

    GlobalSetOfExtra mGlobalSetOfExtra;

    private ArrayList<ServiceDestinationListData> listdata;

//    public ServiceDestinationListDataAdapter(ArrayList<ServiceDestinationListData> listdata) {
//        this.listdata = listdata;
//    }

    public ServiceDestinationListDataAdapter(ArrayList<ServiceDestinationListData> listdata, GlobalSetOfExtra globalSetOfExtra) {
        this.listdata = listdata;
        this.mGlobalSetOfExtra = globalSetOfExtra;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.service_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceDestinationListData serviceDestinationListData = listdata.get(position);
//
//        //holder.textView.setText(serviceDestinationListData.getNomServiceDestination() + " -- " + serviceDestinationListData.getStatutServiceDestination());
//        String serviceStatus = serviceDestinationListData.getStatutServiceDestination();
//        if(serviceStatus.equalsIgnoreCase("Inactif")){
//            int color = Color.parseColor("#838996");//Roman Silver
//            holder.textView.setTextColor(color);
//            //holder.textView.setBackgroundColor(color);
//            //holder.textView.setEnabled(false);
//            //holder.textView.setClickable(false);
//            //holder.relativeLayout.setClickable(false);
//            holder.relativeLayout.setEnabled(false);
//        }
//        if(serviceStatus.equalsIgnoreCase("Pause")){
//            int color = Color.parseColor("#98AFC7");//Blue Gray
//            holder.textView.setTextColor(color);
//            //holder.textView.setBackgroundColor(color);
//            //holder.textView.setEnabled(false);
//            //holder.textView.setClickable(false);
//            //holder.relativeLayout.setClickable(false);
//            holder.relativeLayout.setEnabled(false);
//        }
//        //holder.textView.setText("["+serviceDestinationListData.getLibelleServiceDestination()+"] - "  + serviceDestinationListData.getNomServiceDestination());
        //holder.textView.setText("["+serviceDestinationListData.getLibelleServiceDestination()+"] - "  + serviceDestinationListData.getNomServiceDestination());
        holder.textView.setText("["+serviceDestinationListData.getLibelleServiceDestination()+"] - "  + serviceDestinationListData.getNomServiceDestination());

        holder.imageView.setImageResource(serviceDestinationListData.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+ serviceDestinationListData.getNomService(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(context, EcranPrincipalActivity.class);

                //GlobalSetOfExtra mGlobalSetOfExtra = (GlobalSetOfExtra)getIntent().getSerializableExtra(Global.GLOBALSETOFEXTRA);
                //view.getContext().inte
                String serviceStatus = serviceDestinationListData.getStatutServiceDestination();
                //if(!(serviceStatus.equalsIgnoreCase("Inactif") && serviceStatus.equalsIgnoreCase("Pause"))) {
                    Intent intent = new Intent(view.getContext(), EcranPrincipalActivity.class);
                    mGlobalSetOfExtra.mServiceDestination = serviceDestinationListData.getServiceDestination();
                    intent.putExtra(GlobalSetOfExtra.GLOBALSETOFEXTRA, mGlobalSetOfExtra);
                    //intent.putExtra(Global.SELECTED_SERVICE_DESTINATION_KEY, serviceDestinationListData.getServiceDestination());
                    view.getContext().startActivity(intent);
                //}
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
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
