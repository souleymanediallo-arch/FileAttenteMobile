package com.soul.fileattente.model;

public class Demande {
    //{
    //    "clinique": "Vision Medicale Coumba",
    //        "deviceId": "0122455789632111441251",
    //        "dateHeureDemande": "2023-05-13T10:35:02.678Z"
   // }

    String clinique;
    String deviceId;
    String dateHeureDemande;

//    public Demande() {
//    }
//
//    public Demande(String clinique, String deviceId, String dateHeureDemande) {
//        this.clinique = clinique;
//        this.deviceId = deviceId;
//        this.dateHeureDemande = dateHeureDemande;
//    }

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
