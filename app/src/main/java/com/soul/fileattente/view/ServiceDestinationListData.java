package com.soul.fileattente.view;

import com.soul.fileattente.model.ServiceDestination;

public class ServiceDestinationListData extends ServiceDestination {

    private int imgId;
    private ServiceDestination serviceDestination;

    public ServiceDestinationListData(ServiceDestination serviceDestination, int imgId) {
        super.setLibelleService(serviceDestination.getLibelleService());
        super.setNomService(serviceDestination.getNomService());
        super.setStatutService(serviceDestination.getStatutService());
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
