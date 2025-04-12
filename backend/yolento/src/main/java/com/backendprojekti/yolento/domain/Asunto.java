package com.backendprojekti.yolento.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity

public class Asunto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long asuntoId;

    
    
    private String osoite;
    private String kaupunki;
    private String kaupunginOsa;
    private String status;

    public Asunto(){

    }

    public Asunto(String osoite, String kaupunki, String kaupunginOsa, String status){
        this.osoite = osoite;
        this.kaupunki = kaupunki;
        this.kaupunginOsa = kaupunginOsa;
        this.status = status;
    }

    public Long getAsuntoId() {
        return asuntoId;
    }

    public void setAsuntoId(Long asuntoId) {
        this.asuntoId = asuntoId;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getKaupunginOsa() {
        return kaupunginOsa;
    }

    public void setKaupunginOsa(String kaupunginOsa) {
        this.kaupunginOsa = kaupunginOsa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Asunto [asuntoId=" + asuntoId + ", osoite=" + osoite + ", kaupunki=" + kaupunki + ", kaupunginOsa="
                + kaupunginOsa + ", status=" + status + "]";
    }

}
