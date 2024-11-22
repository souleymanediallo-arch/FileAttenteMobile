package com.soul.fileattente.model;

import java.io.Serializable;

public class ServiceDestination implements Serializable {

    private String idService;
    private String prefixeServiceAAfficher;
    private String nomServiceDestination;
    private String libelleServiceDestination;
    private String statutServiceDestination;
    private Long tempsAttenteMoyen;
    private Long tempsAttenteEstime;
    private String etablissementAssocie;


    public ServiceDestination() {
    }

    public ServiceDestination(String idService, String nomServiceDestination, String libelleServiceDestination, String statutServiceDestination, Long tempsAttenteMoyen, Long tempsAttenteEstime, String etablissementAssocie) {
        this.idService = idService;
        this.nomServiceDestination = nomServiceDestination;
        this.libelleServiceDestination = libelleServiceDestination;
        this.statutServiceDestination = statutServiceDestination;
        this.tempsAttenteMoyen = tempsAttenteMoyen;
        this.tempsAttenteEstime = tempsAttenteEstime;
        this.etablissementAssocie = etablissementAssocie;
    }

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getNomServiceDestination() {
        return nomServiceDestination;
    }

    public void setNomServiceDestination(String nomServiceDestination) {
        this.nomServiceDestination = nomServiceDestination;
    }

    public String getLibelleServiceDestination() {
        return libelleServiceDestination;
    }

    public void setLibelleServiceDestination(String libelleServiceDestination) {
        this.libelleServiceDestination = libelleServiceDestination;
    }

    public String getStatutServiceDestination() {
        return statutServiceDestination;
    }

    public void setStatutServiceDestination(String statutServiceDestination) {
        this.statutServiceDestination = statutServiceDestination;
    }

    public Long getTempsAttenteMoyen() {
        return tempsAttenteMoyen;
    }

    public void setTempsAttenteMoyen(Long tempsAttenteMoyen) {
        this.tempsAttenteMoyen = tempsAttenteMoyen;
    }

    public Long getTempsAttenteEstime() {
        return tempsAttenteEstime;
    }

    public void setTempsAttenteEstime(Long tempsAttenteEstime) {
        this.tempsAttenteEstime = tempsAttenteEstime;
    }

    public String getEtablissementAssocie() {
        return etablissementAssocie;
    }

    public void setEtablissementAssocie(String etablissementAssocie) {
        this.etablissementAssocie = etablissementAssocie;
    }

    public String getPrefixeServiceAAfficher() {
        return prefixeServiceAAfficher;
    }

    public void setPrefixeServiceAAfficher(String prefixeServiceAAfficher) {
        this.prefixeServiceAAfficher = prefixeServiceAAfficher;
    }
}
