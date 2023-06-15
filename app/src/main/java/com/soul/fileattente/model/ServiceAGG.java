package com.soul.fileattente.model;

public class ServiceAGG {

    Long idService;
    String nomService;
    String nbDemandeur;
    String numeroSuivant;
    NumeroSuivantFile numeroSuivantFile;

    public ServiceAGG() {
    }

    public ServiceAGG(Long idService, String nomService, String nbDemandeur, String numeroSuivant, NumeroSuivantFile numeroSuivantFile) {
        this.idService = idService;
        this.nomService = nomService;
        this.nbDemandeur = nbDemandeur;
        this.numeroSuivant = numeroSuivant;
        this.numeroSuivantFile = numeroSuivantFile;
    }

    public Long getIdService() {
        return idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getNbDemandeur() {
        return nbDemandeur;
    }

    public void setNbDemandeur(String nbDemandeur) {
        this.nbDemandeur = nbDemandeur;
    }

    public String getNumeroSuivant() {
        return numeroSuivant;
    }

    public void setNumeroSuivant(String numeroSuivant) {
        this.numeroSuivant = numeroSuivant;
    }

    public NumeroSuivantFile getNumeroSuivantFile() {
        return numeroSuivantFile;
    }

    public void setNumeroSuivantFile(NumeroSuivantFile numeroSuivantFile) {
        this.numeroSuivantFile = numeroSuivantFile;
    }
}
