package com.soul.fileattente.adapters;

import com.soul.fileattente.model.ServiceDestination;

public class ServiceDestinationListData extends ServiceDestination {

    private int imgId;
    private ServiceDestination serviceDestination;

    public ServiceDestinationListData(ServiceDestination serviceDestination, int imgId) {
        super.setLibelleServiceDestination(serviceDestination.getLibelleServiceDestination());
        super.setNomServiceDestination(serviceDestination.getNomServiceDestination());
        super.setStatutServiceDestination(serviceDestination.getStatutServiceDestination());
        this.imgId = imgId;
        this.serviceDestination = serviceDestination;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public ServiceDestination getServiceDestination() {
        return serviceDestination;
    }
}
