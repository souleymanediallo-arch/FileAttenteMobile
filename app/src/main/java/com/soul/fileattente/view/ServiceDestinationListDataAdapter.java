package com.soul.fileattente.view;

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

    // RecyclerView recyclerView;
//    public ServiceListDataAdapter(ServiceListData[] listdata) {
//        this.listdata = listdata;
//    }

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

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        final ServiceListData ServiceListData = listdata[position];
//        holder.textView.setText(listdata[position].getDescription());
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+ServiceListData.getDescription(),Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ServiceDestinationListData serviceDestinationListData = listdata.get(position);
        holder.textView.setText(listdata.get(position).getNomService() + " -- " + listdata.get(position).getStatutService());
        holder.imageView.setImageResource(listdata.get(position).getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+ serviceDestinationListData.getNomService(),Toast.LENGTH_LONG).show();
            }
        });
    }

//    @Override
//    public int getItemCount() {
//        return listdata.length;
//    }

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
