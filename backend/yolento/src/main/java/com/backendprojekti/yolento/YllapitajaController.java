package com.backendprojekti.yolento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class YllapitajaController {
    
    
    @GetMapping("/message")
    public String getMessage() {
        return "Tästä voi suorittaa kaikki sinulle oikeutetut toiminnot";
    }

    private final AsiakkaatRepository repository;

    public YllapitajaController(AsiakkaatRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/asiakkaat")
    public List<Asiakkaat> getAsiakkaat() {
        return repository.findAll();  // Palauttaa asiakkaita JSON-muodossa
    }
}




