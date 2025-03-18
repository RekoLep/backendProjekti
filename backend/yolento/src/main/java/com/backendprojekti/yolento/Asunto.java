package com.backendprojekti.yolento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Asunto {

    @Id
    private Long asuntoId;
    private String osoite;
    private String kaupunki;
    private String kaupunginOsa;
    private String status;

    public Asunto(){

    }

    public Asunto(Long asuntoId, String osoite, String kaupunki, String kaupunginOsa, String status){
        this.asuntoId = asuntoId;
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
