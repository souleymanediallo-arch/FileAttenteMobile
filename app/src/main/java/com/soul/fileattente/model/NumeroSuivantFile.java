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

//    private String numeroSuivant;
//    private String dateHeureRetour;
//    private String nbTotalPatientEnCours;
//    private String tempsAttenteEstime;
//    private String tempsAttenteMoyen;
//    private String clinique;
//    private String deviceId;
//    private String service;
//    private String telephonePatient;
//    private String dateHeureDemande;


    private Long id;
    private Long demandeNumeroFileid;
    private String numeroSuivant;
    private String statutNumSuivantFile;
    private String dateHeureDemande;
    private String dateHeureRetour;
    private String dateHeureAppel;
    private String dateHeureAnnulation;
    private Integer nbAnnulation;
    private Long nbTotalDemandeursEnCours;
    private Long tempsAttenteMoyen;
    private Long tempsAttenteEstime;
    private Long tempsAttenteEffectif;
    private String additionalKeyValuePairCSList;
    //
    private String  nomService;
    private String  serviceDestinationid;
    private String  deviceId;
    private String  telephoneDemandeur;
    private String  emailDemandeur;
    //

    public NumeroSuivantFile() {
    }

    public NumeroSuivantFile(Long id, Long demandeNumeroFileid, String numeroSuivant, String statutNumSuivantFile, String dateHeureDemande, String dateHeureRetour, String dateHeureAppel, String dateHeureAnnulation, Integer nbAnnulation, Long nbTotalDemandeursEnCours, Long tempsAttenteMoyen, Long tempsAttenteEstime, Long tempsAttenteEffectif, String additionalKeyValuePairCSList, String nomService, String serviceDestinationid, String deviceId, String telephoneDemandeur, String emailDemandeur) {
        this.id = id;
        this.demandeNumeroFileid = demandeNumeroFileid;
        this.numeroSuivant = numeroSuivant;
        this.statutNumSuivantFile = statutNumSuivantFile;
        this.dateHeureDemande = dateHeureDemande;
        this.dateHeureRetour = dateHeureRetour;
        this.dateHeureAppel = dateHeureAppel;
        this.dateHeureAnnulation = dateHeureAnnulation;
        this.nbAnnulation = nbAnnulation;
        this.nbTotalDemandeursEnCours = nbTotalDemandeursEnCours;
        this.tempsAttenteMoyen = tempsAttenteMoyen;
        this.tempsAttenteEstime = tempsAttenteEstime;
        this.tempsAttenteEffectif = tempsAttenteEffectif;
        this.additionalKeyValuePairCSList = additionalKeyValuePairCSList;
        this.nomService = nomService;
        this.serviceDestinationid = serviceDestinationid;
        this.deviceId = deviceId;
        this.telephoneDemandeur = telephoneDemandeur;
        this.emailDemandeur = emailDemandeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDemandeNumeroFileid() {
        return demandeNumeroFileid;
    }

    public void setDemandeNumeroFileid(Long demandeNumeroFileid) {
        this.demandeNumeroFileid = demandeNumeroFileid;
    }

    public String getNumeroSuivant() {
        return numeroSuivant;
    }

    public void setNumeroSuivant(String numeroSuivant) {
        this.numeroSuivant = numeroSuivant;
    }

    public String getStatutNumSuivantFile() {
        return statutNumSuivantFile;
    }

    public void setStatutNumSuivantFile(String statutNumSuivantFile) {
        this.statutNumSuivantFile = statutNumSuivantFile;
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

    public String getDateHeureAppel() {
        return dateHeureAppel;
    }

    public void setDateHeureAppel(String dateHeureAppel) {
        this.dateHeureAppel = dateHeureAppel;
    }

    public String getDateHeureAnnulation() {
        return dateHeureAnnulation;
    }

    public void setDateHeureAnnulation(String dateHeureAnnulation) {
        this.dateHeureAnnulation = dateHeureAnnulation;
    }

    public Integer getNbAnnulation() {
        return nbAnnulation;
    }

    public void setNbAnnulation(Integer nbAnnulation) {
        this.nbAnnulation = nbAnnulation;
    }

    public Long getNbTotalDemandeursEnCours() {
        return nbTotalDemandeursEnCours;
    }

    public void setNbTotalDemandeursEnCours(Long nbTotalDemandeursEnCours) {
        this.nbTotalDemandeursEnCours = nbTotalDemandeursEnCours;
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

    public String getAdditionalKeyValuePairCSList() {
        return additionalKeyValuePairCSList;
    }

    public void setAdditionalKeyValuePairCSList(String additionalKeyValuePairCSList) {
        this.additionalKeyValuePairCSList = additionalKeyValuePairCSList;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
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

    @Override
    public String toString() {
        return "NumeroSuivantFile{" +
                "id=" + id +
                ", demandeNumeroFileid=" + demandeNumeroFileid +
                ", numeroSuivant='" + numeroSuivant + '\'' +
                ", statutNumSuivantFile='" + statutNumSuivantFile + '\'' +
                ", dateHeureDemande='" + dateHeureDemande + '\'' +
                ", dateHeureRetour='" + dateHeureRetour + '\'' +
                ", dateHeureAppel='" + dateHeureAppel + '\'' +
                ", dateHeureAnnulation='" + dateHeureAnnulation + '\'' +
                ", nbAnnulation=" + nbAnnulation +
                ", nbTotalDemandeursEnCours=" + nbTotalDemandeursEnCours +
                ", tempsAttenteMoyen=" + tempsAttenteMoyen +
                ", tempsAttenteEstime=" + tempsAttenteEstime +
                ", tempsAttenteEffectif=" + tempsAttenteEffectif +
                ", additionalKeyValuePairCSList='" + additionalKeyValuePairCSList + '\'' +
                ", nomService='" + nomService + '\'' +
                ", serviceDestinationid='" + serviceDestinationid + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", telephoneDemandeur='" + telephoneDemandeur + '\'' +
                ", emailDemandeur='" + emailDemandeur + '\'' +
                '}';
    }

    //    public NumeroSuivantFile() {
//    }
//
//    public NumeroSuivantFile(String numeroSuivant, String dateHeureRetour, String nbTotalPatientEnCours, String tempsAttenteEstime, String tempsAttenteMoyen, String clinique, String deviceId, String service, String telephonePatient, String dateHeureDemande) {
//        this.numeroSuivant = numeroSuivant;
//        this.dateHeureRetour = dateHeureRetour;
//        this.nbTotalPatientEnCours = nbTotalPatientEnCours;
//        this.tempsAttenteEstime = tempsAttenteEstime;
//        this.tempsAttenteMoyen = tempsAttenteMoyen;
//        this.clinique = clinique;
//        this.deviceId = deviceId;
//        this.service = service;
//        this.telephonePatient = telephonePatient;
//        this.dateHeureDemande = dateHeureDemande;
//    }
//
//    public String getNumeroSuivant() {
//        return numeroSuivant;
//    }
//
//    public void setNumeroSuivant(String numeroSuivant) {
//        this.numeroSuivant = numeroSuivant;
//    }
//
//    public String getDateHeureRetour() {
//        return dateHeureRetour;
//    }
//
//    public void setDateHeureRetour(String dateHeureRetour) {
//        this.dateHeureRetour = dateHeureRetour;
//    }
//
//    public String getNbTotalPatientEnCours() {
//        return nbTotalPatientEnCours;
//    }
//
//    public void setNbTotalPatientEnCours(String nbTotalPatientEnCours) {
//        this.nbTotalPatientEnCours = nbTotalPatientEnCours;
//    }
//
//    public String getTempsAttenteEstime() {
//        return tempsAttenteEstime;
//    }
//
//    public void setTempsAttenteEstime(String tempsAttenteEstime) {
//        this.tempsAttenteEstime = tempsAttenteEstime;
//    }
//
//    public String getTempsAttenteMoyen() {
//        return tempsAttenteMoyen;
//    }
//
//    public void setTempsAttenteMoyen(String tempsAttenteMoyen) {
//        this.tempsAttenteMoyen = tempsAttenteMoyen;
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
//    public String getService() {
//        return service;
//    }
//
//    public void setService(String service) {
//        this.service = service;
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
//    @Override
//    public String toString() {
//        return "NumeroSuivantFile{" +
//                "numeroSuivant='" + numeroSuivant + '\'' +
//                ", dateHeureRetour='" + dateHeureRetour + '\'' +
//                ", nbTotalPatientEnCours='" + nbTotalPatientEnCours + '\'' +
//                ", tempsAttenteEstime='" + tempsAttenteEstime + '\'' +
//                ", tempsAttenteMoyen='" + tempsAttenteMoyen + '\'' +
//                ", clinique='" + clinique + '\'' +
//                ", deviceId='" + deviceId + '\'' +
//                ", service='" + service + '\'' +
//                ", telephonePatient='" + telephonePatient + '\'' +
//                ", dateHeureDemande='" + dateHeureDemande + '\'' +
//                '}';
//    }
}
