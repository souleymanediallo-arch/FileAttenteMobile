package com.soul.fileattente.model;

public class DemandeParam extends Demande {

    public DemandeParam() {
        super();
    }

    public DemandeParam(String clinique, String deviceId, String dateHeureDemande) {
        this.clinique = clinique;
        this.deviceId = deviceId;
        this.dateHeureDemande = dateHeureDemande;
    }

}
