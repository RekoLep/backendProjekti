package com.backendprojekti.yolento;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AsiakkaatRepository asiakkaatRepository;
    private final AsuntoRepository asuntoRepository;
    private final VuokrasopimusRepository vuokrasopimusRepository;

    @Autowired
    public DatabaseLoader(AsiakkaatRepository asiakkaatRepository, AsuntoRepository asuntoRepository, VuokrasopimusRepository vuokrasopimusRepository) {
        this.asiakkaatRepository = asiakkaatRepository;
        this.asuntoRepository = asuntoRepository;
        this.vuokrasopimusRepository = vuokrasopimusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        asiakkaatRepository.save(new Asiakkaat(1L, "Matti", "Meikäläinen", "0401234567", "matti@example.com"));
        asiakkaatRepository.save(new Asiakkaat(2L, "Pekka", "Pöllö", "0402345678", "pekka@example.com"));
        asiakkaatRepository.save(new Asiakkaat(3L, "Liisa", "Lintu", "0403456789", "liisa@example.com"));
        asiakkaatRepository.save(new Asiakkaat(4L, "Kaisa", "Kukko", "0404567890", "kaisa@example.com"));
        asiakkaatRepository.save(new Asiakkaat(5L, "Janne", "Jänis", "0405678901", "janne@example.com"));
        asiakkaatRepository.save(new Asiakkaat(6L, "Erik", "Eerola", "0406789012", "erik@example.com"));
        asiakkaatRepository.save(new Asiakkaat(7L, "Sanna", "Saarinen", "0407890123", "sanna@example.com"));
        asiakkaatRepository.save(new Asiakkaat(8L, "Antti", "Aho", "0408901234", "antti@example.com"));
        asiakkaatRepository.save(new Asiakkaat(9L, "Pia", "Peltola", "0409012345", "pia@example.com"));
        asiakkaatRepository.save(new Asiakkaat(10L, "Tommi", "Tapaninen", "0410123456", "tommi@example.com"));
        

        
        asuntoRepository.save(new Asunto("Mannerheimintie 10", "Helsinki", "Kamppi", "Vuokrattu"));
        asuntoRepository.save(new Asunto("Aleksanterinkatu 25", "Helsinki", "Kruununhaka", "Vuokrattu"));
        asuntoRepository.save(new Asunto("Itäkatu 5", "Helsinki", "Itäkeskus", "Vuokrattu"));
        asuntoRepository.save(new Asunto("Hämeentie 45", "Helsinki", "Kallio", "Vapaa"));
        asuntoRepository.save(new Asunto("Pohjoisesplanadi 3", "Helsinki", "Kaartinkaupunki", "Vapaa"));
        asuntoRepository.save(new Asunto("Leppävaarankatu 10", "Espoo", "Leppävaara", "Vapaa"));
        asuntoRepository.save(new Asunto("Tapiolantie 15", "Espoo", "Tapiola", "Vapaa"));
        asuntoRepository.save(new Asunto("Myyrmäentie 7", "Vantaa", "Myyrmäki", "Vapaa"));
        asuntoRepository.save(new Asunto("Tikkurilantie 12", "Vantaa", "Tikkurila", "Vapaa"));
        asuntoRepository.save(new Asunto("Martinlaaksontie 20", "Vantaa", "Martinlaakso", "Vapaa"));


        Optional<Asiakkaat> asiakas1 = asiakkaatRepository.findById(1L);
        Optional<Asiakkaat> asiakas2 = asiakkaatRepository.findById(2L);
        Optional<Asiakkaat> asiakas3 = asiakkaatRepository.findById(3L);
        
        Optional<Asunto> asunto1 = asuntoRepository.findById(1L);
        Optional<Asunto> asunto2 = asuntoRepository.findById(2L);
        Optional<Asunto> asunto3 = asuntoRepository.findById(3L);

        
        if (asiakas1.isPresent() && asunto1.isPresent()) {
            vuokrasopimusRepository.save(new Vuokrasopimus(asiakas1.get(), asunto1.get(), LocalDate.of(2025, 1, 1), LocalDate.of(2026, 1, 1)));
        }

        if (asiakas2.isPresent() && asunto2.isPresent()) {
            vuokrasopimusRepository.save(new Vuokrasopimus(asiakas2.get(), asunto2.get(), LocalDate.of(2025, 2, 1), null)); 
        }

        if (asiakas3.isPresent() && asunto3.isPresent()) {
            vuokrasopimusRepository.save(new Vuokrasopimus(asiakas3.get(), asunto3.get(), LocalDate.of(2025, 3, 1), LocalDate.of(2026, 3, 1)));
        }
        

    }
}