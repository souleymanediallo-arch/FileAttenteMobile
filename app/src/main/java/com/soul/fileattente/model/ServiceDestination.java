package com.soul.fileattente.model;

import java.io.Serializable;

public class ServiceDestination implements Serializable {
    Long id;
    String nomServiceDestination;
    String libelleServiceDestination;
    String statutServiceDestination;
    Long tempsAttenteMoyen;
    Long tempsAttenteEstime;
    Long etablissementid;

    public ServiceDestination() {
    }

    public ServiceDestination(Long id, String nomServiceDestination, String libelleServiceDestination, String statutServiceDestination, Long tempsAttenteMoyen, Long tempsAttenteEstime, Long etablissementid) {
        this.id = id;
        this.nomServiceDestination = nomServiceDestination;
        this.libelleServiceDestination = libelleServiceDestination;
        this.statutServiceDestination = statutServiceDestination;
        this.tempsAttenteMoyen = tempsAttenteMoyen;
        this.tempsAttenteEstime = tempsAttenteEstime;
        this.etablissementid = etablissementid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getEtablissementid() {
        return etablissementid;
    }

    public void setEtablissementid(Long etablissementid) {
        this.etablissementid = etablissementid;
    }
}
