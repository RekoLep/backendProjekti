package com.backendprojekti.yolento.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Asiakkaat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long asiakasId;

    private String etunimi;
    private String sukunimi;
    private String puhelinnumero;

    @NotNull(message = "Sähköposti ei saa olla tyhjä")
    @NotEmpty(message = "Sähköposti ei saa olla tyhjä")
    @Email(message = "Sähköpostiosoite ei ole kelvollinen")
    private String sahkoposti;

    public Asiakkaat() {
    }

    public Asiakkaat(String etunimi, String sukunimi, String puhelinnumero, String sahkoposti) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.puhelinnumero = puhelinnumero;
        this.sahkoposti = sahkoposti;
    }

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
