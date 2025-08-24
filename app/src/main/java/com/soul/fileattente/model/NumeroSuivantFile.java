package com.soul.fileattente.model;

import java.io.Serializable;


public class NumeroSuivantFile implements Serializable {

    private String idFileAttente;
    private String nomEtablissement;
    private String nomServiceDestination;
    private String prefixeServiceAAfficher;
    private String numeroDansFileAttente;
    private String telephoneDemandeur;
    private String patientDeviceId;
    private String monitorDeviceId;
    private String medecinDeviceId;
    private String emailDemandeur;
    private String dateHeureDemande;
    private String dateHeureRetour;
    private String dateHeureAppelMonitor;
    private String dateHeureAppelMedecin;
    private int nbAnnulation;
    private String dateHeureDerniereAnnulation;
    private Long tempsAttenteMoyen;
    private Long tempsAttenteEstime;
    private Long tempsAttenteEffectif;
    private Long nbTotalDemandeursEnCours;
    private String servicesChoisi;
    private String statut;
    private String errorMessageIfAny;

    //
    private int calledByMonitor = 0;
    private int calledByMedecin = 0;
    //

    public NumeroSuivantFile() {
    }

    public String getIdFileAttente() {
        return idFileAttente;
    }

    public void setIdFileAttente(String idFileAttente) {
        this.idFileAttente = idFileAttente;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public String getNomServiceDestination() {
        return nomServiceDestination;
    }

    public void setNomServiceDestination(String nomServiceDestination) {
        this.nomServiceDestination = nomServiceDestination;
    }

    public String getPrefixeServiceAAfficher() {
        return prefixeServiceAAfficher;
    }

    public void setPrefixeServiceAAfficher(String prefixeServiceAAfficher) {
        this.prefixeServiceAAfficher = prefixeServiceAAfficher;
    }

    public String getNumeroDansFileAttente() {
        return numeroDansFileAttente;
    }

    public void setNumeroDansFileAttente(String numeroDansFileAttente) {
        this.numeroDansFileAttente = numeroDansFileAttente;
    }

    public String getTelephoneDemandeur() {
        return telephoneDemandeur;
    }

    public void setTelephoneDemandeur(String telephoneDemandeur) {
        this.telephoneDemandeur = telephoneDemandeur;
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

    public String getMedecinDeviceId() {
        return medecinDeviceId;
    }

    public void setMedecinDeviceId(String medecinDeviceId) {
        this.medecinDeviceId = medecinDeviceId;
    }

    public String getEmailDemandeur() {
        return emailDemandeur;
    }

    public void setEmailDemandeur(String emailDemandeur) {
        this.emailDemandeur = emailDemandeur;
    }

    public String getDateHeureDemande() {
        return dateHeureDemande;
    }

    public void setDateHeureDemande(String dateHeureDemande) {
        this.dateHeureDemande = dateHeureDemande;
    }

    public String getDateHeureRetour() {
        return dateHeureRetour;
    }

    public void setDateHeureRetour(String dateHeureRetour) {
        this.dateHeureRetour = dateHeureRetour;
    }

    public String getDateHeureAppelMonitor() {
        return dateHeureAppelMonitor;
    }

    public void setDateHeureAppelMonitor(String dateHeureAppelMonitor) {
        this.dateHeureAppelMonitor = dateHeureAppelMonitor;
    }

    public String getDateHeureAppelMedecin() {
        return dateHeureAppelMedecin;
    }

    public void setDateHeureAppelMedecin(String dateHeureAppelMedecin) {
        this.dateHeureAppelMedecin = dateHeureAppelMedecin;
    }

    public int getNbAnnulation() {
        return nbAnnulation;
    }

    public void setNbAnnulation(int nbAnnulation) {
        this.nbAnnulation = nbAnnulation;
    }

    public String getDateHeureDerniereAnnulation() {
        return dateHeureDerniereAnnulation;
    }

    public void setDateHeureDerniereAnnulation(String dateHeureDerniereAnnulation) {
        this.dateHeureDerniereAnnulation = dateHeureDerniereAnnulation;
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

    public Long getTempsAttenteEffectif() {
        return tempsAttenteEffectif;
    }

    public void setTempsAttenteEffectif(Long tempsAttenteEffectif) {
        this.tempsAttenteEffectif = tempsAttenteEffectif;
    }

    public Long getNbTotalDemandeursEnCours() {
        return nbTotalDemandeursEnCours;
    }

    public void setNbTotalDemandeursEnCours(Long nbTotalDemandeursEnCours) {
        this.nbTotalDemandeursEnCours = nbTotalDemandeursEnCours;
    }

    public String getServicesChoisi() {
        return servicesChoisi;
    }

    public void setServicesChoisi(String servicesChoisi) {
        this.servicesChoisi = servicesChoisi;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getErrorMessageIfAny() {
        return errorMessageIfAny;
    }

    public void setErrorMessageIfAny(String errorMessageIfAny) {
        this.errorMessageIfAny = errorMessageIfAny;
    }


    public int getCalledByMonitor() {
        return calledByMonitor;
    }

    public void setCalledByMonitor(int calledByMonitor) {
        this.calledByMonitor = calledByMonitor;
    }

    public int getCalledByMedecin() {
        return calledByMedecin;
    }

    public void setCalledByMedecin(int calledByMedecin) {
        this.calledByMedecin = calledByMedecin;
    }
}
