package com.soul.fileattente.model;

public class ServiceAGG {

    String servicesChoisi;
    String nomServiceDestination;
    String numberOfElementInQueue;
    String numeroSuivant;
    NumeroSuivantFile numeroSuivantFile;

    public ServiceAGG() {
    }

    public ServiceAGG(String servicesChoisi, String nomServiceDestination, String numberOfElementInQueue, String numeroSuivant, NumeroSuivantFile numeroSuivantFile) {
        this.servicesChoisi = servicesChoisi;
        this.nomServiceDestination = nomServiceDestination;
        this.numberOfElementInQueue = numberOfElementInQueue;
        this.numeroSuivant = numeroSuivant;
        this.numeroSuivantFile = numeroSuivantFile;
    }

    public String getServicesChoisi() {
        return servicesChoisi;
    }

    public void setServicesChoisi(String servicesChoisi) {
        this.servicesChoisi = servicesChoisi;
    }

    public String getNomServiceDestination() {
        return nomServiceDestination;
    }

    public void setNomServiceDestination(String nomServiceDestination) {
        this.nomServiceDestination = nomServiceDestination;
    }

    public String getNumberOfElementInQueue() {
        return numberOfElementInQueue;
    }

    public void setNumberOfElementInQueue(String numberOfElementInQueue) {
        this.numberOfElementInQueue = numberOfElementInQueue;
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

//    String servicesChoisi,
//    String nomServiceDestination,
//    int numberOfElementInQueue,
//    String numeroSuivant

//    {
//            "servicesChoisi": "672f9b05e434e738150a1cc2",
//            "nomServiceDestination": null,
//            "numberOfElementInQueue": 8,
//            "numeroSuivant": "PED01"
//    }
//
//
//    public ServiceAGG() {
//    }
//
//    public ServiceAGG(Long servicesChoisi, String nomServiceDestination, String numberOfElementInQueue, String numeroSuivant, NumeroSuivantFile numeroSuivantFile) {
//        this.servicesChoisi = servicesChoisi;
//        this.nomServiceDestination = nomServiceDestination;
//        this.numberOfElementInQueue = numberOfElementInQueue;
//        this.numeroSuivant = numeroSuivant;
//        this.numeroSuivantFile = numeroSuivantFile;
//    }
//
//    public Long getServicesChoisi() {
//        return servicesChoisi;
//    }
//
//    public void setServicesChoisi(Long servicesChoisi) {
//        this.servicesChoisi = servicesChoisi;
//    }
//
//    public String getNomServiceDestination() {
//        return nomServiceDestination;
//    }
//
//    public void setNomServiceDestination(String nomServiceDestination) {
//        this.nomServiceDestination = nomServiceDestination;
//    }
//
//    public String getNumberOfElementInQueue() {
//        return numberOfElementInQueue;
//    }
//
//    public void setNumberOfElementInQueue(String numberOfElementInQueue) {
//        this.numberOfElementInQueue = numberOfElementInQueue;
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
//    public NumeroSuivantFile getNumeroSuivantFile() {
//        return numeroSuivantFile;
//    }
//
//    public void setNumeroSuivantFile(NumeroSuivantFile numeroSuivantFile) {
//        this.numeroSuivantFile = numeroSuivantFile;
//    }
}
