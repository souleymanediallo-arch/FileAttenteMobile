package com.soul.fileattente.model;

import java.io.Serializable;

public class Etablissement  implements Serializable {
    private String idEtablissement;
    private String nomEtablissement;
    private String adresse;
    private String telephone;
    private String email;

    public Etablissement() {
    }

    public String getIdEtablissement() {
        return idEtablissement;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setIdEtablissement(String idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "idEtablissement='" + idEtablissement + '\'' +
                ", nomEtablissement='" + nomEtablissement + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
