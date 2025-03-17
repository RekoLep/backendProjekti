package com.backendprojekti.yolento;

import jakarta.persistence.*;

@Entity
public class Asiakkaat {

    @Id
    private long asiakasId;
    private String etunimi;
    private String sukunimi;
    private String puhelinnumero;
    private String sahkoposti;

    // Oletuskonstruktori
    public Asiakkaat() {
    }

    // Parametrillinen konstruktori
    public Asiakkaat(long asiakasId, String etunimi, String sukunimi, String puhelinnumero, String sahkoposti) {
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
    }

    // Getterit ja setterit
    public long getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(long asiakasId) {
        this.asiakasId = asiakasId;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    @Override
    public String toString() {
        return "Asiakkaat [etunimi=" + etunimi + ", sukunimi=" + sukunimi + ", puhelinnumero=" + puhelinnumero
                + ", sahkoposti=" + sahkoposti + "]";
    }
}
