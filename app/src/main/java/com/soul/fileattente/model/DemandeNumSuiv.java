package com.soul.fileattente.model;

public class DemandeNumSuiv extends Demande {

    String service;

    public DemandeNumSuiv() {
        super();
    }

    public DemandeNumSuiv(String clinique, String service, String deviceId, String dateHeureDemande) {
        this.clinique = clinique;
        this.service = service;
        this.deviceId = deviceId;
        this.dateHeureDemande = dateHeureDemande;
    }

}
