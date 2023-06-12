package com.soul.fileattente.model;

import java.io.Serializable;

public class Param implements Serializable {
    private Long id;
    String key;
    String value;
    private Long etablissementid;

    public Param() {
    }

    public Param(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEtablissementid() {
        return etablissementid;
    }

    public void setEtablissementid(Long etablissementid) {
        this.etablissementid = etablissementid;
    }

    @Override
    public String toString() {
        return "Param{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", etablissementid=" + etablissementid +
                '}';
    }
}
