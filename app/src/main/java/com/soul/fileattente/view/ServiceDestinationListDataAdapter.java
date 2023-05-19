package com.soul.fileattente.view;

import android.content.Context;
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
import java.util.ArrayList;

public class ServiceDestinationListDataAdapter extends RecyclerView.Adapter<ServiceDestinationListDataAdapter.ViewHolder> {

    private ArrayList<ServiceDestinationListData> listdata;

    public ServiceDestinationListDataAdapter(ArrayList<ServiceDestinationListData> listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.service_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceDestinationListData serviceDestinationListData = listdata.get(position);
        holder.textView.setText(serviceDestinationListData.getNomService() + " -- " + serviceDestinationListData.getStatutService());
        holder.imageView.setImageResource(serviceDestinationListData.getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+ serviceDestinationListData.getNomService(),Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(context, EcranPrincipalActivity.class);
                Intent intent = new Intent(view.getContext(), EcranPrincipalActivity.class);
                intent.putExtra(Global.SELECTED_SERVICE_DESTINATION_KEY, serviceDestinationListData.getServiceDestination());
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
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
