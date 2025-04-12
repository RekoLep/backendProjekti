package com.backendprojekti.yolento.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Vuokrasopimus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "asiakas_id", nullable = false)
    private Asiakkaat asiakas;

    
    @ManyToOne
    @JoinColumn(name = "asunto_id", nullable = false)
    private Asunto asunto;

    
    @Column(nullable = false)
    private LocalDate alkamisaika;

   
    private LocalDate paattymisaika;

    public Vuokrasopimus(){
        
    }

    
    public Vuokrasopimus(Asiakkaat asiakas, Asunto asunto, LocalDate alkamisaika, LocalDate paattymisaika) {
        this.asiakas = asiakas;
        this.asunto = asunto;
        this.alkamisaika = alkamisaika;
        this.paattymisaika = paattymisaika;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Asiakkaat getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakkaat asiakas) {
        this.asiakas = asiakas;
    }

    public Asunto getAsunto() {
        return asunto;
    }

    public void setAsunto(Asunto asunto) {
        this.asunto = asunto;
    }

    public LocalDate getAlkamisaika() {
        return alkamisaika;
    }

    public void setAlkamisaika(LocalDate alkamisaika) {
        this.alkamisaika = alkamisaika;
    }

    public LocalDate getPaattymisaika() {
        return paattymisaika;
    }

    public void setPaattymisaika(LocalDate paattymisaika) {
        this.paattymisaika = paattymisaika;
    }

    @Override
    public String toString() {
        return "Vuokrasopimus [id=" + id + ", asiakas=" + asiakas.getEtunimi() + " " + asiakas.getSukunimi() +
                ", asunto=" + asunto.getOsoite() + ", alkamisaika=" + alkamisaika + ", paattymisaika=" + paattymisaika + "]";
    }
}
