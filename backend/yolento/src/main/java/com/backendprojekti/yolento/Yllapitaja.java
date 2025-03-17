package com.backendprojekti.yolento;

public class Yllapitaja {
    private String etunimi;
    private String sukunimi;
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
    @Override
    public String toString() {
        return "Yllapitaja [etunimi=" + etunimi + ", sukunimi=" + sukunimi + "]";
    }
    
}
