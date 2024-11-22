package com.soul.fileattente.model;

public class DemandeNumeroFile {

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
}
