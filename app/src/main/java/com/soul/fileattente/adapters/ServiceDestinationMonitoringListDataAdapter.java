package com.soul.fileattente.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.R;
import com.soul.fileattente.databinding.ServiceMonitorListItemBinding;
import com.soul.fileattente.utils.Utils;
import com.soul.fileattente.utils.GlobalSetOfExtra;

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
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ServiceDestinationListData serviceDestinationListData = listdata.get(position);

//        holder.txtServiceDestination.setText(serviceDestinationListData.getNomService() + " -- " + serviceDestinationListData.getStatutService());
        holder.txtServiceDestination.setText(Utils.formatStringForView(serviceDestinationListData.getNomServiceDestination()));
//        holder.txtNumPatientCourant.setText("0001");
//        holder.txtnumPatientSuivant.setText("0002");
        holder.txtSuivant.setText("Suivant");
        holder.txtAnnuler.setText("Annuler");

//        holder.txtSuivant.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    holder.txtSuivant.setBackgroundColor(ContextCompat.getColor(holder.imageView.getContext(), R.color.purple_500));
//                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    holder.txtSuivant.setBackgroundColor(ContextCompat.getColor(holder.imageView.getContext(), R.color.purple_700));
//                }
//                return false;
//            }
//        });

        holder.imageView.setImageResource(serviceDestinationListData.getImgId());
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
                //EcranPrincipalMonitoringActivityList.userViewModel.appelerNumero(10L);
            }
        });

        holder.txtAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("txtAnnuler.................................................................> Just Clicked ! and my Index position " + position);
                Toast.makeText(view.getContext(), "Annuler just Clicked ! ", Toast.LENGTH_SHORT).show();
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
