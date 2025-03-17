package com.backendprojekti.yolento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final AsiakkaatRepository repository;

    @Autowired
    public DatabaseLoader(AsiakkaatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Asiakkaat(1L, "Matti", "Meikäläinen", "0401234567", "matti@example.com"));
        repository.save(new Asiakkaat(2L, "Pekka", "Pöllö", "0402345678", "pekka@example.com"));
        repository.save(new Asiakkaat(3L, "Liisa", "Lintu", "0403456789", "liisa@example.com"));
        repository.save(new Asiakkaat(4L, "Kaisa", "Kukko", "0404567890", "kaisa@example.com"));
        repository.save(new Asiakkaat(5L, "Janne", "Jänis", "0405678901", "janne@example.com"));
    }
}
