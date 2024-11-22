package com.soul.fileattente.model;

public class DemandeGeneric {

    String idEtablissement;
    String idService;
    String telephoneDemandeur;
    String emailDemandeur;
    String patientDeviceId;
    String monitorDeviceId;
    String medecinDeviceId;
    String nomService;
    String prefixeService;
    String servicesChoisi;

    public DemandeGeneric() {
    }

    public DemandeGeneric(String idEtablissement, String idService, String telephoneDemandeur, String emailDemandeur, String patientDeviceId, String monitorDeviceId, String medecinDeviceId, String nomService, String prefixeService, String servicesChoisi) {
        this.idEtablissement = idEtablissement;
        this.idService = idService;
        this.telephoneDemandeur = telephoneDemandeur;
        this.emailDemandeur = emailDemandeur;
        this.patientDeviceId = patientDeviceId;
        this.monitorDeviceId = monitorDeviceId;
        this.medecinDeviceId = medecinDeviceId;
        this.nomService = nomService;
        this.prefixeService = prefixeService;
        this.servicesChoisi = servicesChoisi;
    }

    public String getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(String idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
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

    public String getPatientDeviceId() {
        return patientDeviceId;
    }

    public void setPatientDeviceId(String patientDeviceId) {
        this.patientDeviceId = patientDeviceId;
    }

    public String getMonitorDeviceId() {
        return monitorDeviceId;
    }

    public void setMonitorDeviceId(String monitorDeviceId) {
        this.monitorDeviceId = monitorDeviceId;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getPrefixeService() {
        return prefixeService;
    }

    public void setPrefixeService(String prefixeService) {
        this.prefixeService = prefixeService;
    }

    public String getMedecinDeviceId() {
        return medecinDeviceId;
    }

    public void setMedecinDeviceId(String medecinDeviceId) {
        this.medecinDeviceId = medecinDeviceId;
    }

    public String getServicesChoisi() {
        return servicesChoisi;
    }

    public void setServicesChoisi(String servicesChoisi) {
        this.servicesChoisi = servicesChoisi;
    }
}
