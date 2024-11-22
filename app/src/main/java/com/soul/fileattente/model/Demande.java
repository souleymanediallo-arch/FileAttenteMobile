package com.soul.fileattente.model;

public class Demande {

    String clinique;
    String deviceId;
    String dateHeureDemande;

    public String getClinique() {
        return clinique;
    }

    public void setClinique(String clinique) {
        this.clinique = clinique;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(String dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }

    @Override
    public String toString() {
        return "DemandeParam{" +
                "clinique='" + clinique + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", dateHeureDemande='" + dateHeureDemande + '\'' +
                '}';
    }
}
