package com.backendprojekti.yolento;

public class Asiakkaat {
    private String etunimi;
    private String sukunimi;
    private String puhelinnumero;
    private String sahkoposti;
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
