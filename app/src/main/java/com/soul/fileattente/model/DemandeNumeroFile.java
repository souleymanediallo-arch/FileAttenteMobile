package com.soul.fileattente.model;

public class DemandeNumeroFile {

    //{
    //        "clinique": "Vision Medicale Coumba",
    //        "deviceId": "0122455789632111441251",
    //        "service": "Pediatrie",
    //        "telephonePatient": "+221766752276",
    //        "dateHeureDemande": "2023-05-13T10:35:02.678Z"
    //}

    String clinique;
    String deviceId;
    String service;
    String telephonePatient;
    String dateHeureDemande;

    public DemandeNumeroFile() {
    }

    public DemandeNumeroFile(String clinique, String deviceId, String service, String telephonePatient, String dateHeureDemande) {
        this.clinique = clinique;
        this.deviceId = deviceId;
        this.service = service;
        this.telephonePatient = telephonePatient;
        this.dateHeureDemande = dateHeureDemande;
    }

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

    public String getTelephonePatient() {
        return telephonePatient;
    }

    public void setTelephonePatient(String telephonePatient) {
        this.telephonePatient = telephonePatient;
    }

    public String getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(String dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "DemandeNumeroFile{" +
                "clinique='" + clinique + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", service='" + service + '\'' +
                ", telephonePatient='" + telephonePatient + '\'' +
                ", dateHeureDemande='" + dateHeureDemande + '\'' +
                '}';
    }
}
