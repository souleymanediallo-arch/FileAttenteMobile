package com.soul.fileattente.adapters;

import com.soul.fileattente.model.ServiceAGG;
import com.soul.fileattente.model.ServiceDestination;

public class ServiceAGGListData extends ServiceAGG {

    private int imgId;
    private ServiceAGG serviceAGG;

    public ServiceAGGListData(ServiceAGG serviceAGG, int imgId) {
        super.setIdService(serviceAGG.getIdService());
        super.setNomService(serviceAGG.getNomService());
        super.setNbDemandeur(serviceAGG.getNbDemandeur());
        super.setNumeroSuivant(serviceAGG.getNumeroSuivant());
        super.setNumeroSuivantFile(serviceAGG.getNumeroSuivantFile());
        this.imgId = imgId;
        this.serviceAGG = serviceAGG;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public ServiceAGG getServiceAGG() {
        return serviceAGG;
    }
}
