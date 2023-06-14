package com.soul.fileattente.model;

public class DemandeGeneric {
    private Long id;
    private Long serviceDestinationid;
    private String nomServiceDestination;
    private String etablissementid;
    private String clinique;
    private String deviceId;
    private String service;
    private String dateHeureDemande;

    public DemandeGeneric() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceDestinationid() {
        return serviceDestinationid;
    }

    public void setServiceDestinationid(Long serviceDestinationid) {
        this.serviceDestinationid = serviceDestinationid;
    }

    public String getNomServiceDestination() {
        return nomServiceDestination;
    }

    public void setNomServiceDestination(String nomServiceDestination) {
        this.nomServiceDestination = nomServiceDestination;
    }

    public String getEtablissementid() {
        return etablissementid;
    }

    public void setEtablissementid(String etablissementid) {
        this.etablissementid = etablissementid;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(String dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }
}
