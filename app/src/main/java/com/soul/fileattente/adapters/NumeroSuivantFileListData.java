package com.soul.fileattente.adapters;

import com.soul.fileattente.model.NumeroSuivantFile;

public class NumeroSuivantFileListData extends NumeroSuivantFile {

    private int imgId;
    private NumeroSuivantFile numeroSuivantFile;

    public NumeroSuivantFileListData(NumeroSuivantFile numeroSuivantFile, int imgId) {
//        super.setLibelleServiceDestination(serviceDestination.getLibelleServiceDestination());
//        super.setNomServiceDestination(serviceDestination.getNomServiceDestination());
//        super.setStatutServiceDestination(serviceDestination.getStatutServiceDestination());
//        this.imgId = imgId;
//        this.serviceDestination = serviceDestination;

        super.setNomServiceDestination(numeroSuivantFile.getNomServiceDestination());
        super.setStatut(numeroSuivantFile.getStatut());
        this.imgId = imgId;
        this.numeroSuivantFile = numeroSuivantFile;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public NumeroSuivantFile getNumeroSuivantFile() {
        return numeroSuivantFile;
    }
}
