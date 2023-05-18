package com.soul.fileattente.model;

public class DemandeService extends Demande {


    public DemandeService() {
    }

    public DemandeService(String clinique, String deviceId, String dateHeureDemande) {
        this.clinique = clinique;
        this.deviceId = deviceId;
        this.dateHeureDemande = dateHeureDemande;
    }

}
