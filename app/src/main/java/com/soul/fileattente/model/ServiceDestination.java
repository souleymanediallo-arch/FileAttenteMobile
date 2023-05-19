package com.soul.fileattente.model;

import java.io.Serializable;

public class ServiceDestination implements Serializable {

    String nomService;
    String libelleService;
    String statutService;


    public ServiceDestination() {
    }

    public ServiceDestination(String nomService, String libelleService, String statutService) {
        this.nomService = nomService;
        this.libelleService = libelleService;
        this.statutService = statutService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getLibelleService() {
        return libelleService;
    }

    public void setLibelleService(String libelleService) {
        this.libelleService = libelleService;
    }

    public String getStatutService() {
        return statutService;
    }

    public void setStatutService(String statutService) {
        this.statutService = statutService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "nomService='" + nomService + '\'' +
                ", libelleService='" + libelleService + '\'' +
                ", statutService='" + statutService + '\'' +
                '}';
    }
}
