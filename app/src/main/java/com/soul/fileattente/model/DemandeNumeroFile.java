package com.soul.fileattente.model;

public class DemandeNumeroFile {

    //{
    //        "clinique": "Vision Medicale Coumba",
    //        "deviceId": "0122455789632111441251",
    //        "service": "Pediatrie",
    //        "telephonePatient": "+221766752276",
    //        "dateHeureDemande": "2023-05-13T10:35:02.678Z"
    //}

//    String clinique;
//    String deviceId;
//    String service;
//    String telephonePatient;
//    String dateHeureDemande;

    private String  nomService;
    private String  etablissementid;
    private String  serviceDestinationid;
    private String  deviceId;
    private String  telephoneDemandeur;
    private String  emailDemandeur;

    public DemandeNumeroFile() {
    }

    public DemandeNumeroFile(String nomService, String etablissementid, String serviceDestinationid, String deviceId, String telephoneDemandeur, String emailDemandeur) {
        this.nomService = nomService;
        this.etablissementid = etablissementid;
        this.serviceDestinationid = serviceDestinationid;
        this.deviceId = deviceId;
        this.telephoneDemandeur = telephoneDemandeur;
        this.emailDemandeur = emailDemandeur;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getEtablissementid() {
        return etablissementid;
    }

    public void setEtablissementid(String etablissementid) {
        this.etablissementid = etablissementid;
    }

    public String getServiceDestinationid() {
        return serviceDestinationid;
    }

    public void setServiceDestinationid(String serviceDestinationid) {
        this.serviceDestinationid = serviceDestinationid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTelephoneDemandeur() {
        return telephoneDemandeur;
    }

    public void setTelephoneDemandeur(String telephoneDemandeur) {
        this.telephoneDemandeur = telephoneDemandeur;
    }

    public String getEmailDemandeur() {
        return emailDemandeur;
    }

    public void setEmailDemandeur(String emailDemandeur) {
        this.emailDemandeur = emailDemandeur;
    }
//    public DemandeNumeroFile() {
//    }
//
//    public DemandeNumeroFile(String clinique, String deviceId, String service, String telephonePatient, String dateHeureDemande) {
//        this.clinique = clinique;
//        this.deviceId = deviceId;
//        this.service = service;
//        this.telephonePatient = telephonePatient;
//        this.dateHeureDemande = dateHeureDemande;
//    }
//
//    public String getClinique() {
//        return clinique;
//    }
//
//    public void setClinique(String clinique) {
//        this.clinique = clinique;
//    }
//
//    public String getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(String deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    public String getTelephonePatient() {
//        return telephonePatient;
//    }
//
//    public void setTelephonePatient(String telephonePatient) {
//        this.telephonePatient = telephonePatient;
//    }
//
//    public String getDateHeureDemande() {
//        return dateHeureDemande;
//    }
//
//    public void setDateHeureDemande(String dateHeureDemande) {
//        this.dateHeureDemande = dateHeureDemande;
//    }
//
//    public String getService() {
//        return service;
//    }
//
//    public void setService(String service) {
//        this.service = service;
//    }
//
//    @Override
//    public String toString() {
//        return "DemandeNumeroFile{" +
//                "clinique='" + clinique + '\'' +
//                ", deviceId='" + deviceId + '\'' +
//                ", service='" + service + '\'' +
//                ", telephonePatient='" + telephonePatient + '\'' +
//                ", dateHeureDemande='" + dateHeureDemande + '\'' +
//                '}';
//    }
}
