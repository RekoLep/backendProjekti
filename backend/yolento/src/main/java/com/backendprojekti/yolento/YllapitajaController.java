package com.backendprojekti.yolento;

import org.springframework.web.bind.annotation.*;
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

    // Pääsivu, joka palauttaa viestin
    @GetMapping("/message")
    public String getMessage() {
        return "Tästä voi suorittaa kaikki sinulle oikeutetut toiminnot";
    }

    // Hakee kaikki asiakkaat
    @GetMapping("/asiakkaat")
    public List<Asiakkaat> getAsiakkaat() {
        return asiakkaatRepository.findAll();
    }

    // Hakee kaikki asunnot
    @GetMapping("/asunnot")
    public List<Asunto> getAsunnot() {
        return asuntoRepository.findAll();
    }

    // Hakee kaikki vuokrasopimukset
    @GetMapping("/vuokrasopimukset")
    public List<Vuokrasopimus> getVuokrasopimukset() {
        return vuokrasopimusRepository.findAll();
    }

    // Hakee tietyn vuokrasopimuksen id:n perusteella
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
        if (vuokrasopimusRepository.existsById(id)) {
            vuokrasopimus.setId(id);
            return vuokrasopimusRepository.save(vuokrasopimus);
        } else {
            throw new RuntimeException("Vuokrasopimusta ei löydy ID:llä: " + id);
        }
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




