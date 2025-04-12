package com.backendprojekti.yolento.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.backendprojekti.yolento.domain.Asiakkaat;
import com.backendprojekti.yolento.domain.AsiakkaatRepository;
import com.backendprojekti.yolento.domain.Asunto;
import com.backendprojekti.yolento.domain.AsuntoRepository;
import com.backendprojekti.yolento.domain.Vuokrasopimus;
import com.backendprojekti.yolento.domain.VuokrasopimusRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class YllapitajaController {

    

    private final AsiakkaatRepository asiakkaatRepository;
    private final AsuntoRepository asuntoRepository;
    private final VuokrasopimusRepository vuokrasopimusRepository;

    public YllapitajaController(AsiakkaatRepository asiakkaatRepository,
            AsuntoRepository asuntoRepository,
            VuokrasopimusRepository vuokrasopimusRepository) {
        this.asiakkaatRepository = asiakkaatRepository;
        this.asuntoRepository = asuntoRepository;
        this.vuokrasopimusRepository = vuokrasopimusRepository;
        
    }

    // Pääsivu, joka palauttaa viestin (tehty reactin toimivuuden testaamisen
    // vuoksi)
    @GetMapping("/message")
    public String getMessage() {
        return "Tästä voi suorittaa kaikki sinulle oikeutetut toiminnot";
    }

    // Hakee kaikki asiakkaat
    @GetMapping("/asiakkaat")
    public List<Asiakkaat> getAsiakkaat() {
        return asiakkaatRepository.findAll();
    }

  

    // Päivittää asunnon tietoja
    @PutMapping("/asunnot/{id}")
    public Asunto updateAsunto(@PathVariable Long id, @RequestBody Asunto asunto) {
        asunto.setAsuntoId(id);
        return asuntoRepository.save(asunto);
    }

    // Poistaa asunnon
    @DeleteMapping("/asunnot/{id}")
    public void deleteAsunto(@PathVariable Long id) {
        asuntoRepository.deleteById(id);
    }

    // Hakee kaikki vuokrasopimukset
    @GetMapping("/vuokrasopimukset")
    public List<Vuokrasopimus> getVuokrasopimukset() {
        return vuokrasopimusRepository.findAll();
    }

    // Hakee tietyn vuokrasopimuksen
    @GetMapping("/vuokrasopimukset/{id}")
    public Optional<Vuokrasopimus> getVuokrasopimusById(@PathVariable Long id) {
        return vuokrasopimusRepository.findById(id);
    }

    // Luo uuden vuokrasopimuksen
    @PostMapping("/vuokrasopimukset")
    public Vuokrasopimus createVuokrasopimus(@RequestBody Vuokrasopimus vuokrasopimus) {
        return vuokrasopimusRepository.save(vuokrasopimus);
    }

    // Päivittää olemassa olevan vuokrasopimuksen
    @PutMapping("/vuokrasopimukset/{id}")
    public Vuokrasopimus updateVuokrasopimus(@PathVariable Long id, @RequestBody Vuokrasopimus vuokrasopimus) {
        vuokrasopimus.setId(id);
        return vuokrasopimusRepository.save(vuokrasopimus);
    }

    // Poistaa vuokrasopimuksen id:n perusteella
    @DeleteMapping("/vuokrasopimukset/{id}")
    public void deleteVuokrasopimus(@PathVariable Long id) {
        if (vuokrasopimusRepository.existsById(id)) {
            vuokrasopimusRepository.deleteById(id);
        } else {
            throw new RuntimeException("Vuokrasopimusta ei löydy ID:llä: " + id);
        }
    }
}
