package com.soul.fileattente.adapters;

//public class ServiceDestinationSyncListDataAdapter {
//}

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soul.fileattente.databinding.ServiceDestinationListItemBinding;
import com.soul.fileattente.model.ServiceDestination;

import java.util.List;

public class ServiceDestinationSyncListDataAdapter extends RecyclerView.Adapter<ServiceDestinationSyncListDataAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ServiceDestination service, int position);
    }

    private List<ServiceDestination> services;
    private OnItemClickListener listener;

    public ServiceDestinationSyncListDataAdapter(List<ServiceDestination> services, OnItemClickListener listener) {
        this.services = services;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ServiceDestinationListItemBinding binding;

        public ViewHolder(ServiceDestinationListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ServiceDestination service, int position) {
            binding.textNom.setText(service.getNomServiceDestination());
            binding.textPrefix.setText(service.getPrefixeServiceAAfficher());
            binding.getRoot().setOnClickListener(v -> listener.onItemClick(service, position));
        }
    }

    @NonNull
    @Override
    public ServiceDestinationSyncListDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceDestinationListItemBinding binding = ServiceDestinationListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceDestinationSyncListDataAdapter.ViewHolder holder, int position) {
        holder.bind(services.get(position), position);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }
}
