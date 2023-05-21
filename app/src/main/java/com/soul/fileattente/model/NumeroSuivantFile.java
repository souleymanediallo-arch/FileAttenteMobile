package com.soul.fileattente.model;

import java.io.Serializable;

public class NumeroSuivantFile implements Serializable {

//    {
//            "numeroSuivant": "0001",
//            "dateHeureRetour": "2023-05-13T10:36:02.678Z",
//            "nbTotalPatientEnCours": "100",
//            "tempsAttenteEstime": "300",
//            "tempsAttenteMoyen": "250",
//            "clinique": "Vision Medicale Coumba",
//            "deviceId": "0122455789632111441251",
//            "service": "Pediatrie",
//            "telephonePatient": "+221766752276",
//            "dateHeureDemande": "2023-05-13T10:35:02.678Z"
//    }

//    @SerializedName("numeroSuivant")
//    private String numeroSuivant;
//    @SerializedName("dateHeureRetour")
//    private String dateHeureRetour;
//    @SerializedName("nbTotalPatientEnCours")
//    private String nbTotalPatientEnCours;
//    @SerializedName("tempsAttenteEstime")
//    private String tempsAttenteEstime;
//    @SerializedName("tempsAttenteMoyen")
//    private String tempsAttenteMoyen;
//    @SerializedName("clinique")
//    private String clinique;
//    @SerializedName("deviceId")
//    private String deviceId;
//    @SerializedName("service")
//    private String service;
//    @SerializedName("telephonePatient")
//    private String telephonePatient;
//    @SerializedName("dateHeureDemande")
//    private String dateHeureDemande;

    private String numeroSuivant;
    private String dateHeureRetour;
    private String nbTotalPatientEnCours;
    private String tempsAttenteEstime;
    private String tempsAttenteMoyen;
    private String clinique;
    private String deviceId;
    private String service;
    private String telephonePatient;
    private String dateHeureDemande;

    public NumeroSuivantFile() {
    }

    public NumeroSuivantFile(String numeroSuivant, String dateHeureRetour, String nbTotalPatientEnCours, String tempsAttenteEstime, String tempsAttenteMoyen, String clinique, String deviceId, String service, String telephonePatient, String dateHeureDemande) {
        this.numeroSuivant = numeroSuivant;
        this.dateHeureRetour = dateHeureRetour;
        this.nbTotalPatientEnCours = nbTotalPatientEnCours;
        this.tempsAttenteEstime = tempsAttenteEstime;
        this.tempsAttenteMoyen = tempsAttenteMoyen;
        this.clinique = clinique;
        this.deviceId = deviceId;
        this.service = service;
        this.telephonePatient = telephonePatient;
        this.dateHeureDemande = dateHeureDemande;
    }

    public String getNumeroSuivant() {
        return numeroSuivant;
    }

    public void setNumeroSuivant(String numeroSuivant) {
        this.numeroSuivant = numeroSuivant;
    }

    public String getDateHeureRetour() {
        return dateHeureRetour;
    }

    public void setDateHeureRetour(String dateHeureRetour) {
        this.dateHeureRetour = dateHeureRetour;
    }

    public String getNbTotalPatientEnCours() {
        return nbTotalPatientEnCours;
    }

    public void setNbTotalPatientEnCours(String nbTotalPatientEnCours) {
        this.nbTotalPatientEnCours = nbTotalPatientEnCours;
    }

    public String getTempsAttenteEstime() {
        return tempsAttenteEstime;
    }

    public void setTempsAttenteEstime(String tempsAttenteEstime) {
        this.tempsAttenteEstime = tempsAttenteEstime;
    }

    public String getTempsAttenteMoyen() {
        return tempsAttenteMoyen;
    }

    public void setTempsAttenteMoyen(String tempsAttenteMoyen) {
        this.tempsAttenteMoyen = tempsAttenteMoyen;
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

    @Override
    public String toString() {
        return "NumeroSuivantFile{" +
                "numeroSuivant='" + numeroSuivant + '\'' +
                ", dateHeureRetour='" + dateHeureRetour + '\'' +
                ", nbTotalPatientEnCours='" + nbTotalPatientEnCours + '\'' +
                ", tempsAttenteEstime='" + tempsAttenteEstime + '\'' +
                ", tempsAttenteMoyen='" + tempsAttenteMoyen + '\'' +
                ", clinique='" + clinique + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", service='" + service + '\'' +
                ", telephonePatient='" + telephonePatient + '\'' +
                ", dateHeureDemande='" + dateHeureDemande + '\'' +
                '}';
    }
}
